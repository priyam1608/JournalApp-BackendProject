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