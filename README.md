# Java CRUD Application with PostgreSQL and JWT Authentication

This is a Java-based CRUD application that uses PostgreSQL as its database. The application provides functionality to manage user details, including creating, updating, and deleting users. It also implements password encoding and JWT-based authentication.

## Features

1. **User Management**
   - Create user details
   - Update user details
   - Delete user details

2. **Password Security**
   - Passwords are securely encoded before storing them in the database.

3. **Authentication**
   - Validates user login credentials to ensure authentication is successful.

4. **JWT Integration**
   - **Token Generation:** Generates a JWT token for the user upon successful authentication.
   - **Token Validation:** Validates the client token at the backend before processing requests (In Progress).
   - **Token Encryption:** Uses SHA-256 algorithm to encrypt the token before sending it to the user.

## Technologies Used

- Java
- Spring Boot
- PostgreSQL
- Spring Security
- JWT (JSON Web Tokens)
- Maven

## Getting Started

### Prerequisites

- Java JDK 11 or higher
- PostgreSQL Database
- Maven

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/your-repo-name.git

