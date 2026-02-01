# tlk-service

## Purpose
Implements application use-cases by orchestrating domain rules + persistence + integrations.

## Responsibilities
- Booking workflows (request-to-confirm, approval, proposals, cancellations)
- Profile management workflows (client + stylist)
- Admin workflows (manage stylists/users, lock/unlock)
- Payment workflows (deposit, final payment, tips, refunds/voids)
- Notification workflows (reminders, delivery tracking, preferences)

## Non-goals
- No controller logic (tlk-web)
- No persistence details (tlk-data)

## Design note
- Service-layer use-cases should remain **agnostic to transport and auth mechanism** (session vs token),
  so future clients (e.g., a mobile app) can reuse the same workflows and endpoints.

## Key workflows owned here
- **Request-to-confirm booking**
  - client requests appointment
  - stylist approves/declines/proposes changes
  - client accepts proposal (if applicable)
  - optional: deposit required to confirm/lock
- **Hybrid payments**
  - online payments via Authorize.Net
  - in-person payments recorded as CASH or CARD_TERMINAL
  - tips supported on final payment

## TODO (service)
- [ ] Implement registration/login workflows (MVP: email/password)
- [ ] Booking request workflow:
  - [ ] create booking request
  - [ ] stylist approve/decline/propose
  - [ ] client accept proposal
- [ ] Availability computation (weekly hours + exceptions + existing bookings)
- [ ] Booking conflict handling (transaction boundary + retry strategy)
- [ ] Payment orchestration:
  - [ ] deposit flow: require deposit to confirm booking (after stylist approval)
  - [ ] final payment flow: compute outstanding balance + accept tip
  - [ ] record in-person payments (cash/card terminal)
  - [ ] refunds/voids (admin)
  - [ ] idempotency for payment calls (safe retries)
- [ ] Notifications:
  - [ ] send **Request received** notification (immediate)
  - [ ] send **Appointment confirmed/declined** notification (immediate)
  - [ ] schedule **24-hour reminder** for confirmed bookings
  - [ ] email implementation first; SMS provider integration later
  - [ ] opt-in/opt-out handling (for SMS)
- [ ] Admin workflows: promote user to stylist, lock/unlock