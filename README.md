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
    - `email` (String): User's email address.
    - `password` (String): User's password.
  - Returns:
    - JWT token upon successful authentication along with user details.

- **POST /api/v1/auth/signup**
  - Endpoint for user registration.
  - Parameters:
    - `User` object: User details including email, password, and other relevant information.
  - Returns:
    - Newly registered user details along with a JWT token for subsequent authentication.

### ClinicController

- **GET /clinics/{id}**
  - Summary: Get a clinic by its id.
  - Parameters:
    - `id` (Long): Clinic's id.
  - Returns:
    - Response containing the clinic details.

- **GET /clinics/name/{name}**
  - Summary: Get a clinic by its name.
  - Parameters:
    - `name` (String): Clinic's name.
  - Returns:
    - Response containing the clinic details.

- **GET /clinics/filter**
  - Summary: Filter the clinics by name.
  - Parameters:
    - `name` (String, optional): Clinic's name for filtering.
  - Returns:
    - Response containing the filtered clinics or all clinics if no name is provided.

- **GET /clinics/all**
  - Summary: Get all clinics.
  - Parameters:
    - `pageNo` (int, default: 0): Page number.
    - `pageSize` (int, default: 5): Number of items per page.
    - `sortcol` (String, default: "id"): Column to sort by.
    - `isAsc` (Boolean, default: true): Sort order (ascending or descending).
  - Returns:
    - Paginated response containing the list of clinics.

- **POST /clinics/create**
  - Summary: Add a new clinic.
  - Parameters:
    - `Clinic` object: Clinic details.
  - Returns:
    - Response containing the newly created clinic details.

- **PUT /clinics/update**
  - Summary: Update a clinic.
  - Parameters:
    - `Clinic` object: Clinic details to update.
  - Returns:
    - Response containing the updated clinic details.

- **DELETE /clinics/{id}**
  - Summary: Delete a clinic by its id.
  - Parameters:
    - `id` (Long): Clinic's id.
  - Returns:
    - Response confirming the deletion of the clinic.
### DoctorController

- GET /doctors/{id}
  - Summary: Get a doctor by his Id.
  - Parameters:
    - `id` (long): Doctor's id.
  - Returns:
    - Doctor details.

- GET /doctors/all
  - Summary: Get all doctors.
  - Returns:
    - List of all doctors.

- POST /doctors/create
  - Summary: Add new doctor.
  - Parameters:
    - `Doctor` object: Doctor details.
  - Returns:
    - Newly created doctor details.

- PUT /doctors/name/update
  - Summary: Update the doctor's name.
  - Parameters:
    - `id` (long): Doctor's id.
    - `fullName` (String): Doctor's full name.
  - Returns:
    - Updated doctor details.

- PUT /doctors/phonenumber/update
  - Summary: Update the doctor's phone number.
  - Parameters:
    - `id` (long): Doctor's id.
    - `phoneNumber` (String): Doctor's phone number.
  - Returns:
    - Updated doctor details.

- DELETE /doctors/{id}
  - Summary: Delete a doctor by his id.
  - Parameters:
    - `id` (long): Doctor's id.
  - Returns:
    - Deletion status message.
### PatientController

- GET /patients/{id}
  - Operation: Get a patient by his Id.
  - Parameters:
    - `id` (long): ID of the patient.
  - Returns:
    - ResponseEntity containing the patient details.

- GET /patients/all
  - Operation: Get all patients.
  - Returns:
    - ResponseEntity containing a list of all patients.

- POST /patients/create
  - Operation: Add new patient.
  - Parameters:
    - `Patient` object: Details of the patient to be created.
  - Returns:
    - ResponseEntity containing the newly created patient details.

- PUT /patients/name/update
  - Operation: Update the patient's name.
  - Parameters:
    - `id` (long): ID of the patient.
    - `fullName` (String): Updated full name of the patient.
  - Returns:
    - ResponseEntity containing the updated patient details.

- PUT /patients/phonenumber/update
  - Operation: Update the patient's phone number.
  - Parameters:
    - `id` (long): ID of the patient.
    - `phoneNumber` (String): Updated phone number of the patient.
  - Returns:
    - ResponseEntity containing the updated patient details.

- DELETE /patients/{id}
  - Operation: Delete a patient by its id.
  - Parameters:
    - `id` (long): ID of the patient to be deleted.
  - Returns:
    - ResponseEntity containing the result of the deletion operation.

### ReservationController

- POST /reservations/create
  - Admin: Create a New Reservation
  - Parameters:
    - `Reservation` object: Details of the reservation to be created.
  - Returns:
    - CustomResponse with the created reservation.

- POST /reservations/report
  - Retrieve reservation report for a specific doctor in a specific date
  - Parameters:
    - `ReservationReport` object: Contains date and clinics map.
  - Returns:
    - CustomResponse with the generated reservation report.

- POST /reservations
  - Add a new reservation
  - Parameters:
    - `ReservationRequest` object: Contains report, patientId, and timeArray.
  - Returns:
    - CustomResponse with the added reservation.

- DELETE /reservations/{id}
  - Delete a reservation by its id
  - Parameters:
    - `id` (Long): ID of the reservation to be deleted.
  - Returns:
    - CustomResponse indicating the success of the deletion.

- GET /reservations/all
  - Retrieve all reservations
  - Returns:
    - CustomResponse with all reservations.

- GET /reservations/doctor/{id}
  - Retrieve a Doctor's reservations
  - Parameters:
    - `id` (Long): ID of the doctor.
  - Returns:
    - CustomResponse with the doctor's reservations.

- GET /reservations/patient/{id}
  - Retrieve a Patient's reservations
  - Parameters:
    - `id` (Long): ID of the patient.
  - Returns:
    - CustomResponse with the patient's reservations.
