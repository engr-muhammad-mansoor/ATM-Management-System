# 🏦 ATM Management System

A comprehensive RESTful ATM Management System built with Spring Boot, providing secure banking operations including authentication, balance inquiry, cash transactions, and PIN management.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Database Configuration](#database-configuration)
- [API Documentation](#api-documentation)
- [Project Structure](#project-structure)
- [Key Components](#key-components)
- [Usage Examples](#usage-examples)
- [Transaction Limits](#transaction-limits)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## 🎯 Overview

This ATM Management System is a full-featured backend application that simulates real-world ATM operations. It provides secure user authentication, account management, transaction processing, and maintains a complete audit trail of all banking operations.

The system follows industry-standard design patterns and best practices, implementing a clean architecture with separation of concerns between controllers, services, and data access layers.

## ✨ Features

### 🔐 Authentication & Security
- **Card-based Login**: Secure authentication using card number and PIN
- **Session Management**: Singleton pattern for maintaining user session
- **PIN Update**: Secure PIN modification with automatic logout after update

### 💰 Account Operations
- **Balance Inquiry**: Real-time account balance checking
- **Cash Withdrawal**: Withdraw funds with transaction and daily limits
- **Cash Deposit**: Deposit money into account with transaction limits
- **Transaction History**: Complete audit trail of all transactions

### 🛡️ Business Rules & Validation
- Transaction amount validation
- Daily withdrawal limits
- Deposit limits per transaction
- Insufficient funds checking
- Session validation for all operations

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.0.5
- **Language**: Java 20
- **Database**: MySQL 8.0 (with H2 for testing)
- **ORM**: Spring Data JPA / Hibernate
- **Build Tool**: Maven
- **API Documentation**: SpringDoc OpenAPI (Swagger UI)
- **View Technology**: Thymeleaf
- **Utilities**: Lombok
- **Actuator**: Spring Boot Actuator for monitoring

## 🏗️ Architecture

The application follows a **layered architecture** pattern:

```
┌─────────────────────────────────────┐
│         Controllers Layer           │  ← REST API Endpoints
├─────────────────────────────────────┤
│         Services Layer              │  ← Business Logic
├─────────────────────────────────────┤
│         Repositories Layer          │  ← Data Access
├─────────────────────────────────────┤
│         Entities Layer              │  ← Database Models
└─────────────────────────────────────┘
```

### Design Patterns Used
- **MVC (Model-View-Controller)**: Separation of concerns
- **Singleton Pattern**: Login session management
- **Repository Pattern**: Data access abstraction
- **Dependency Injection**: Spring's IoC container

## 📦 Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK)**: Version 20 or higher
- **Maven**: Version 3.6+ 
- **MySQL**: Version 8.0 or higher
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code (recommended)
- **Git**: For version control

## 🚀 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/ATM-Management-System.git
cd ATM-Management-System
```

### 2. Database Setup

Create a MySQL database for the application:

```sql
CREATE DATABASE atm;
```

### 3. Configure Application Properties

Update `src/main/resources/application.properties` with your database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/atm
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### 4. Build the Project

```bash
mvn clean install
```

### 5. Run the Application

```bash
mvn spring-boot:run
```

Or run the `AtmManagementSystemApplication.java` class directly from your IDE.

The application will start on `http://localhost:8080`

### 6. Access API Documentation

Once the application is running, access Swagger UI at:
```
http://localhost:8080/swagger-ui/index.html
```

## 🗄️ Database Configuration

The application uses **JPA/Hibernate** with automatic schema generation. Tables are created automatically based on entity definitions.

### Entity Relationships

- **Customer** (1) → (Many) **Account**
- **Account** (Many) → (1) **Customer**
- **Account** (1) → (1) **AccountType**
- **Account** (1) → (1) **CardType**
- **Account** (1) → (Many) **Transaction**
- **Transaction** (Many) → (1) **TransactionType**

### Required Transaction Types

Make sure your database has the following transaction types:
- ID 1: Withdrawal
- ID 2: Balance Inquiry
- ID 3: Deposit
- ID 4: PIN Update

## 📚 API Documentation

All endpoints are prefixed with `/atm`

### Authentication

#### Login
```http
GET /atm/login/{cardNumber}/{cardPin}
```

**Parameters:**
- `cardNumber` (Long): Card number
- `cardPin` (Long): PIN number

**Response:**
- `200 OK`: "Welcome you are Logged in"
- Invalid credentials: "Invalid Credentials"

**Example:**
```bash
curl http://localhost:8080/atm/login/1234567890123456/1234
```

### Account Operations

#### Balance Inquiry
```http
GET /atm/balance-inquiry
```

**Response:**
- `200 OK`: "Available Balance = {amount}"
- Not logged in: "Please Login to continue"

**Example:**
```bash
curl http://localhost:8080/atm/balance-inquiry
```

#### Cash Withdrawal
```http
PUT /atm/withdraw-cash/{amount}
```

**Parameters:**
- `amount` (Long): Amount to withdraw

**Response:**
- `200 OK`: "{amount} withdrawan successfully"
- Not logged in: "Please Login to continue"
- Insufficient funds: "Not enough funds"
- Transaction limit exceeded: "Withdrawal Limit exceeds for one transaction"
- Daily limit exceeded: "Daily Transaction Limit exceeds"

**Limits:**
- Maximum per transaction: ₹25,000
- Maximum daily: ₹50,000

**Example:**
```bash
curl -X PUT http://localhost:8080/atm/withdraw-cash/5000
```

#### Cash Deposit
```http
PUT /atm/deposit-cash/{amount}
```

**Parameters:**
- `amount` (Long): Amount to deposit

**Response:**
- `200 OK`: "{amount} deposited successfully"
- Not logged in: "Please Login to continue"
- Limit exceeded: "Deposit Limit exceeds for one transaction"

**Limits:**
- Maximum per transaction: ₹200,000

**Example:**
```bash
curl -X PUT http://localhost:8080/atm/deposit-cash/10000
```

#### Update PIN
```http
PUT /atm/update-pin/{pin}
```

**Parameters:**
- `pin` (int): New PIN (4 digits)

**Response:**
- `200 OK`: "Pin updated successfully"
- Not logged in: "Please Login to continue"
- Same PIN: "Pin already exists"

**Note:** User is automatically logged out after PIN update.

**Example:**
```bash
curl -X PUT http://localhost:8080/atm/update-pin/5678
```

## 📁 Project Structure

```
ATM-Management-System/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── TechLiftProject/ATMManagementSystem/
│   │   │       ├── Controllers/          # REST API Controllers
│   │   │       │   ├── LoginController.java
│   │   │       │   ├── BalanceCheckController.java
│   │   │       │   ├── CashDepositController.java
│   │   │       │   ├── CashWithdrawalController.java
│   │   │       │   └── UpdatePinController.java
│   │   │       ├── Services/             # Business Logic Layer
│   │   │       │   ├── LoginService.java
│   │   │       │   ├── BalanceCheckService.java
│   │   │       │   ├── CashDepositService.java
│   │   │       │   ├── CashWithdrawalService.java
│   │   │       │   ├── UpdatePinService.java
│   │   │       │   ├── AccountDataService.java
│   │   │       │   └── TransactionService.java
│   │   │       ├── Repositories/         # Data Access Layer
│   │   │       │   ├── AccountRepository.java
│   │   │       │   ├── CustomerRepository.java
│   │   │       │   ├── TransactionRepository.java
│   │   │       │   └── ...
│   │   │       ├── Entities/             # JPA Entities
│   │   │       │   ├── Account.java
│   │   │       │   ├── Customer.java
│   │   │       │   ├── Transaction.java
│   │   │       │   ├── AccountType.java
│   │   │       │   ├── CardType.java
│   │   │       │   └── TransactionType.java
│   │   │       ├── Models/               # Data Models
│   │   │       │   ├── Login.java
│   │   │       │   └── ModelData.java
│   │   │       └── AtmManagementSystemApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/                              # Unit Tests
│       └── java/
│           └── TechLiftProject/ATMManagementSystem/
├── pom.xml                                # Maven Configuration
└── README.md
```

## 🔑 Key Components

### Controllers
- **LoginController**: Handles user authentication
- **BalanceCheckController**: Manages balance inquiry requests
- **CashDepositController**: Processes cash deposits
- **CashWithdrawalController**: Handles cash withdrawals
- **UpdatePinController**: Manages PIN updates

### Services
- **LoginService**: Authentication and session management
- **BalanceCheckService**: Balance retrieval logic
- **CashDepositService**: Deposit processing with validation
- **CashWithdrawalService**: Withdrawal processing with limits
- **UpdatePinService**: PIN update with security measures
- **TransactionService**: Transaction recording and history
- **AccountDataService**: Account data management

### Entities
- **Account**: Core account entity with relationships
- **Customer**: Customer information
- **Transaction**: Transaction records
- **AccountType**: Account type classifications
- **CardType**: Card type definitions
- **TransactionType**: Transaction type classifications

## 💡 Usage Examples

### Complete Workflow

1. **Login**
```bash
GET /atm/login/1234567890123456/1234
# Response: "Welcome you are Logged in"
```

2. **Check Balance**
```bash
GET /atm/balance-inquiry
# Response: "Available Balance = 50000"
```

3. **Withdraw Cash**
```bash
PUT /atm/withdraw-cash/10000
# Response: "10000 withdrawan successfully"
```

4. **Deposit Cash**
```bash
PUT /atm/deposit-cash/5000
# Response: "5000 deposited successfully"
```

5. **Update PIN**
```bash
PUT /atm/update-pin/5678
# Response: "Pin updated successfully"
# Note: Automatically logged out after PIN update
```

## ⚠️ Transaction Limits

| Operation | Per Transaction Limit | Daily Limit |
|-----------|----------------------|-------------|
| **Withdrawal** | ₹25,000 | ₹50,000 |
| **Deposit** | ₹200,000 | No limit |

## 🧪 Testing

The project includes unit tests. Run tests using:

```bash
mvn test
```

Test files are located in `src/test/java/`

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is part of the TechLift Project portfolio. 

## 👨‍💻 Author

**TechLift Project**
- GitHub: [@yourusername](https://github.com/yourusername)

## 🙏 Acknowledgments

- Built as part of TechLift training program
- Spring Boot community for excellent documentation
- All contributors and testers

---

⭐ If you found this project helpful, please consider giving it a star!

