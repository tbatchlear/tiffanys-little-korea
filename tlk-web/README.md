# tlk-web

## Purpose
Spring MVC web/API layer: exposes endpoints to the UI and enforces security at the boundary.

## Responsibilities
- REST endpoints + request/response DTOs
- Authentication and authorization rules
- Validation + error handling
- Provider callbacks/webhooks (if required by payment/SMS providers)

## Non-goals
- No business rules (tlk-domain)
- No orchestration logic beyond request handling (tlk-service owns workflows)

## API conventions (high level)
- API endpoints are exposed under an **`/api`** prefix.
- Default deployment is **same-origin** (UI and API on the same domain) to simplify cookies and avoid CORS complexity.
- Authentication is **session-based** for the web app (secure HTTP-only cookie). Token-based auth can be added later for mobile.

## API areas (expected)
- Auth (register/login/logout)
- Profiles (client/stylist)
- Public content (pricing, stylists, portfolios)
- Booking (request-to-confirm: request/approve/decline/propose/accept/cancel)
- Payments:
  - deposit payment initiation/confirmation
  - final payment + tip
  - record in-person payments (cash / terminal card)
  - refunds/voids (admin)
- Notifications preferences (SMS opt-in/out) + reminder status (as needed)

## TODO (web)
- [ ] Define API routes for auth, profiles, services, availability, bookings, payments
- [ ] Configure security rules per role (client/stylist/admin)
- [ ] Consistent error model for UI (validation errors, forbidden, conflicts)
- [ ] Payment endpoints for Authorize.Net integration (provider abstraction at service layer)
- [ ] Optional: webhook/callback endpoint strategy (as required by provider approach)