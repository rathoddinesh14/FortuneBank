# Fortune Bank - where money matters

Welcome to the Fortune Bank! This application is designed to provide users with a comprehensive banking experience, including account management, transactions, beneficiary management, and more. Below, you'll find information on setting up and using this application.

## Table of Contents
1. [Introduction](#introduction)
2. [Features](#features)
3. [Installation](#installation)
4. [Usage](#usage)
5. [Contributing](#contributing)
6. [License](#license)

## Introduction

This full-stack banking application aims to provide a seamless and secure banking experience to both users and administrators. Users can perform various tasks such as creating accounts, managing beneficiaries, making transactions, and viewing their account summaries. Administrators have access to a dashboard for user management and system monitoring.

## Features

### 1. User Application Online
- Users can create new accounts online by providing their personal information.

### 2. User Netbanking Registration
- Registered users can enable net banking for their accounts to access online banking services.

### 3. User Login
- Users can securely log in to their accounts using their credentials.

### 4. Account Summary
- Users can view their account summaries, including transaction history, between two specified dates.

### 5. Add/Remove Beneficiaries
- Users can manage their list of beneficiaries by adding or removing them.

### 6. Transfer
- Users can perform fund transfers using various modes, including NEFT (National Electronic Funds Transfer), IMPS (Immediate Payment Service), and RTGS (Real-Time Gross Settlement).

### 7. View Transactions
- Users can view their transaction history and details for reference.

### 8. Deposit/Withdraw
- Users can deposit money into their accounts or make withdrawals as needed.

### 9. Forgot UserID and Password
- Users who forget their UserIDs or passwords can initiate a recovery process to regain access to their accounts.

### 10. Admin Dashboard
- Administrators can access a dashboard for managing user accounts, monitoring system health, and overseeing transactions.

### 11. User Dashboard
- Users have a personalized dashboard where they can access their account information and perform banking operations.

### 12. View Beneficiaries
- Users can view their added beneficiaries for quick and easy fund transfers.

## Installation

To install and run the Full Stack Banking Application, follow these steps:

1. Clone the repository from [GitHub](https://github.com/rathoddinesh14/FortuneBank.git).

2. Navigate to the project directory in your terminal.

3. Install the required dependencies by running:
   ```bash
   npm install
   ```

4. Set up a database to store user and transaction data.

5. Configure the database connection in the application by updating the database configuration files.

6. Start the application by running:
   ```bash
   npm start
   ```

7. Access the application via a web browser at `http://localhost:3000` (or the specified port).

8. Start the Spring Boot application.

## Usage

### Importing a MySQL Database

To import a MySQL database dump, use the following command:

```sh
mysql -u [username] -p [database_name] < [dump_file].sql
```

- Replace `[username]` with your MySQL username.
- Replace `[database_name]` with the name of the database you want to import into.
- Replace `[dump_file]` with the filename of the dump file.

**Example:**

```sh
mysql -u root -p mydatabase < mydatabase_dump.sql
```

1. Users can access the application by signing up for an account or logging in if they already have one.

2. After logging in, users can perform various banking operations such as viewing account summaries, adding/removing beneficiaries, making transactions, and more.

3. Administrators can log in to the admin dashboard to manage user accounts and monitor system health.

4. Users and administrators can recover their UserIDs or passwords if they forget them.

## Contributing

We welcome contributions from the community to improve and expand this banking application. If you'd like to contribute, please follow our [Contribution Guidelines](CONTRIBUTING.md).

## License

This Full Stack Banking Application is licensed under the [GNU General Public License, version 3](LICENSE).

---

Thank you for using the Full Stack Banking Application. We hope it provides a secure and efficient banking experience for both users and administrators. If you encounter any issues or have suggestions for improvement, please don't hesitate to reach out to our development team.