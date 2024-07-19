# Authentication API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

This project is an API built using **Java, Java Spring, Flyway Migrations, MySQL Workbench as the database, and Spring Security and JWT for authentication control.**

The API was developed based on [Youtube Tutorial](https://www.youtube.com/watch?v=5w-YCcOjPD0), to demonstrate how to configure Authentication and Authorization in Spring application using Spring Security.

## Table of Contents

- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [API Testing](#api-testing)
- [Authentication](#authentication)
- [Database](#database)
- [Contributing](#contributing)

## Installation

1. Clone the repository:

```bash
https://github.com/murilopbc/auth-backend.git
```

2. Install dependencies with Maven

3. Install [MySQL](https://dev.mysql.com/downloads/installer/)

## Usage

1. Start the application with Maven
2. The API will be accessible at http://localhost:8080


## API Endpoints
The API provides the following endpoints:

```markdown
POST /product - Register a new product (ADMIN access required).

GET /product - Retrieve a list of all products. (all authenticated users)

GET /product/{id} - Retrieve the details of a product by id. (all authenticated users)

PUT /product - Update information of a product. (ADMIN access required)

DELETE /product/{id} - Delete a product by id (ADMIN access required).

DELETE /product/inativar/{id} - Inactivate a product by id. (ADMIN access required).

PUT /product/ativar/{id} - Activate a product by id. (ADMIN access required).

POST /auth/login - Login into the App

POST /auth/register - Register a new user into the App

GET /auth/users - Get all users into the App
```

## API Testing
I recommend you to use [Postman](https://www.postman.com/downloads/) or [Insomnia](https://insomnia.rest/download)  to project, build, test and collaborate your application.


## Authentication
The API uses Spring Security for authentication control. The following roles are available:

```
USER -> Standard user role for logged-in users.
ADMIN -> Admin role for managing partners (registering new partners).
```
To access protected endpoints as an ADMIN user, provide the appropriate authentication credentials in the request header.

## Database
The project utilizes [MySQL](https://www.mysql.com/) as the database. The necessary database migrations are managed using Flyway.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request to the repository.

When contributing to this project, please follow the existing code style, [commit conventions](https://github.com/iuricode/padroes-de-commits), and submit your changes in a separate branch.




