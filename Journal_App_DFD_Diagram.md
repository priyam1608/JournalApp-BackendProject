# Journal App - Data Flow Diagram (DFD)

## Level 0 DFD (Context Diagram)

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   External      │    │   External      │    │   External      │
│   Weather API   │    │   Email Service │    │   Google Auth   │
│                 │    │   (SMTP)        │    │                 │
└─────────┬───────┘    └─────────┬───────┘    └─────────┬───────┘
          │                      │                      │
          │                      │                      │
          ▼                      ▼                      ▼
┌─────────────────────────────────────────────────────────────────┐
│                        JOURNAL APP                              │
│                                                                 │
│  ┌─────────────────┐    ┌─────────────────┐    ┌─────────────┐  │
│  │   User Input    │    │   Admin Input   │    │   Public    │  │
│  │   (CRUD Ops)    │    │   (Management)  │    │   Access    │  │
│  └─────────────────┘    └─────────────────┘    └─────────────┘  │
│                                                                 │
│  ┌─────────────────┐    ┌─────────────────┐    ┌─────────────┐  │
│  │   MongoDB       │    │   Redis Cache   │    │   File      │  │
│  │   Database      │    │   (Weather)     │    │   Storage   │  │
│  └─────────────────┘    └─────────────────┘    └─────────────┘  │
└─────────────────────────────────────────────────────────────────┘
```

## Level 1 DFD (Main Processes)

```
┌─────────────────────────────────────────────────────────────────────────────────────────────────┐
│                                    JOURNAL APP SYSTEM                                           │
│                                                                                                 │
│  ┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐      │
│  │   1.0           │    │   2.0           │    │   3.0           │    │   4.0           │      │
│  │ Authentication  │    │ Journal Entry   │    │ User Management │    │ Weather Service │      │
│  │ & Authorization │    │ Management      │    │                 │    │                 │      │
│  └─────────┬───────┘    └─────────┬───────┘    └─────────┬───────┘    └─────────┬───────┘      │
│            │                      │                      │                      │              │
│            ▼                      ▼                      ▼                      ▼              │
│  ┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐      │
│  │   5.0           │    │   6.0           │    │   7.0           │    │   8.0           │      │
│  │ Sentiment       │    │ Email           │    │ Data            │    │ Admin           │      │
│  │ Analysis        │    │ Notification    │    │ Caching         │    │ Management      │      │
│  └─────────────────┘    └─────────────────┘    └─────────────────┘    └─────────────────┘      │
│                                                                                                 │
└─────────────────────────────────────────────────────────────────────────────────────────────────┘
```

## Level 2 DFD (Detailed Process Decomposition)

### Process 1.0: Authentication & Authorization
```
┌─────────────────────────────────────────────────────────────────┐
│                   1.0 AUTHENTICATION & AUTHORIZATION             │
│                                                                 │
│  ┌─────────────┐    ┌─────────────┐    ┌─────────────────────┐  │
│  │ 1.1 JWT     │    │ 1.2 Google  │    │ 1.3 Spring Security │  │
│  │ Token       │    │ OAuth       │    │ Filter Chain        │  │
│  │ Generation  │    │ Integration │    │                     │  │
│  └─────────────┘    └─────────────┘    └─────────────────────┘  │
│                                                                 │
│  ┌─────────────┐    ┌─────────────┐    ┌─────────────────────┐  │
│  │ 1.4 User    │    │ 1.5 Role    │    │ 1.6 Session         │  │
│  │ Validation  │    │ Management  │    │ Management          │  │
│  └─────────────┘    └─────────────┘    └─────────────────────┘  │
└─────────────────────────────────────────────────────────────────┘
```

### Process 2.0: Journal Entry Management
```
┌─────────────────────────────────────────────────────────────────┐
│                   2.0 JOURNAL ENTRY MANAGEMENT                  │
│                                                                 │
│  ┌─────────────┐    ┌─────────────┐    ┌─────────────────────┐  │
│  │ 2.1 Create  │    │ 2.2 Read    │    │ 2.3 Update          │  │
│  │ Entry       │    │ Entries     │    │ Entry               │  │
│  └─────────────┘    └─────────────┘    └─────────────────────┘  │
│                                                                 │
│  ┌─────────────┐    ┌─────────────┐    ┌─────────────────────┐  │
│  │ 2.4 Delete  │    │ 2.5 Search  │    │ 2.6 Filter by       │  │
│  │ Entry       │    │ Entries     │    │ Date/Sentiment      │  │
│  └─────────────┘    └─────────────┘    └─────────────────────┘  │
└─────────────────────────────────────────────────────────────────┘
```

### Process 3.0: User Management
```
┌─────────────────────────────────────────────────────────────────┐
│                   3.0 USER MANAGEMENT                           │
│                                                                 │
│  ┌─────────────┐    ┌─────────────┐    ┌─────────────────────┐  │
│  │ 3.1 User    │    │ 3.2 Profile │    │ 3.3 Password        │  │
│  │ Registration│    │ Update      │    │ Management          │  │
│  └─────────────┘    └─────────────┘    └─────────────────────┘  │
│                                                                 │
│  ┌─────────────┐    ┌─────────────┐    ┌─────────────────────┐  │
│  │ 3.4 Account │    │ 3.5 User    │    │ 3.6 User            │  │
│  │ Deletion    │    │ Preferences │    │ Analytics           │  │
│  └─────────────┘    └─────────────┘    └─────────────────────┘  │
└─────────────────────────────────────────────────────────────────┘
```

## Data Stores

### Primary Data Stores:
1. **MongoDB Collections:**
   - `users` - User profiles and authentication data
   - `journal_entries` - Journal entries with sentiment analysis
   - `frequently_used_data` - Cached application data

2. **Redis Cache:**
   - Weather data caching
   - Session data
   - Frequently accessed data

### External Data Sources:
1. **Weather API** - External weather service for user greetings
2. **Email Service (SMTP)** - For notifications and sentiment reports
3. **Google OAuth** - External authentication service

## Data Flows

### User Authentication Flow:
```
User Input → JWT Filter → User Service → MongoDB → JWT Token → User Session
```

### Journal Entry Flow:
```
User Input → Journal Controller → Journal Service → Sentiment Analysis → MongoDB → User Update
```

### Weather Data Flow:
```
User Request → Weather Service → Redis Cache → Weather API → Cache Update → Response
```

### Email Notification Flow:
```
Scheduler → User Analysis → Sentiment Processing → Email Service → SMTP → User Email
```

## Security Controls

1. **JWT Authentication** - Token-based security
2. **Spring Security** - Role-based access control
3. **Input Validation** - Data sanitization
4. **CORS Configuration** - Cross-origin resource sharing
5. **API Rate Limiting** - Request throttling

## System Boundaries

- **Internal System:** Spring Boot application with MongoDB and Redis
- **External Systems:** Weather API, Email Service, Google OAuth
- **User Interfaces:** REST API endpoints, Swagger documentation
- **Data Persistence:** MongoDB Atlas (cloud), Redis Cloud 