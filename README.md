
<h1 align="center">Bank Management System</h1>



**_Project Overview_**

The Bank Management System is a full-stack web application built using Spring Boot that allows users to manage their banking activities such as creating an account, depositing, withdrawing, and viewing account details. Each user can create only one account, ensuring data consistency and control. The system uses MySQL as a database and integrates AI-enhanced responsive frontend design for a better user experience.



**_Features_**

1. User registration and login authentication
2. One account per user restriction logic
3. Create, deposit, and withdraw operations
4. Dashboard showing account balance and details
5. Interactive and responsive web UI built using Thymeleaf and CSS
6. Backend logic developed using Spring Data JPA and REST API
7. Secure session-based login system
8. Automatic validation and error handling


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
