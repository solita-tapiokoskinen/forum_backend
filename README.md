# forum_backend
This is part of the DevAcademy practical assignment, the a Spring Boot REST API for managing users, topics and comments.

## Dependencies

- Spring boot - starter-jpa, starter-web
- Spring security
- jsonwebtoken
- Mariadb jdbc
- H2Database
- Junit


## Accesspoints

All accesspoints start with localhost:8080/api
<ul>
  <li>/auth/register - Methods: POST</li>
  body { "username": username, "email": email, "password": password }
  <li>/auth/login - Methods: POST</li>
  body { "username": username, "password": password }
  <li>/topics - Methods: GET, POST</li>
  POST body { "title": yourtitle }
  <li>/topics/{id} - Methods: GET, PUT, DELETE</li>
  PUT body { "title": yourtitle }
  <li>/topics/{id}/comments - Methods: GET, POST</li>
  POST body { "comment": yourcomment }
  <li>/topics/{id}/comments/{id} - Methods: PUT, DELETE</li>
  PUT body { "comment": yourcomment }
  </ul>

Username is parsed from a valid JWT-token, that will determine if a comment or a topic is owned by the user.

## Database

The API uses MariaDB. In order to use this API, you need to establish a connection to a running database.

You can use the official mariaDB image from docker.

Before starting the API:

- Make sure the database runs on the default port 3306 and that the username is root and password my-secret-pw

- Inject 2 roles to the roles table 
  - id:1 role:ADMIN
  - id:2 role:USER

## Spring security

The API uses spring security. Securityfilterchain allows all access to login and register. All other requests require a JWT token from the server, which is provided when a user logs in.

The only admin right that was required in the assignment was that only a user with a role ADMIN can delete any topic.

Cors configuration is set to allow all requests from localhost:3000, which is the default port for a ReactJS application.
