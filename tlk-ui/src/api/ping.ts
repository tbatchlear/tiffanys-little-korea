export type PingResponse = {
    message: string;
};

export async function fetchPing(signal?: AbortSignal): Promise<PingResponse> {
    const res = await fetch("/api/ping", { signal });
    if (!res.ok) {
        throw new Error(`Ping failed: ${res.status} ${res.statusText}`);
    }
    return (await res.json()) as PingResponse;
}
