Tadiwanashe Kapfidze
H230762X

This Java application implements a user registration form and a login form using Swing components for the graphical user interface (GUI) and JDBC for database interaction

Registration Form
Users can input their information to create a new account, which is then stored in a MySQL database.
RegistrationForm Class: Represents a dialog box for user registration, containing fields for name, registration number, email, program, gender, password, and confirm password.

Database Interaction:
Connects to a MySQL database to insert new user records.
Uses JDBC to execute parameterized queries for secure database operations.
User Class: Represents a user object with attributes like name, registration number, email, program, gender, and password.

Features:
User Registration: Allows users to enter their details and register for a new account.
Validation Checks:
Ensures all fields are filled before registration.
Verifies that the password and confirm password fields match.
Database Insertion:
Inserts user data into the database upon successful registration.
Feedback:
Provides user feedback through message dialogs for successful registration or errors.

Login Form

Key Features:
User Authentication: Allows users to enter their email and password to log in.
Database Interaction: Queries a MySQL database to verify user credentials.
Security Measures:
Uses parameterized queries to prevent SQL injection attacks.
Handles passwords securely by converting them to character arrays.
Modular Design: Encapsulates database operations in a separate method for better code organization.

Project Components:
LoginForm Class: Represents a dialog box for user login, with fields for form elements and user information.
Database Connection: Connects to a MySQL database to authenticate users.
Main Method: Instantiates the login form and retrieves the authenticated user.

Security aspects achieved by the project
Parameterized Queries:
The code uses a prepared statement with placeholders for the email and password in the SQL query to prevent SQL injection attacks. This approach helps sanitize user input and prevents malicious SQL queries.

Secure Password Handling:
The code retrieves the password as a character array (pfPassword.getPassword()) and converts it to a string using String.valueOf() to avoid storing passwords as plain text. Storing passwords as char arrays instead of strings can provide better security as char arrays can be cleared after use.

Connection Security:
The database connection credentials (username and password) are stored within the code. In a production environment, it is recommended to store these credentials securely, such as using environment variables or a secure configuration mechanism.

Limited Error Exposure:
The code catches exceptions that may occur during database operations and prints the stack trace. While printing stack traces can aid in debugging, in a production environment, it's better to log these exceptions securely without exposing sensitive information to end-users.

Secure Data Retrieval:
User data is retrieved from the database based on the provided email and password. By validating user credentials against stored data in the database, the code ensures that only authorized users can access the system.

Modular Design:
The code separates database interaction logic into a separate method (getAuthenticatedUser) to encapsulate database operations. This modular design helps maintain code cleanliness and potentially reduces security vulnerabilities by isolating sensitive database operations.
Secure Disposal of Resources:
The code closes the database connection and statement objects in a 'finally' block to ensure that database resources are properly released, even in the event of an exception. Proper resource management helps prevent resource leaks and potential security vulnerabilities.
 
Registration Details saved in the Database
User 1
Reg Number - H230763Z
Name - Tinotenda
Email - tinotenda@gmail.com
Gender - Male
Program - Financial Engineering
Password - TINO05

User 2
Reg Number - H230762X
Name - Tadiwa Kapfidze
Email - kapfidzetadiwanashe@gmail.com
Gender - Female
Program - Information Security and Assurance
Password - TADIWA04

PS: You can only use existing details to login

