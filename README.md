## Service Order Management System - Backend

Development of a REST API using Java with Spring Boot 4.22.0 and MySQL database. The project employs JPA with Hibernate for data validation, exception handling, and proper use of the HTTP protocol in the REST standard.

### Table of Contents
- [About](#about)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgments](#acknowledgments)

### About
This is a web application implementing a CRUD (Create, Read, Update, and Delete) system for simple Service Order Management.

### Features
- REST API developed using the **Spring Boot Framework 4.22.0**.
- Angular framework used to test the API.
- Deployment of the REST API to the cloud via the Heroku platform.
- Integration between the API and frontend, both hosted in the cloud.

### Installation
To set up the project locally, download the necessary tools:
- [Spring Tools Suite](https://spring.io/tools)
- [Java JDK 11 LTS](https://www.oracle.com/java/technologies/javase-downloads.html)
- [VSCode](https://code.visualstudio.com/download)
- [Heroku Account](https://www.heroku.com/)
- [Postman](https://www.postman.com/downloads/)

### Usage
To run the project:
1. Clone the repository.
2. Import the project into your workspace using Spring Boot.

A brief overview of the project structure:

- **Config:** Handles application configuration, including database connections and security settings.
- **Domain:** Contains domain models and interfaces.
- **DTO:** Data Transfer Objects used for communication between the frontend and backend.
- **Enums:** Defines enumeration types and auxiliary tables.
- **Repositories:** Manages database interactions, processes requests from services, and returns data.
- **Resources:** Components that handle incoming requests from the frontend and pass them to the relevant services.
- **Security:** Contains security configurations and protocols.
- **Services:** Implements business logic, processes requests from resources, and interacts with the database.

### Contributing
I welcome contributions to this project! If you want to contribute, here are a few ways to get involved:

- **Reporting Bugs:** If you encounter bugs or issues, feel free to open an issue on our GitHub repository. Please provide as much detail as possible to help us resolve the problem quickly.
- **Suggesting Features:** Have ideas for new features or improvements? Open an issue and share your suggestions with us. We appreciate your feedback and creativity!
- **Submitting Pull Requests:** To contribute code, fork the repository, make your changes, and submit a pull request. We will review your changes and merge them if they align with the project's objectives.

For more information on how to contribute, refer to our Contribution Guidelines.

### License
This project is not licensed and is public domain. Feel free to use it as you wish.

### Acknowledgments
I would like to express my gratitude to **Vandir Cezar**, whose course on the Udemy platform provided me with valuable knowledge and insights. This project wouldn't have been possible without the comprehensive training and guidance provided by Vandir. Thank you for your dedication and expertise.
