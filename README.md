# Tiffany’s Little Korea (TLK)

Salon website + booking platform for Tiffany’s Little Korea.

## Product scope
### Client features
- Accounts (email/password; social login later)
- Profile + hair preferences
- Book appointments
- View pricing and stylist portfolios
- Leave reviews

### Stylist features
- Profile + portfolio + socials
- Set availability and services offered
- Set personal pricing (optional/phase 2)
- View and manage appointments

### Admin features
- Add/remove stylists
- Lock/unlock accounts
- Manage services/pricing content

### Payments
- Deposit to lock in a booking slot
- Final payment collection
- Refund policy (TBD)

## Architecture
- tlk-domain: business model + rules
- tlk-data: persistence layer
- tlk-service: application use-cases
- tlk-web: Spring MVC API/web layer
- tlk-ui: React/TypeScript UI

## Roadmap (high level)
- Milestone 0: Foundation
- Milestone 1: Identity & roles
- Milestone 2: Profiles & public marketing
- Milestone 3: Scheduling MVP
- Milestone 4: Payments v1 (deposit + final)
- Milestone 5: Reviews + notifications + polish