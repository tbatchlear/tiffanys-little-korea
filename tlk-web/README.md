# tlk-web

## Purpose
Spring MVC web/API layer: exposes endpoints to the UI and enforces security at the boundary.

## Responsibilities
- REST endpoints + request/response DTOs
- Authentication and authorization rules
- Validation + error handling
- Webhooks endpoints for payments (if provider requires)

## Non-goals
- No business rules (tlk-domain)
- No orchestration logic beyond request handling (tlk-service owns workflows)

## TODO (web)
- [ ] Define API routes for auth, profiles, services, availability, bookings, payments
- [ ] Configure security rules per role (client/stylist/admin)
- [ ] Error model for UI (validation errors, forbidden, conflicts)
- [ ] Payment webhook endpoint + signature verification (when payments are implemented)