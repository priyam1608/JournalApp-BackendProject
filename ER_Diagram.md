# Journal App - Entity Relationship (E-R) Diagram

## Entity Relationship Diagram

```
┌─────────────────────────────────────────────────────────────────────────────────────────────────┐
│                                    JOURNAL APP E-R DIAGRAM                                      │
│                                                                                                 │
│  ┌─────────────────────────────────────────────────────────────────────────────────────────────┐  │
│  │                                    USER ENTITY                                              │  │
│  │                                                                                             │  │
│  │  ┌─────────────────────────────────────────────────────────────────────────────────────────┐  │
│  │  │                              User (Collection: users)                                   │  │
│  │  │                                                                                         │  │
│  │  │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐  │  │
│  │  │  │     _id     │  │  username   │  │  password   │  │    email    │  │sentimentAna │  │  │
│  │  │  │ (ObjectId)  │  │   (String)  │  │   (String)  │  │   (String)  │  │   lysis     │  │  │
│  │  │  │   Primary   │  │   Unique    │  │   Required  │  │   Optional  │  │  (Boolean)  │  │  │
│  │  │  │     Key     │  │   Indexed   │  │             │  │             │  │             │  │  │
│  │  │  └─────────────┘  └─────────────┘  └─────────────┘  └─────────────┘  └─────────────┘  │  │
│  │  │                                                                                         │  │
│  │  │  ┌─────────────────────────────────────────────────────────────────────────────────┐  │  │
│  │  │  │                    journalEntries (List<JournalEntry>)                          │  │  │
│  │  │  │                              @DBRef                                             │  │  │
│  │  │  └─────────────────────────────────────────────────────────────────────────────────┘  │  │
│  │  │                                                                                         │  │
│  │  │  ┌─────────────────────────────────────────────────────────────────────────────────┐  │  │
│  │  │  │                        roles (List<String>)                                     │  │  │
│  │  │  └─────────────────────────────────────────────────────────────────────────────────┘  │  │
│  │  └─────────────────────────────────────────────────────────────────────────────────────────┘  │
│  └─────────────────────────────────────────────────────────────────────────────────────────────┘  │
│                                                                                                 │
│  ┌─────────────────────────────────────────────────────────────────────────────────────────────┐  │
│  │                                 JOURNAL ENTRY ENTITY                                       │  │
│  │                                                                                             │  │
│  │  ┌─────────────────────────────────────────────────────────────────────────────────────────┐  │
│  │  │                         JournalEntry (Collection: journal_entries)                     │  │
│  │  │                                                                                         │  │
│  │  │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐  │  │
│  │  │  │     _id     │  │    title    │  │   content   │  │  dateTime   │  │ sentiment   │  │  │
│  │  │  │ (ObjectId)  │  │   (String)  │  │   (String)  │  │(LocalDateTime)│  │ (Sentiment) │  │  │
│  │  │  │   Primary   │  │   Optional  │  │   Required  │  │   Auto-set  │  │   Enum      │  │  │
│  │  │  │     Key     │  │             │  │             │  │             │  │             │  │  │
│  │  │  └─────────────┘  └─────────────┘  └─────────────┘  └─────────────┘  └─────────────┘  │  │
│  │  └─────────────────────────────────────────────────────────────────────────────────────────┘  │
│  └─────────────────────────────────────────────────────────────────────────────────────────────┘  │
│                                                                                                 │
│  ┌─────────────────────────────────────────────────────────────────────────────────────────────┐  │
│  │                              FREQUENTLY USED DATA ENTITY                                   │  │
│  │                                                                                             │  │
│  │  ┌─────────────────────────────────────────────────────────────────────────────────────────┐  │
│  │  │                    FrequentlyUsedData (Collection: frequently_used_data)                │  │
│  │  │                                                                                         │  │
│  │  │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐                                      │  │
│  │  │  │  objectId   │  │     key     │  │    value    │                                      │  │
│  │  │  │ (ObjectId)  │  │   (String)  │  │   (String)  │                                      │  │
│  │  │  │   Primary   │  │   Required  │  │   Required  │                                      │  │
│  │  │  │     Key     │  │             │  │             │                                      │  │
│  │  │  └─────────────┘  └─────────────┘  └─────────────┘                                      │  │
│  │  └─────────────────────────────────────────────────────────────────────────────────────────┘  │
│  └─────────────────────────────────────────────────────────────────────────────────────────────┘  │
│                                                                                                 │
│  ┌─────────────────────────────────────────────────────────────────────────────────────────────┐  │
│  │                                   SENTIMENT ENUM                                            │  │
│  │                                                                                             │  │
│  │  ┌─────────────────────────────────────────────────────────────────────────────────────────┐  │
│  │  │                              Sentiment Enum                                             │  │
│  │  │                                                                                         │  │
│  │  │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐                    │  │
│  │  │  │    HAPPY    │  │     SAD     │  │   ANXIOUS   │  │    ANGRY    │                    │  │
│  │  │  └─────────────┘  └─────────────┘  └─────────────┘  └─────────────┘                    │  │
│  │  └─────────────────────────────────────────────────────────────────────────────────────────┘  │
│  └─────────────────────────────────────────────────────────────────────────────────────────────┘  │
│                                                                                                 │
│  ┌─────────────────────────────────────────────────────────────────────────────────────────────┐  │
│  │                                   RELATIONSHIPS                                             │  │
│  │                                                                                             │  │
│  │  ┌─────────────────────────────────────────────────────────────────────────────────────────┐  │
│  │  │  User (1) ──────────────── (Many) JournalEntry                                         │  │
│  │  │  • One user can have multiple journal entries                                          │  │
│  │  │  • Each journal entry belongs to one user                                             │  │
│  │  │  • Relationship: @DBRef in User entity                                                 │  │
│  │  │  └─────────────────────────────────────────────────────────────────────────────────────────┘  │
│  │                                                                                             │  │
│  │  ┌─────────────────────────────────────────────────────────────────────────────────────────┐  │
│  │  │  JournalEntry (Many) ──────── (1) Sentiment                                            │  │
│  │  │  • Multiple journal entries can have the same sentiment                                │  │
│  │  │  • Each journal entry has exactly one sentiment                                        │  │
│  │  │  • Relationship: Enum field in JournalEntry entity                                     │  │
│  │  │  └─────────────────────────────────────────────────────────────────────────────────────────┘  │
│  └─────────────────────────────────────────────────────────────────────────────────────────────┘  │
│                                                                                                 │
└─────────────────────────────────────────────────────────────────────────────────────────────────┘
```

## Entity Descriptions

### 1. User Entity
- **Collection:** `users`
- **Purpose:** Stores user authentication and profile information
- **Key Attributes:**
  - `_id`: Primary key (ObjectId)
  - `username`: Unique identifier for login (indexed)
  - `password`: Encrypted password for authentication
  - `email`: User's email address for notifications
  - `sentimentAnalysis`: Boolean flag for sentiment analysis preference
  - `journalEntries`: List of references to user's journal entries
  - `roles`: List of user roles for authorization

### 2. JournalEntry Entity
- **Collection:** `journal_entries`
- **Purpose:** Stores individual journal entries with sentiment analysis
- **Key Attributes:**
  - `_id`: Primary key (ObjectId)
  - `title`: Optional title for the journal entry
  - `content`: The main content/text of the journal entry
  - `dateTime`: Timestamp when entry was created
  - `sentiment`: Emotional sentiment classification (enum)

### 3. FrequentlyUsedData Entity
- **Collection:** `frequently_used_data`
- **Purpose:** Caches frequently accessed application data
- **Key Attributes:**
  - `objectId`: Primary key (ObjectId)
  - `key`: Identifier for the cached data
  - `value`: The cached data value

### 4. Sentiment Enum
- **Purpose:** Defines possible sentiment classifications
- **Values:** HAPPY, SAD, ANXIOUS, ANGRY

## Relationships

### 1. User ↔ JournalEntry (One-to-Many)
- **Cardinality:** 1:N
- **Description:** One user can have multiple journal entries
- **Implementation:** @DBRef annotation in User entity
- **Business Rule:** Journal entries are always associated with a user

### 2. JournalEntry ↔ Sentiment (Many-to-One)
- **Cardinality:** N:1
- **Description:** Multiple journal entries can have the same sentiment
- **Implementation:** Enum field in JournalEntry entity
- **Business Rule:** Each journal entry must have exactly one sentiment

## Database Design Considerations

### MongoDB Features Used:
1. **Document-based storage** for flexible schema
2. **ObjectId** for primary keys
3. **@DBRef** for document references
4. **Indexing** on username field for performance
5. **Embedded documents** for simple relationships

### Data Integrity:
1. **Unique constraints** on username
2. **Required fields** validation
3. **Enum validation** for sentiment values
4. **Referential integrity** through @DBRef

### Performance Optimizations:
1. **Indexed username** for fast user lookups
2. **Embedded journal entries** in user documents
3. **Caching layer** with Redis for frequently accessed data
4. **Auto-index creation** enabled 