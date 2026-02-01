# Tiffany’s Little Korea (TLK)

Website and booking platform for **Tiffany’s Little Korea**, designed to support salon operations end-to-end:
marketing, client intake, appointment scheduling, stylist workflows, administrative controls, and payments.

## Purpose (what this site is for)

### 1) Public website (marketing + trust)
- Showcase the salon brand and services
- Publish **pricing information** (base pricing and/or ranges)
- Showcase **stylist portfolios** (photos of work, specialties, featured looks)
- Provide social links and contact info

### 2) Client portal (self-service)
Clients can:
- Create an account and log in (MVP: email/password; social login can be added later)
- Maintain a **profile** with personal information and hair preferences/notes
- **Request appointments** (request-to-confirm workflow)
- View upcoming/past appointments and their status (requested/approved/proposed/etc.)
- View pricing and stylist portfolios
- Leave reviews (recommended: only after completed appointments)

### 3) Stylist portal (daily operations)
Stylists can:
- Maintain a stylist profile (bio, specialties, socials)
- Build and manage a portfolio
- Configure availability (weekly hours + exceptions)
- Manage booking requests:
  - approve / decline / propose changes
  - view upcoming schedule

### 4) Admin functions (control + safety)
Admins can:
- Add/change/remove stylists (or promote an existing user to stylist)
- Lock/unlock stylist and user accounts
- Manage service catalog and pricing content (scope can grow over time)
- Moderate reviews (optional but recommended)

### 5) Payments (deposits + final payments + tips; online or in-person)
- Support **deposits** to lock in a booking slot (after approval)
- Support **final payments** with **tips**
- Payments may be:
  - **Online** via **Authorize.Net (Chase Merchant Services)**, or
  - **In-person** at the salon (cash / card terminal), recorded in the system for accurate balances and reporting
- Track payment status, provider transaction references, refunds/voids (policy-driven)

### 6) Notifications (reduce no-shows)
- Send client notifications:
  - **Request received** (immediate)
  - **Appointment confirmed/declined** (immediate after stylist decision)
  - **24-hour reminder** (scheduled)
- Delivery channels: **email first**, then **SMS**
- Messages are **outbound-only** (no replies required); include a link to view/cancel/reschedule on the website
- Support opt-in/opt-out (for SMS) and basic delivery logging

## Architecture (modules)
- **tlk-domain**: business model + rules (framework-light)
- **tlk-data**: persistence layer (schema, repositories, migrations)
- **tlk-service**: application use-cases (booking workflow, payments, admin operations)
- **tlk-web**: Spring MVC API/web layer (controllers, security, validation)
- **tlk-ui**: React/TypeScript UI (Vite)

## Architecture decisions (high level)

### UI architecture
- The UI is a **Single Page Application (SPA)** built with React + TypeScript (Vite).
- Client/stylist/admin experiences are implemented as SPA routes.

### API architecture
- Backend exposes a JSON API under an **`/api`** path prefix.

### Deployment / origin strategy
- Prefer **same-origin** hosting:
  - UI served from the same domain as the backend
  - API available at `https://<domain>/api/...`
- This simplifies cookies, authentication, and avoids CORS complexity in the default deployment.

### Authentication strategy (phased)
- **Now (website):** session-based authentication using secure, HTTP-only cookies.
- **Later (mobile expansion):** add token-based auth (e.g., OIDC + access tokens) without rewriting core business endpoints.

## Glossary (shared vocabulary)

### Roles
- **CLIENT**: A customer who requests and attends appointments.
- **STYLIST**: A staff member who provides services and manages availability and booking requests.
- **ADMIN**: Full administrative access: manage stylists/users, lock/unlock accounts, moderate content.

### Booking
- **Request-to-confirm**: Client requests an appointment; a stylist must approve (or propose changes) before it’s confirmed.
- **Booking statuses (planned)**
  - `REQUESTED`: client submitted a request.
  - `PROPOSED`: stylist proposed changes (time/service/etc.) and client action is required.
  - `APPROVED`: stylist approved the request; deposit may still be required to confirm.
  - `CONFIRMED`: booking is locked (deposit paid if required) and placed on the schedule.
  - `DECLINED`: stylist declined the request.
  - `CANCELLED`: cancelled by client/stylist/admin according to policy.
  - `COMPLETED`: appointment occurred.

### Payments
- **Deposit**: Payment required to lock a booking slot after approval.
- **Final payment**: Remaining balance due for services performed.
- **Tip**: Optional gratuity added to final payment (recorded separately for reporting).
- **Tender types (planned)**
  - `ONLINE`: processed online via payment provider.
  - `CASH`: paid at salon and recorded manually.
  - `CARD_TERMINAL`: paid at salon via the terminal and recorded manually.
- **Payment provider (planned)**
  - **Authorize.Net** (via Chase Merchant Services) for online card processing.

### Notifications
- **Reminder**: Automated message sent prior to an appointment to reduce no-shows.
- **Notification events (planned)**
  - **Request received**: sent when a booking request is submitted.
  - **Confirmed/declined**: sent when the stylist approves or declines the request.
  - **24-hour notice**: sent 24 hours before the appointment start time (for confirmed bookings).
- **Outbound-only**: clients manage changes via website links (cancel/reschedule), not by replying.

## Tech decisions (ADR-style log)

This section tracks decisions so the project stays consistent over time.
When a decision changes, add a new entry rather than editing history.

### Decision template
- **ID**: ADR-YYYY-MM-DD-###
- **Status**: Proposed | Accepted | Deprecated
- **Context**: Why this decision is needed
- **Decision**: What we chose
- **Consequences**: Tradeoffs and follow-ups

### Decisions
- **ID**: ADR-2026-02-01-001  
  **Status**: Accepted  
  **Context**: Booking workflow needs to match salon operations and reduce back-and-forth confusion.  
  **Decision**: Booking is **request-to-confirm** (stylist approves/declines/proposes changes).  
  **Consequences**: Requires booking request inbox for stylists and status-driven UI.

- **ID**: ADR-2026-02-01-002  
  **Status**: Accepted  
  **Context**: Salon already uses Chase Merchant Services terminal; online payments should match the same merchant setup.  
  **Decision**: Online card processing will use **Authorize.Net (Chase Merchant Services)**.  
  **Consequences**: Implement Authorize.Net integration and store provider transaction references.

- **ID**: ADR-2026-02-01-003  
  **Status**: Accepted  
  **Context**: Clients may pay online or at the salon; the system must reflect balances accurately regardless of channel.  
  **Decision**: Support **hybrid payments**: online payments plus manual recording for in-person `CASH` and `CARD_TERMINAL` payments.  
  **Consequences**: Payment model must include tender type and support partial payments and reconciliation.

- **ID**: ADR-2026-02-01-004  
  **Status**: Accepted  
  **Context**: Reduce no-shows with automated reminders and keep operations simple at low volume.  
  **Decision**: Notifications will be **email first**, then **cloud SMS** (provider TBD). Client messages are:
  1) **Request received** (immediate), 2) **Appointment confirmed/declined** (immediate), 3) **24-hour reminder** (scheduled).  
     **Consequences**: Requires notification scheduling and delivery logging. SMS requires opt-in/opt-out support. Messages should include website links to view/cancel/reschedule; inbound replies are not required.

- **ID**: ADR-2026-02-01-005  
  **Status**: Accepted  
  **Context**: Booking/payment systems require durable storage, integrity constraints, and reliable reporting.  
  **Decision**: Use **PostgreSQL** as the primary database. Consider **Redis later** for caching/auxiliary needs (not as the system of record).  
  **Consequences**: Add migrations, backup approach, and local dev DB via Docker.

- **ID**: ADR-2026-02-01-006  
  **Status**: Accepted  
  **Context**: The system should be easy to run locally and deploy consistently.  
  **Decision**: **Dockerize early**, including local development orchestration (e.g., app + PostgreSQL).  
  **Consequences**: Define container boundaries, env vars, and a local dev compose setup.

- **ID**: ADR-2026-02-01-007  
  **Status**: Accepted  
  **Context**: Choose a UI architecture that supports client/stylist/admin flows with minimal page reload friction.  
  **Decision**: The website UI will be a **Single Page Application (SPA)** using React + TypeScript (Vite).  
  **Consequences**: Routing is handled client-side; backend primarily serves JSON APIs and (optionally) static assets.

- **ID**: ADR-2026-02-01-008  
  **Status**: Accepted  
  **Context**: Keep authentication simple and secure for a web-first product while preserving the option for a future mobile app.  
  **Decision**: Use **session-based authentication** (secure HTTP-only cookie) for the web app initially; design for a future **token-based** auth mode for mobile.  
  **Consequences**: Avoid storing tokens in browser storage for the web app; keep API boundaries clean so mobile can reuse endpoints later.

- **ID**: ADR-2026-02-01-009  
  **Status**: Accepted  
  **Context**: Reduce deployment complexity and avoid CORS/cookie cross-site issues.  
  **Decision**: Prefer **same-origin** hosting: UI and backend served from the same domain; backend API uses an **`/api`** prefix.  
  **Consequences**: Simplifies cookies and local/prod parity. If split-origin hosting is added later, CORS and auth settings must be revisited.

## Projected technologies (living list)

These are **planned or under evaluation** and may change. Confirm choices via ADRs.

### Backend
- Java 21
- Spring MVC (web)
- (Planned) Spring Security for authN/authZ
- PostgreSQL (Accepted)
- (Future) Redis for caching/auxiliary features (Proposed, not required)

### Frontend
- React + TypeScript (Vite)
- ESLint

### Integrations
- Payments: Authorize.Net (Accepted)
- Notifications:
  - Email reminders (Accepted as first step)
  - SMS reminders via cloud SMS provider (Accepted direction; provider TBD)

### DevOps / Packaging
- Docker (Accepted early)
- (Planned) Docker Compose for local dev
- (Planned) Same-origin deployment (UI + API on one domain)