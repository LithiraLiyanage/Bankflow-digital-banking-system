<div align="center">

# 🏦 BankFlow  
### 💳 Digital Banking & Transaction Management System

<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=700&size=26&duration=2500&pause=700&color=2563EB&center=true&vCenter=true&width=950&lines=Secure+Spring+Boot+Digital+Banking+System;JWT+Auth+%7C+Transactions+%7C+Audit+Logs;Fraud+Detection+%7C+PDF+Statements+%7C+Admin+Dashboard;Built+for+Java+Spring+Boot+%7C+Full+Stack+Portfolios" alt="Typing SVG" />

<br/>

![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-JWT-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![React](https://img.shields.io/badge/React-Vite-61DAFB?style=for-the-badge&logo=react&logoColor=black)
![Tailwind](https://img.shields.io/badge/Tailwind-CSS-06B6D4?style=for-the-badge&logo=tailwindcss&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

<br/>

**A secure, modern, portfolio-ready banking application with simulated transactions, audit logs, fraud detection rules, PDF statements, and dashboards.**

<br/>

<img src="https://capsule-render.vercel.app/api?type=waving&color=2563EB&height=95&section=header" width="100%" />

</div>

---

## 📸 Project Preview

<div align="center">

<img src="screenshots/bankflow-preview.png" alt="BankFlow Project Preview" width="100%" />

</div>

> Add your screenshot as `screenshots/bankflow-preview.png`.

---

## ⚠️ Important Scope

**BankFlow is a portfolio/demo banking system for educational use only.**  
It does not connect to real banking systems, payment networks, card networks, or real money services. All banking operations are simulated inside the local application database.

---

## 🌟 Overview

**BankFlow** is a full-stack digital banking and transaction management system built with **Spring Boot + React**.

Customers can create bank accounts, deposit, withdraw, transfer funds internally, view transaction history, and download monthly PDF statements. Admins can monitor customers, accounts, transactions, audit logs, and suspicious activity.

---

## ✨ Key Features

### 👤 Customer
- 🔐 Register/login with JWT
- 🏦 Create savings/current accounts
- 💰 Deposit money
- 🏧 Withdraw money
- 🔁 Transfer funds internally
- 📜 View transaction history
- 📄 Download PDF statements
- 📊 View dashboard analytics

### 🛡️ Admin
- 👥 View all users
- 🏦 View all accounts
- 💳 View all transactions
- 🚩 View flagged transactions
- 📜 View audit logs
- 📊 Dashboard summary

---

## 🧠 Fraud Detection Rules

Transactions can be flagged when:

| Rule | Description |
|---|---|
| 🚩 High Amount | Amount greater than 500,000 |
| ⚡ Rapid Transfers | More than 5 transfers within 10 minutes |
| 🏧 Large Withdrawal | Withdrawal greater than 80% of balance |
| ❌ Failed Attempts | Multiple failed withdrawals |

---

## 🧰 Tech Stack

| Layer | Technology |
|---|---|
| ☕ Backend | Java 17, Spring Boot 3 |
| 🔐 Security | Spring Security, JWT, BCrypt |
| 🗄️ Database | PostgreSQL, Spring Data JPA |
| ✅ Validation | Bean Validation |
| 📘 API Docs | Swagger / OpenAPI |
| 📄 PDF Export | OpenPDF |
| 🎨 Frontend | React, Vite, Tailwind CSS |
| 📊 Charts | Recharts |
| 🐳 Database Runtime | Docker Compose |

---

## 🏗️ System Architecture

```text
┌──────────────────────────────┐
│        React Frontend         │
│  Vite + Tailwind + Recharts   │
└───────────────┬──────────────┘
                │ REST API + JWT
                ▼
┌──────────────────────────────┐
│       Spring Boot Backend     │
│ Security + Validation + APIs  │
└───────────────┬──────────────┘
                │ Spring Data JPA
                ▼
┌──────────────────────────────┐
│          PostgreSQL           │
│ Users / Accounts / Txns       │
│ Audit Logs / Statements       │
└──────────────────────────────┘
```

---

## 📁 Project Structure

```text
bankflow-digital-banking-system/
├── backend/
│   ├── src/main/java/com/bankflow/
│   ├── src/main/resources/
│   └── pom.xml
├── frontend/
│   ├── src/
│   └── package.json
├── docs/
├── screenshots/
├── docker-compose.yml
├── README.md
└── .gitignore
```

---

## ⚙️ Run Locally

### 1️⃣ Start PostgreSQL

```bash
docker compose up -d
```

### 2️⃣ Start Backend

```bash
cd backend
copy src\main\resources\application-example.properties src\main\resources\application.properties
mvn spring-boot:run
```

Backend: `http://localhost:8080`  
Swagger: `http://localhost:8080/swagger-ui/index.html`

### 3️⃣ Start Frontend

```bash
cd frontend
npm install
copy .env.example .env
npm run dev
```

Frontend: `http://localhost:5173`

---

## 🔑 Demo Accounts

```text
Admin:    admin@example.com / Admin12345
Customer: customer@example.com / Customer12345
```

---

## 🔌 API Endpoints

### Auth
```text
POST /api/auth/register
POST /api/auth/login
GET  /api/auth/me
```

### Accounts
```text
POST /api/accounts
GET  /api/accounts
GET  /api/accounts/{id}
```

### Transactions
```text
POST /api/transactions/deposit
POST /api/transactions/withdraw
POST /api/transactions/transfer
GET  /api/transactions
```

### Statements
```text
GET /api/statements/{accountId}/download?month=5&year=2026
```

### Admin
```text
GET /api/admin/dashboard
GET /api/admin/users
GET /api/admin/accounts
GET /api/admin/transactions
GET /api/admin/transactions/flagged
GET /api/admin/audit-logs
```

---

## ✅ Validation Features

- Email format validation
- Password strength validation
- Positive transaction amounts
- Insufficient balance prevention
- Frozen/closed account protection
- Account ownership validation
- Admin-only API protection
- Clean API error responses

---

## 🛡️ Security Features

- Spring Security
- JWT auth
- BCrypt password hashing
- Role-based access control
- DTO responses
- Global exception handler
- CORS config
- Audit logging

---

## 📌 CV Bullet

> Developed BankFlow, a secure digital banking system using Spring Boot and React with JWT authentication, account management, deposits, withdrawals, fund transfers, transaction history, fraud detection rules, audit logs, PDF statement export, Swagger documentation, and role-based dashboards.

---

## 👨‍💻 Author

<div align="center">

**Lithira Liyanage**  
Java Developer | Spring Boot Developer | Full Stack Developer

[![GitHub](https://img.shields.io/badge/GitHub-LithiraLiyanage-181717?style=for-the-badge&logo=github)](https://github.com/LithiraLiyanage)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Lithira%20Liyanage-0A66C2?style=for-the-badge&logo=linkedin)](https://www.linkedin.com/in/lithira-liyanage-667b99403)
[![Portfolio](https://img.shields.io/badge/Portfolio-Visit%20Website-2563EB?style=for-the-badge&logo=vercel&logoColor=white)](https://lithira-liyanage.vercel.app/)

</div>

---

<div align="center">

### ⭐ If you like this project, give it a star!

<img src="https://capsule-render.vercel.app/api?type=waving&color=2563EB&height=120&section=footer" width="100%" />

</div>
