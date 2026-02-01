# tlk-domain

## Purpose
Holds the core business concepts and rules for the salon: users, profiles, booking, services, and payments.
This module should be as framework-light as possible.

## Responsibilities
- Define domain objects (booking, services, payments, profiles)
- Define invariants and state transitions (e.g., when a booking is confirmed)
- Define domain-level statuses and policies (cancellation windows, deposit rules)

## Non-goals
- No HTTP / controllers
- No database mappings
- No UI concerns

## TODO (domain)
- [ ] Finalize roles model (CLIENT / STYLIST / ADMIN)
- [ ] Define Booking lifecycle states and valid transitions
- [ ] Define Service + duration + pricing model
- [ ] Define Payment concepts: deposit vs final, payment statuses
- [ ] Define cancellation + refund policy rules (even if simple initially)