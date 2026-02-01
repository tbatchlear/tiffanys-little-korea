# tlk-data

## Purpose
Persistence layer: stores and retrieves application data.

## Responsibilities
- Repositories/DAOs
- Database schema/migrations
- Data integrity constraints and indices

## Non-goals
- No business workflows (belongs in tlk-service)
- No web endpoints (belongs in tlk-web)

## TODO (data)
- [ ] Pick DB (if not already): PostgreSQL recommended for scheduling/payment systems
- [ ] Create schema for users/roles/account_status
- [ ] Create schema for profiles (client, stylist)
- [ ] Create schema for services + stylist offerings
- [ ] Create schema for availability + exceptions
- [ ] Create schema for bookings + booking status history (optional)
- [ ] Create schema for payments/transactions (provider ids, status, amount, currency)
- [ ] Add uniqueness + conflict prevention where possible (e.g., booking slot constraints)