
<h1 align="center">Bank Management System</h1>



**_Project Overview_**

The Bank Management System is a full-stack web application built using Spring Boot that allows users to perform essential banking operations such as creating an account, depositing, withdrawing, closing accounts, and viewing account details.
Each user can create only one account, ensuring better control and data consistency.
The system uses MySQL as the database and features an AI-enhanced responsive frontend for a modern user experience.

<hr style="border-top: 1px solid #ccc; height: 0; margin: 10px 0;">


**_Features_**

1. User registration and login authentication
2. One-account-per-user restriction
3. Create, deposit, withdraw, and close account operations
4. Dashboard displaying user account details and balance
5. Responsive UI built using Thymeleaf and CSS
6. Secure session management for authenticated users
7. CRUD operations handled via Spring Data JPA and REST APIs
8. Real-time feedback messages for user actions


**_Technologies Used_**

- Java
- Spring Boot
- Spring Data JPA
- Spring Web MVC
- REST API
- Thymeleaf
- HTML and CSS
- MySQL (Database)
- Maven (Build tool)
- Eclipse IDE


**_Project Structure_**
  
```
BankManagementSystem/
│
├── src/
│   ├── main/
│   │   ├── java/in/bank/main/
│   │   │   ├── BankManagementSystemApplication.java       # Main Spring Boot entry point
│   │   │   ├── controller/
│   │   │   │   ├── AccountController.java                 # Handles account operations
│   │   │   │   ├── AuthController.java                    # Manages registration and login
│   │   │   ├── entity/
│   │   │   │   ├── User.java                              # User entity class
│   │   │   │   ├── Account.java                           # Account entity class
│   │   │   ├── repository/
│   │   │   │   ├── UserRepository.java                    # User database operations
│   │   │   │   ├── AccountRepository.java                 # Account database operations
│   │   │   ├── service/
│   │   │   │   ├── AccountService.java                    # Account service interface
│   │   │   │   ├── AccountServiceImpl.java                # Implements business logic
│   │   │   │   ├── AuthService.java                       # Authentication service
│   │   │
│   │   └── resources/
│   │       ├── static/css/                                # CSS stylesheets
│   │       │   ├── auth.css
│   │       │   ├── style.css
│   │       ├── templates/                                 # Thymeleaf templates
│   │       │   ├── login.html
│   │       │   ├── register.html
│   │       │   ├── dashboard.html
│   │       │   ├── createAccount.html
│   │       │   ├── deposit.html
│   │       │   ├── withdraw.html
│   │       └── application.properties                     # Database configuration
│   │
│   └── test/                                               # Unit and integration tests
│
├── pom.xml                                                 # Maven dependencies
└── README.md                                               # Project documentation
```


**_How It Works_**

- The user registers through a sign-up form. Details are securely stored in the MySQL database.
- After successful registration, the user logs in using valid credentials.
- On the first login, the dashboard shows the Create Account option if the user doesn’t have an account yet.
- Once an account is created, the Create Account option is hidden, and available operations include Deposit, Withdraw, and Close Account.
- All actions (like deposit or withdraw) automatically update the database in real time.
- The Close Account feature allows the user to permanently delete their account record.
- Spring Data JPA handles all CRUD operations efficiently, and data validation is managed through service and controller layers.


**_Database Schema_**

Database Name: `bank_project`

Table: users

| Column Name | Data Type            | Description        |
| ----------- | -------------------- | ------------------ |
| id          | bigint (Primary Key) | Unique user ID     |
| username    | varchar(255)         | User’s login name  |
| password    | varchar(255)         | Encrypted password |

Table: accounts

| Column Name         | Data Type            | Description                         |
| ------------------- | -------------------- | ----------------------------------- |
| account_number      | bigint (Primary Key) | Unique account number               |
| account_holder_name | varchar(255)         | Name of the account holder          |
| account_balance     | double               | Current balance of the account      |
| user_id             | bigint (Foreign Key) | References user id from users table |

**_Relationship_**:
One-to-One relationship between `users` and `accounts`
Each user can have only one account, linked `via user_id`.


**_Frontend Description_**

- The frontend is developed using Thymeleaf, HTML, and CSS, with AI-assisted responsive design techniques.
- The interface automatically adapts to different devices and screen sizes, providing a smooth and intuitive user experience.
- Dynamic Thymeleaf expressions are used to show or hide options (like Create Account) based on user status.
- Each page, including deposit, withdraw, and close account, has clear feedback messages for successful or failed actions.


**_Future Enhancements_**

1. Implement a transaction history table to log deposits, withdrawals, and closed accounts.
2. Add different account types such as Savings and Current.
3. Develop an Admin panel to manage users and their accounts.
4. Add password reset functionality with email verification.
5. Expose REST API endpoints for mobile or third-party applications.
6. Integrate Spring Boot Security for advanced authentication and authorization.
7. Extend the system into Microservices architecture for scalability and modularity.
