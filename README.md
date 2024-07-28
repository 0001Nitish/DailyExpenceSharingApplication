# DailyExpenceSharingApplication

### Setup and Installation Instructions

1. _Clone the Repository_:
   git clone <https://github.com/0001Nitish/DailyExpenceSharingApplication>
   cd <DailyExpenceSharingApplication>

2. _Install Dependencies_:
   Ensure you have Maven installed. Run the following command to install the necessary dependencies:
   mvn clean install

3. _Database Setup_:

   - Ensure you have a running instance of a database (e.g., MySQL, PostgreSQL).
   - Update the application.properties file with your database configuration:
     properties
     spring.datasource.url=jdbc:postgres://localhost:5432/your_database
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     spring.jpa.hibernate.ddl-auto=update

     DDL

4. _Run the Application_:
   Use the following command to start the Spring Boot application:
   mvn spring-boot:run

5. _Access the Application_:
   Open your browser and navigate to http://localhost:8080.

6. \_Postman collection
