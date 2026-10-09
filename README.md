# FutureHire - Placement Preparation Portal

A full-stack placement preparation portal built with **React + Vite** (Frontend) and **Spring Boot + MySQL** (Backend).

## 🚀 Tech Stack

| Layer | Technology |
|---|---|
| Frontend | React 18, Vite, Axios, React Router, Lucide Icons |
| Backend | Spring Boot 3.2, Spring Security, JWT, JPA/Hibernate |
| Database | MySQL 8.4 |
| Auth | JWT Bearer Token |

## 📁 Project Structure

```
FutureHire/
├── frontend/         # React + Vite app (port 3000)
└── backend/          # Spring Boot REST API (port 8081)
```

## ⚡ Quick Start (Local Development)

### Prerequisites
- Java 21+
- Maven 3.9+
- Node.js 18+
- MySQL 8.x running locally

### 1. Backend
```bash
cd backend
# Update src/main/resources/application.properties with your MySQL password
mvn spring-boot:run
# API runs at http://localhost:8081
```

### 2. Frontend
```bash
cd frontend
npm install
npm run dev
# App runs at http://localhost:3000
```

## 🔑 Demo Credentials
| Role | Email | Password |
|---|---|---|
| Admin | admin@futurehire.org | admin123 |
| Student | alex.morgan@university.edu | student123 |

## 🌐 API Docs
Swagger UI available at: `http://localhost:8081/swagger-ui.html`

## ✨ Features
- JWT Authentication (Student & Admin roles)
- Coding Practice (DSA Problems)
- Aptitude Quiz Engine
- Company Information Portal
- Resume Upload & ATS Score
- Mock Interview Booking
- Interview Experience Sharing
- Study Resources
- Admin Dashboard (Users, Reports, Questions)
