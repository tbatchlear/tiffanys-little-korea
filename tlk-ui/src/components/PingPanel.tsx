import { useEffect, useState } from "react";
import { fetchPing, type PingResponse } from "../api/ping";

type LoadState =
    | { status: "idle" }
    | { status: "loading" }
    | { status: "success"; data: PingResponse }
    | { status: "error"; message: string };

export function PingPanel() {
    const [state, setState] = useState<LoadState>({ status: "idle" });

    useEffect(() => {
        const controller = new AbortController();

        setState({ status: "loading" });
        fetchPing(controller.signal)
            .then((data) => setState({ status: "success", data }))
            .catch((err: unknown) => {
                if (err instanceof DOMException && err.name === "AbortError") return;
                setState({
                    status: "error",
                    message: err instanceof Error ? err.message : String(err),
                });
            });

        return () => controller.abort();
    }, []);

    return (
        <div style={{ fontFamily: "system-ui", padding: 24, maxWidth: 720 }}>
            <h1 style={{ marginBottom: 8 }}>Tiffany&apos;s Little Korea</h1>
            <p style={{ marginTop: 0, opacity: 0.8 }}>
                Backend connectivity check:
            </p>

            {state.status === "idle" && <p>Idle</p>}
            {state.status === "loading" && <p>Loading…</p>}
            {state.status === "error" && <p>❌ {state.message}</p>}
            {state.status === "success" && <p>✅ {state.data.message}</p>}
        </div>
    );
}
