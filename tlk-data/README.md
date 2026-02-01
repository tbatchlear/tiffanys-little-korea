# tlk-data

## Purpose
Persistence layer: stores and retrieves application data.

## Responsibilities
- Repositories/DAOs
- Database schema/migrations
- Data integrity constraints and indices
- Audit-friendly storage for booking and payment changes (useful for disputes and customer support)

## Non-goals
- No business workflows (belongs in tlk-service)
- No web endpoints (belongs in tlk-web)

## TODO (data)
- [X] PostgreSQL schema + migrations (chosen primary database)
- [ ] Users/roles/account_status tables
- [ ] Profiles:
    - [ ] client profile (personal info, preferences)
    - [ ] stylist profile (bio, socials, specialties)
- [ ] Portfolio items per stylist (images/urls, tags, ordering)
- [ ] Services catalog:
    - [ ] service definitions (name, duration, base price)
    - [ ] stylist service offerings + optional price overrides
- [ ] Availability model:
    - [ ] recurring weekly availability
    - [ ] exceptions/time-off/blocks
- [ ] Booking storage:
    - [ ] booking requests + status
    - [ ] proposed changes history (recommended)
    - [ ] conflict-safe constraints where possible
- [ ] Payments storage:
    - [ ] provider metadata for online payments (Authorize.Net references)
    - [ ] tender types: ONLINE, CASH, CARD_TERMINAL
    - [ ] deposit/final classification + tip amounts
    - [ ] status transitions, refunds/voids
- [ ] Add uniqueness + conflict prevention where possible (e.g., booking slot constraints)