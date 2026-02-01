# tlk-domain

## Purpose
Holds the core business concepts and rules for the salon: users, profiles, booking, services, and payments.
This module should be as framework-light as possible.

## Responsibilities
- Define domain objects (booking, services, payments, profiles)
- Define invariants and state transitions (e.g., when a booking is confirmed)
- Define domain-level statuses and policies (cancellation windows, deposit rules)
- Define shared enums/types used across modules (roles, booking/payment statuses)

## Non-goals
- No HTTP / controllers
- No database mappings
- No UI concerns

## Key domain concepts
- **Booking** is *request-to-confirm*: clients request, stylists approve/decline/propose changes.
- **Payments** support:
    - deposits (to lock/confirm a booking after approval)
    - final payments with tips
    - multiple tender types (online vs in-person recorded)

## TODO (domain)
- [ ] Finalize roles model (CLIENT / STYLIST / ADMIN; optionally MANAGER)
- [ ] Define Booking lifecycle states and valid transitions (REQUESTED/PROPOSED/APPROVED/CONFIRMED/DECLINED/CANCELLED/COMPLETED)
- [ ] Define Service + duration + pricing model (base price + optional stylist overrides)
- [ ] Define Payment model:
    - [ ] deposit vs final payment
    - [ ] tip amount handling
    - [ ] tender types: ONLINE, CASH, CARD_TERMINAL
    - [ ] payment statuses + refund/void semantics
- [ ] Define cancellation + refund policy rules (simple MVP rules first)