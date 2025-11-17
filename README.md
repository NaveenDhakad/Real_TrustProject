# FLIPR Placement Full Stack Web Application

A complete full-stack web application built with Java Spring Boot, MySQL, and Bootstrap 5.

## Project Overview

This project is a portfolio and project management system with:
- **Frontend**: Landing page with project showcase and contact form
- **Backend**: Spring Boot REST APIs with MySQL database
- **Admin Panel**: Complete CRUD management for projects, clients, contact forms, and newsletter subscriptions

## System Requirements

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+
- Node.js (optional, for frontend development tools)

## Installation & Setup

### 1. Clone or Download the Project

\`\`\`bash
cd FLIPR-PLACEMENT-FULLSTACK-PROJECT
\`\`\`

### 2. Create MySQL Database

Open MySQL client and run:

\`\`\`sql
CREATE DATABASE flipr_placement;
\`\`\`

Or execute the SQL script:
\`\`\`bash
mysql -u root -p < src/main/sql/init.sql
\`\`\`

### 3. Update Application Configuration

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/flipr_placement?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_mysql_password
