# Patient Scheduler — Full Stack DevOps Project

A production-grade Patient Appointment Management System built with Spring Boot,
containerized with Docker, and deployed with a full CI/CD pipeline.

## Live Architecture

Browser → Nginx (port 80) → Spring Boot App (port 8080) → PostgreSQL (port 5432)

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Backend | Java 17 + Spring Boot 4.0 |
| Database | PostgreSQL 15 |
| Containerization | Docker + Docker Compose |
| Reverse Proxy | Nginx |
| CI/CD | GitHub Actions |
| Image Registry | Docker Hub |
| Cloud | DigitalOcean |

## Features

- Book patient appointments with real-time conflict detection
- View and cancel appointments
- Search doctors by specialty
- Admin dashboard with statistics
- Doctor schedule viewer

## Project Structure

patient-scheduler/
├── src/                          # Spring Boot source code
│   ├── main/java/                # Controllers, Services, Models
│   └── main/resources/           # Templates, properties
├── nginx/                        # Nginx reverse proxy config
├── .github/workflows/            # GitHub Actions CI pipeline
├── Dockerfile                    # Multi-stage Docker build
├── docker-compose.yml            # Multi-container orchestration
└── .env.example                  # Environment variables template

## How to Run Locally

**Prerequisites:** Docker and Docker Compose installed

```bash
# Clone the repository
git clone https://github.com/Dhiraj-Mantri/Patient-scheduler-via-SpringBoot.git
cd Patient-scheduler-via-SpringBoot

# Copy environment variables
cp .env.example .env
# Edit .env and fill in your values

# Start all containers
docker compose up -d

# Open in browser
http://localhost
```

## CI/CD Pipeline

Every push to `main` branch automatically:

1. Builds the Spring Boot project with Gradle
2. Runs all tests
3. Builds a Docker image
4. Pushes image to Docker Hub
5. Verifies all secrets are configured

## Environment Variables

| Variable | Description |
|----------|-------------|
| DB_USERNAME | PostgreSQL username |
| DB_PASSWORD | PostgreSQL password |
| DB_NAME | PostgreSQL database name |
| SPRING_PROFILES_ACTIVE | Spring profile (prod) |

## DevOps Journey

This project was built over 10 days learning DevOps from scratch:

- Day 1 — Git + GitHub
- Day 2 — Dockerfile
- Day 3 — Docker Compose + PostgreSQL
- Day 4 — GitHub Actions CI
- Day 5 — Docker Hub + Server simulation
- Day 6 — Nginx reverse proxy
- Day 7 — Secrets management
- Day 8 — Documentation + Production hardening
- Day 9 — Final polish
- Day 10 — Live cloud deployment

## Author

Dhiraj Mantri — [@Dhiraj-Mantri](https://github.com/Dhiraj-Mantri)
