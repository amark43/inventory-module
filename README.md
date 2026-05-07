# Dealer & Vehicle Inventory Module

Spring Boot multi-tenant inventory module.

# Dealer & Vehicle Inventory Module

## Features

- Multi-tenant inventory module
- Dealer CRUD APIs
- Vehicle CRUD APIs
- Pagination & sorting
- Vehicle filtering
- Tenant isolation
- Role-based admin endpoint

---

## Multi-Tenancy

Implemented using `X-Tenant-Id` request header.

All dealer and vehicle operations are tenant-scoped.

---

## Security

Admin endpoint requires:

- Username: admin
- Password: admin

Role:

- GLOBAL_ADMIN

---

## Admin Count Scope

`GET /admin/dealers/countBySubscription`

Returns GLOBAL counts across all tenants.

---

## Run Application

```bash
mvn spring-boot:run
