# GEVS Voting System

The GEVS (General Election Voting System) is a web application developed using Spring MVC, Thymeleaf, Spring Security, Spring Boot, and MySQL Database.

## Getting Started

### Prerequisites

Before running the application, make sure you have the following installed:

- Java Development Kit (JDK) 8 or later
- MySQL Database
- Maven
- Intellij Idea

### Database Setup

1. Import the `gevs.sql` file into your MySQL Database to set up the required tables and data.


2. Verify that the database connection details in the `application.properties` file are correct.

  ```bash
   spring.datasource.username=root
    spring.datasource.password= 
```
Change the username and password accordingly.

### Build and Run

1. Open Intellij Idea

2. Navigate to the project directory. And Open project.

3. Build and run the application using Maven.

    ```bash
    mvn spring-boot:run
    ```

4. Open a web browser and go to [http://localhost:8080](http://localhost:8080) to access the GEVS Voting System.

### Default Credentials used in application.properties file

  - Username: root
  - Password: 

## Features
- Home Page
- Voter Login and Register
- ELection Commission Login
- List of Candidates to vote (Voter Dashboard)
- Secure Voting Process (if the voter has already voted, he wont be able to do so again until     election ends and starts again)
- Election Votes real time Monitoring(Election Commission Dashboard)
- Election Result will be shown when election ends 

## Technologies Used

- Spring MVC
- Thymeleaf
- Spring Security
- Spring Boot
- MySQL Database


