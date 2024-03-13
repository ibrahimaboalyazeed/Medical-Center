# Medical Center System Backend

## Summary
This project showcases the development of a backend system using Spring Boot for a medical center management application. Key features include user authentication, appointment scheduling, patient and doctor management, and secure storage of medical records. The application ensures efficient handling of medical center operations while maintaining robust security measures through JWT-based authentication.

## Tech Stack

- Framework: Spring Boot 2.4
- Authentication: JWT-based authentication & stateless session
- Database: MySQL
- Languages: Java 11
- Build Tool: Maven
- Tomcat Server
- Swagger: UI for API documentation
## How to Run the Project

1. ### Clone the Repository:

   ```bash
   git clone https://github.com/ibrahimaboalyazeed/Medical-Center.git
   cd Medical-Center-

2. ### Set Up the Database:
    - Install MySQL if not already installed.
    - Create a new database named medical_center.
    - Update the application.properties file with your database credentials.
3. ### Build and Run the Application:
    - Open the project in your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse).
    - Build and run the application.
    - Alternatively, you can use Maven to build and run the application:
      
       ```bash
       mvn spring-boot:run
4. ### Test the Endpoints:

     - Once the application is running, test the endpoints using tools like Postman or curl.
## API Documentation
Postman Collection => <https://documenter.getpostman.com/view/28314600/2sA2xjyWah>

Swagger => <http://localhost:8080/swagger-ui.html>
![Swagger-UI!](https://github.com/ibrahimaboalyazeed/Medical-Center/blob/master/screenshots/Swagger-UI.PNG)

## Database Schema
![database-schema!](https://github.com/ibrahimaboalyazeed/Medical-Center/blob/master/screenshots/databse-schema.PNG)
## Details

### User Authentication:

 - Implement robust user authentication allowing users to log in securely.
 - Utilize JWT-based authentication for enhanced security.
   
### Appointment Scheduling:

 - Develop functionalities for patients to schedule appointments with available doctors.

### Patient and Doctor Management:

 - Implement features for administrators to manage patient and doctor records efficiently.

### Medical Records Management:

 - Ensure secure storage and management of medical records for patients.

## Controllers and Endpoints:

 ### AuthController

- **POST /api/v1/auth/login**
  - Endpoint for user login.
  - Parameters:
    - > email (String): User's email address.
    - password (String): User's password.
  - Returns:
    - JWT token upon successful authentication along with user details.

- **POST /api/v1/auth/signup**
  - Endpoint for user registration.
  - Parameters:
    - User object: User details including email, password, and other relevant information.
  - Returns:
    - Newly registered user details along with a JWT token for subsequent authentication.

### ClinicController

- **GET /clinics/{id}**
  - Summary: Get a clinic by its id.
  - Parameters:
    - id (Long): Clinic's id.
  - Returns:
    - Response containing the clinic details.

- **GET /clinics/name/{name}**
  - Summary: Get a clinic by its name.
  - Parameters:
    - name (String): Clinic's name.
  - Returns:
    - Response containing the clinic details.

- **GET /clinics/filter**
  - Summary: Filter the clinics by name.
  - Parameters:
    - name (String, optional): Clinic's name for filtering.
  - Returns:
    - Response containing the filtered clinics or all clinics if no name is provided.

- **GET /clinics/all**
  - Summary: Get all clinics.
  - Parameters:
    - pageNo (int, default: 0): Page number.
    - pageSize (int, default: 5): Number of items per page.
    - sortcol (String, default: "id"): Column to sort by.
    - isAsc (Boolean, default: true): Sort order (ascending or descending).
  - Returns:
    - Paginated response containing the list of clinics.

- **POST /clinics/create**
  - Summary: Add a new clinic.
  - Parameters:
    - Clinic object: Clinic details.
  - Returns:
    - Response containing the newly created clinic details.

- **PUT /clinics/update**
  - Summary: Update a clinic.
  - Parameters:
    - Clinic object: Clinic details to update.
  - Returns:
    - Response containing the updated clinic details.

- **DELETE /clinics/{id}**
  - Summary: Delete a clinic by its id.
  - Parameters:
    - id (Long): Clinic's id.
  - Returns:
    - Response confirming the deletion of the clinic.

