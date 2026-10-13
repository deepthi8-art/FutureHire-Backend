# FutureHire Backend

The backend service for **FutureHire**, built using **Java 21**, **Spring Boot 3.2**, **Spring Security**, **JWT Authentication**, and **MySQL / H2 Database**.

---

## 🚀 Key Features

- **Layered Architecture**: Controller → Service → Repository → Database Entity.
- **Role-Based Access Control (RBAC)**: Supports `ROLE_STUDENT` and `ROLE_ADMIN` using Spring Security and JWT filters.
- **Authentication**: JWT token generation, BCrypt password hashing, and user validation.
- **REST APIs**: Full suite of endpoints for Coding Practice, Aptitude Quizzes, Company Info, Resume Management, Interview Experiences, Mock Interviews, Progress Tracking, Study Resources, Notifications, and Admin Management.
- **Swagger / OpenAPI Documentation**: Interactive API documentation at `/swagger-ui.html`.
- **Database Seeding**: Automatic database initialization on startup with sample users, coding problems, aptitude tests, companies, and mock interview slots.

---

## 📋 Prerequisites

- **Java JDK 17+** (JDK 21 recommended)
- **Apache Maven 3.8+**
- **MySQL Database Server** (Optional: embedded H2 database runs automatically by default)

---

## 🛠️ How to Run the Spring Boot Server

### Run locally

```bash
cd backend
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

The Spring Boot backend starts at **`http://localhost:8081`** with an in-memory H2 database. The local profile seeds the demo student (`alex.morgan@university.edu` / `student123`) and admin (`admin@futurehire.org` / `admin123`) accounts. These credentials exist only in the local H2 profile.

### 2. Live API Documentation (Swagger UI)
Once the server is running, open `http://localhost:8081/swagger-ui.html`.

### 3. Database H2 Web Console (Embedded DB)
👉 **`http://localhost:8080/h2-console`**
- **JDBC URL**: `jdbc:h2:mem:futurehire_db`
- **Username**: `sa`
- **Password**: *(leave blank)*

---

## 🔑 Demo Credentials (Seeded Automatically)

| Role | Email | Password | Allowed Access |
|---|---|---|---|
| **Student** | `alex.morgan@university.edu` | `student123` | Student Dashboard & Module APIs |
| **Admin** | `admin@futurehire.org` | `admin123` | Admin Control Panel & Admin APIs |

---

## 🛰️ Key REST API Endpoints Overview

### Authentication (`/api/auth`)
- `POST /api/auth/register` - Student registration
- `POST /api/auth/login` - Authenticate & receive JWT token

### Student Modules (`/api/student`)
- `GET /api/student/coding/problems` - Fetch coding problems
- `POST /api/student/coding/submissions` - Submit code solution
- `GET /api/student/aptitude/quizzes` - List available aptitude quizzes
- `POST /api/student/aptitude/quizzes/{id}/submit` - Submit timed quiz answers
- `GET /api/student/companies` - List partner companies & recruitment rounds
- `POST /api/student/resumes/upload` - Upload PDF resume for ATS scanning
- `GET /api/student/interviews/experiences` - Browse senior interview experiences
- `POST /api/student/mock-interviews/book/{slotId}` - Book 1-on-1 mock interview slot
- `GET /api/student/progress/overview` - Placement readiness score & analytics
- `GET /api/student/resources` - Downloadable study guides & cheatsheets
- `GET /api/student/notifications` - Campus drive alerts & reminders

### Admin Modules (`/api/admin` - Protected for `ROLE_ADMIN`)
- `GET /api/admin/users` - Manage registered student profiles
- `POST /api/admin/questions/coding` - Add new coding problem
- `POST /api/admin/companies` - Configure recruitment partner profile
- `POST /api/admin/mock-interviews/slots` - Schedule mock interview slot
- `PUT /api/admin/mock-interviews/bookings/{id}/feedback` - Submit mentor feedback
- `GET /api/admin/reports/overview` - Platform analytics & departmental charts
