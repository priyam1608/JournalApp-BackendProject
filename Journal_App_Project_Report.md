# Journal App Project Report

---
**Formatting Instructions:**
- Font: Arial or Times New Roman
- Headings: 14pt, Bold
- Paragraphs: 12pt
- Line Spacing: 1.5
- Page Size: A4
---

## Index (Table of Contents)

| S. No. | Section Title                                      | Page No. |
|--------|----------------------------------------------------|----------|
| VI     | Introduction                                      |    1     |
|        | (a) About Organization                            |          |
|        | (b) Aims & Objectives                             |          |
|        | (c) Manpower                                      |          |
| VII    | System Study                                      |          |
|        | a) Existing System along with limitations          |          |
|        | b) Proposed System along with advantages           |          |
| VIII   | Feasibility Study                                 |          |
|        | a) Technical                                      |          |
|        | b) Behavioural                                    |          |
|        | c) Economic                                       |          |
| IX     | Project Monitoring System                         |          |
|        | a) Gantt Chart                                    |          |
| X      | System Analysis                                   |          |
|        | a) Requirement Specification                      |          |
|        | b) System Flowcharts                              |          |
|        | c) DFDs /ERDs (up to Level 2)                     |          |
| XI     | System Design                                     |          |
|        | a) File/ Data Design                              |          |
| XII    | Input / Output Form Design                        |          |
|        | a) Screen Design (Screenshots of all screens)      |          |
|        | b) Report Design                                  |          |
| XIII   | System Testing                                    |          |
|        | a) Preparation of Test Data                       |          |
|        | b) Testing With Live Data                         |          |
|        | c) Test Cases with results                        |          |
| XIV    | System Implementation                             |          |
|        | a) System Requirements (Hardware/Software)        |          |
| XV     | Documentation                                     |          |
| XVI    | Scope of the Project                              |          |
| XVII   | Bibliography                                      |          |

---

# VI. Introduction

## (a) About Organization
This project was developed as part of an academic exercise to demonstrate the design and implementation of a modern web-based journal application. The organization is a generic educational institution focused on fostering practical software engineering skills among students.

## (b) Aims & Objectives
- To design and implement a secure, user-friendly digital journal application.
- To enable users to create, manage, and analyze their personal journal entries.
- To integrate sentiment analysis for emotional insights.
- To provide weather-based greetings and notifications.
- To ensure data security, privacy, and scalability using modern technologies.

## (c) Manpower
- **Project Guide:** 1 (Faculty/Instructor)
- **Developers:** 1-2 (Students)
- **Testers:** 1 (Peer/Student)
- **End Users:** Students, Faculty, or General Users

---

# VII. System Study

## a) Existing System along with limitations
Traditional journaling is often paper-based or uses basic digital tools (e.g., text editors). These systems lack:
- Centralized, secure storage
- Sentiment analysis and emotional tracking
- Automated notifications or reminders
- Integration with external data (e.g., weather)
- User authentication and role management
- Data backup and recovery

## b) Proposed System along with advantages
The proposed Journal App offers:
- Secure, cloud-based storage of journal entries
- User authentication (including Google OAuth)
- Sentiment analysis for each entry
- Weather-based greetings and notifications
- Email notifications for weekly sentiment summaries
- Role-based access and admin features
- Scalable architecture using Spring Boot, MongoDB, and Redis

---

# VIII. Feasibility Study

## a) Technical Feasibility
- Uses proven technologies: Spring Boot, MongoDB, Redis, REST APIs
- Cloud-ready and scalable
- Integrates with external APIs (weather, email)
- Supports modern authentication (JWT, OAuth)

## b) Behavioural Feasibility
- User-friendly interface
- Minimal learning curve for end users
- Encourages regular journaling and self-reflection
- Provides actionable insights via sentiment analysis

## c) Economic Feasibility
- Open-source technologies minimize cost
- Cloud deployment can be scaled as needed
- Minimal hardware requirements for end users
- Maintenance and updates are straightforward

---

# IX. Project Monitoring System

## a) Gantt Chart
*Insert Gantt chart here (use MS Project, Excel, or online tool and paste image)*

| Task                        | Start Date | End Date   | Duration | Status   |
|-----------------------------|------------|------------|----------|----------|
| Requirement Analysis        | 01/01/2024 | 05/01/2024 | 5 days   | Complete |
| System Design               | 06/01/2024 | 12/01/2024 | 7 days   | Complete |
| Implementation              | 13/01/2024 | 25/01/2024 | 13 days  | Complete |
| Testing                     | 26/01/2024 | 30/01/2024 | 5 days   | Complete |
| Documentation               | 31/01/2024 | 05/02/2024 | 6 days   | Ongoing  |

---

# X. System Analysis

## a) Requirement Specification
- **Functional Requirements:**
  - User registration and login (including Google OAuth)
  - Create, read, update, delete (CRUD) journal entries
  - Sentiment analysis for each entry
  - Weather-based greeting on login
  - Email notifications for weekly sentiment summary
  - Admin features for user management
- **Non-Functional Requirements:**
  - Security (JWT, password encryption)
  - Performance (fast response, caching)
  - Scalability (cloud deployment)
  - Usability (intuitive UI)
  - Reliability (data backup, error handling)

## b) System Flowcharts
*Insert system flowcharts here (draw using draw.io, Lucidchart, or similar and paste image)*

## c) DFDs / ERDs (up to Level 2)
*See attached DFD_Diagram.md and ER_Diagram.md for detailed diagrams. Paste images or diagrams here as needed.*

---

# XI. System Design

## a) File/ Data Design
- **Database:** MongoDB (collections: users, journal_entries, frequently_used_data)
- **Data Relationships:**
  - One-to-many: User to JournalEntry
  - Enum: Sentiment in JournalEntry
- **Caching:** Redis for weather data and frequently used data
- **Data Security:** Passwords encrypted, JWT for sessions

---

# XII. Input / Output Form Design

## a) Screen Design (Screenshots of all screens In Color)
*Insert screenshots of login, registration, journal entry, dashboard, admin panel, etc. here*

## b) Report Design
- **User Reports:**
  - Weekly sentiment summary (email)
  - Journal entry history
- **Admin Reports:**
  - User activity logs
  - System usage statistics

---

# XIII. System Testing

## a) Preparation of Test Data
- Created sample users and journal entries with various sentiments
- Simulated weather API responses
- Prepared test cases for all user roles

## b) Testing With Live Data
- Deployed on test server
- Real users performed journaling, login, and admin tasks
- Monitored system logs and error reports

## c) Test Cases with results
| Test Case ID | Description                        | Input Data         | Expected Result         | Actual Result   | Status   |
|--------------|------------------------------------|--------------------|------------------------|-----------------|----------|
| TC01         | User Registration                  | Valid user info    | User created           | User created    | Pass     |
| TC02         | Login with valid credentials       | Username/password  | Login success          | Login success   | Pass     |
| TC03         | Create Journal Entry               | Entry data         | Entry saved            | Entry saved     | Pass     |
| TC04         | Sentiment Analysis                | Journal text       | Sentiment detected     | Sentiment OK    | Pass     |
| TC05         | Weather Greeting                  | User login         | Weather shown          | Weather shown   | Pass     |
| TC06         | Email Notification                | Weekly trigger     | Email sent             | Email sent      | Pass     |
| TC07         | Admin User Deletion               | User ID            | User deleted           | User deleted    | Pass     |

---

# XIV. System Implementation

## a) System Requirements (Hardware/Software)
- **Hardware:**
  - Any modern PC or laptop
  - Minimum 4GB RAM, 1GHz processor
  - Internet connection for cloud services
- **Software:**
  - Java 17+
  - Spring Boot
  - MongoDB Atlas (cloud)
  - Redis Cloud
  - Email (SMTP) service
  - Web browser (for UI)

---

# XV. Documentation
- User manual for basic operations
- Admin guide for management features
- API documentation (Swagger/OpenAPI)
- System architecture diagrams
- Database schema documentation

---

# XVI. Scope of the Project
- **Current Scope:**
  - Personal journaling with sentiment analysis
  - Weather-based greetings
  - Email notifications
  - Admin management
- **Future Enhancements:**
  - Mobile app version
  - Advanced analytics and visualization
  - Integration with calendar/reminders
  - Multi-language support

---

# XVII. Bibliography
- Spring Boot Documentation: https://spring.io/projects/spring-boot
- MongoDB Documentation: https://docs.mongodb.com/
- Redis Documentation: https://redis.io/documentation
- JavaMail API: https://javaee.github.io/javamail/
- OpenAPI/Swagger: https://swagger.io/
- draw.io (for diagrams): https://draw.io/
- Online Gantt Chart Tools: https://www.gantt.com/
- Additional academic and online resources as referenced in the report

---

**[End of Report]**

*Note: Please insert all required screenshots, diagrams, and Gantt chart images in the indicated sections before finalizing your report for submission. Format the document in your word processor as per the font and spacing guidelines above.* 