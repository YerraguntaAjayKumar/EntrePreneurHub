# EntrepreneurHub - DBMS Project

A comprehensive role-based collaboration platform that connects **entrepreneurs** with **investors** to foster business growth and innovation.

## 📋 Project Overview

**EntrepreneurHub** is a desktop application built with Java Swing that provides a complete ecosystem for:
- **Entrepreneurs** to pitch and manage their business ideas
- **Investors** to discover and evaluate investment opportunities
- **Administrators** to manage users and oversee platform operations
- **Real-time communication** between all parties through an integrated chat system

## 🏗️ Architecture

The project follows a **3-tier architecture**:

```
┌─────────────────────────────────────┐
│    UI Layer (Swing GUI)             │
│  LoginUI, EntrepreneurUI, ChatUI    │
├─────────────────────────────────────┤
│    Service Layer (Business Logic)   │
│  AuthService, IdeaService, etc.     │
├─────────────────────────────────────┤
│    Data Access Layer (DAO)          │
│  UserDAO, IdeaDAO, ProjectDAO       │
├─────────────────────────────────────┤
│    Database (Oracle XEPDB1)         │
└─────────────────────────────────────┘
```

## 📁 Project Structure

```
src/
├── Main.java                    # Application entry point
├── models/                      # Entity classes
│   ├── User.java               # User entity with roles
│   ├── Idea.java               # Business idea entity
│   ├── Project.java            # Project entity
│   ├── Agreement.java          # Contract/agreement entity
│   └── Message.java            # Message entity
├── dao/                        # Data Access Objects
│   ├── UserDAO.java            # User database operations
│   ├── IdeaDAO.java            # Idea database operations
│   ├── ProjectDAO.java         # Project database operations
│   ├── AgreementDAO.java       # Agreement database operations
│   └── MessageDAO.java         # Message database operations
├── service/                    # Business logic layer
│   ├── AuthService.java        # Authentication & authorization
│   ├── IdeaService.java        # Idea management logic
│   ├── ProjectService.java     # Project management logic
│   └── AgreementService.java   # Agreement processing
├── ui/                         # Swing GUI components
│   ├── LoginUI.java            # Login screen
│   ├── RegisterUI.java         # User registration
│   ├── EntrepreneurUI.java     # Entrepreneur dashboard
│   ├── InvestorUI.java         # Investor dashboard
│   ├── AdminUI.java            # Admin panel
│   ├── ChatUI.java             # Chat interface
│   └── components/             # Reusable UI components
│       ├── Header.java         # Dashboard header
│       ├── Sidebar.java        # Navigation sidebar
│       └── TableView.java      # Data table component
└── db/
    └── DBConnection.java       # Oracle database connection

sql/
├── schema.sql                  # Database schema definition
└── test_data.sql              # Sample test data

lib/
└── ojdbc8.jar                 # Oracle JDBC driver

bin/                           # Compiled bytecode (generated)
```

## 🗄️ Database Schema

### Tables

| Table | Purpose |
|-------|---------|
| `users` | User accounts with roles (ENTREPRENEUR, INVESTOR, ADMIN) and approval status |
| `idea` | Business ideas submitted by entrepreneurs |
| `project` | Projects linking ideas with investor involvement |
| `agreement` | Contracts and agreements between parties |
| `messages` | Real-time communication records |

### Database Connection
- **Type**: Oracle Database
- **Server**: localhost:1521
- **Database**: XEPDB1
- **User**: system
- **Driver**: Oracle JDBC 8 (ojdbc8.jar)

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK 8 or higher)
- Oracle Database (XEPDB1) running locally on port 1521
- Oracle JDBC driver (ojdbc8.jar) - included in `lib/` folder

### Build Instructions

1. **Compile the project**:
   ```bash
   cd "path/to/EntrepreneurHub"
   javac -d bin -cp lib/ojdbc8.jar $(find src -name "*.java")
   ```

   Or use the VS Code Build task:
   ```
   Ctrl+Shift+B → Select "Build EntrepreneurHub"
   ```

2. **Run the application**:
   ```bash
   java -cp bin;lib/ojdbc8.jar Main
   ```

   Or use the VS Code Run task:
   ```
   Ctrl+Shift+B → Select "Run EntrepreneurHub"
   ```

## 👥 User Roles & Features

### 1. **Entrepreneur** 👨‍💼
- Create and manage business ideas
- View interested investors
- Negotiate project terms
- Track project progress
- Real-time chat with investors

### 2. **Investor** 💰
- Discover business ideas
- Evaluate opportunities
- Fund promising projects
- Monitor investments
- Communicate with entrepreneurs

### 3. **Admin** 🛡️
- User account management
- Platform oversight
- Approve/reject new users
- Monitor all transactions

### 4. **Guest** 🔓
- View login/registration screens
- Access is restricted until account approval

## 🔒 Authentication Flow

1. **Registration**: New user submits registration form
2. **Pending Approval**: Admin reviews user credentials
3. **Approved**: User can login (only APPROVED users)
4. **Role-Based Access**: Dashboard changes based on user role
5. **Session Management**: User logout clears session

## 💬 Communication Features

- **Real-time Chat**: Direct messaging between entrepreneurs and investors
- **Message History**: All conversations are persisted in database
- **Notification System**: Users notified of new messages

## 🎯 Core Features

- **Idea Management**: Create, edit, list, and delete business ideas
- **Project Management**: Link ideas with investors and manage projects
- **Agreement Handling**: Create and track contracts between parties
- **User Management**: Register, approve, and manage user accounts
- **Chat System**: Real-time communication platform
- **Status Tracking**: Monitor idea and project statuses

## 🔧 Configuration

### VS Code Tasks
Two pre-configured tasks are available in `.vscode/tasks.json`:

1. **Build EntrepreneurHub** - Compiles all Java sources to `bin/` directory
2. **Run EntrepreneurHub** - Executes the compiled application

### Database Configuration
Update connection details in `src/db/DBConnection.java`:
```java
private static final String URL = "jdbc:oracle:thin:@localhost:1521:XEPDB1";
private static final String USER = "system";
private static final String PASSWORD = "oracle";
```

## 📦 Dependencies

- **Java Swing** - GUI framework
- **Oracle JDBC 8** - Database connectivity
- **Oracle Database** - Data persistence

## ✅ Testing

Run the application and test the following workflows:

1. **User Registration & Login**
   - Register new user → Get admin approval → Login

2. **Idea Management**
   - Create idea → View ideas → Edit/Delete ideas

3. **Project Creation**
   - Match idea with investor → Create project → Track status

4. **Communication**
   - Send messages → View chat history

## 📝 Git Repository

- **Repository**: https://github.com/YerraguntaAjayKumar/EntrePreneurHub
- **Branch**: main
- **Build Status**: ✅ Compiles successfully

## 🐛 Troubleshooting

### Compilation Errors
- Ensure all `.java` files are in the `src/` directory
- Verify Oracle JDBC driver is in `lib/` folder
- Check Java version compatibility

### Runtime Issues
- Verify Oracle Database is running on localhost:1521
- Confirm database credentials in `DBConnection.java`
- Check database schema is properly created

### ClassNotFoundException
- Rebuild the project: `javac -d bin ...`
- Verify classpath includes `bin/` and `lib/ojdbc8.jar`

## 👨‍💻 Development Team

**Project**: EntrepreneurHub - DBMS Collaborative Platform  
**Institution**: VASAVI - IT4 DBMS Course  
**Year**: 2026

## 📄 License

This project is developed as part of academic coursework.

## 🎓 Learning Outcomes

- Database Design & Normalization
- JDBC Programming
- 3-tier Architecture Implementation
- GUI Development with Java Swing
- Transaction Management
- Data Persistence Patterns
