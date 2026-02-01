# tlk-ui

## Purpose
React + TypeScript user interface for clients, stylists, and admins.

## Responsibilities
- Public marketing pages (pricing, stylists, portfolio)
- Client portal (profile, request booking, view status, payments, reviews)
- Stylist portal (availability, approve/decline/propose booking requests, portfolio)
- Admin portal (manage stylists/users, lock/unlock accounts)

## Technical notes
- This UI is a **Single Page Application (SPA)** (Vite + client-side routing).
- Default auth is **session/cookie-based** (no JWT storage in the browser for MVP).
- Backend API is expected under an **`/api`** prefix on the same origin in the default deployment.

## UX notes (key flows)
- Booking is **request-to-confirm**
    - client submits request
    - stylist responds (approve/decline/propose)
    - client may need to accept a proposal
    - deposit may be required to reach “confirmed”
- Payments support:
    - online payment (Authorize.Net) for deposit/final
    - “pay at salon” option
    - tips on final payment

## TODO (ui)
- [ ] App layout + routing (public / client / stylist / admin)
- [ ] Auth screens (sign up, login, logout)
- [ ] Public pages: pricing + stylists + portfolio
- [ ] Client portal:
    - [ ] profile + preferences
    - [ ] request appointment + view status timeline
    - [ ] accept proposed changes
    - [ ] payments (deposit + final + tip) and “pay at salon”
- [ ] Stylist portal:
    - [ ] set availability (weekly + exceptions)
    - [ ] booking requests inbox (approve/decline/propose)
    - [ ] upcoming appointments view
    - [ ] portfolio management
- [ ] Admin pages:
    - [ ] lock/unlock accounts
    - [ ] add/remove stylists (or promote users)
- [ ] Notification preferences UI (SMS opt-in/out) + reminder messaging states