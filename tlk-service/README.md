# tlk-service

## Purpose
Implements application use-cases by orchestrating domain rules + persistence + integrations.

## Responsibilities
- Booking use-cases (search availability, create booking, cancel, reschedule)
- Profile management use-cases
- Admin use-cases (lock/unlock, manage stylists)
- Payment use-cases (create deposit, capture final payment, refund)

## Non-goals
- No controller logic (tlk-web)
- No persistence details (tlk-data)

## TODO (service)
- [ ] Implement registration/login workflows (MVP: email/password)
- [ ] Booking availability computation (recurring hours + exceptions + existing bookings)
- [ ] Booking conflict handling (transaction boundary + retry strategy)
- [ ] Deposit flow: require deposit to confirm booking
- [ ] Final payment flow: compute remaining balance and record payment
- [ ] Idempotency for payments (safe retries)
- [ ] Admin workflows: promote user to stylist, lock/unlock