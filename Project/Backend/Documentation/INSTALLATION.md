
# Installation Instructions for Java Spring Maven Backend

## Prerequisites

Before proceeding with the installation, ensure the following prerequisites are met:

1. **Java JDK 17**: Ensure Java Development Kit 17 is installed on your machine. You can download it from [Oracle's website](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).

2. **MySQL Database**: The application requires a MySQL database. Install MySQL and create a database named `sudoku`.

3. **Maven**: The project uses Maven for dependency management. Install Maven from [Apache Maven Project](https://maven.apache.org/download.cgi).

## Database Setup

1. After installing MySQL, log in to the MySQL console and create a new database:

   ```sql
   CREATE DATABASE sudoku;
   ```

2. Create a user and grant all privileges on the `sudoku` database:

   ```sql
   CREATE USER 'sudoku'@'localhost' IDENTIFIED BY 'sudoku';
   GRANT ALL PRIVILEGES ON sudoku.* TO 'sudoku'@'localhost';
   FLUSH PRIVILEGES;
   ```

## Configuring Application Properties

1. Navigate to the `src/main/resources` directory in your project.

2. Edit the `application.properties` file with the following properties:

   ```properties
   # Database connection properties
   spring.datasource.url=jdbc:mysql://localhost:3306/sudoku
   spring.datasource.username=sudoku
   spring.datasource.password=sudoku

   # Additional database configuration
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true

   # Spring Security
   spring.security.user.name=admin
   spring.security.user.password=geheim

   # JWT Configuration
   wissquiz.app.jwtSecret="halloSecretsauce"
   wissquiz.app.jwtExpirationMs=36000000
   ```

## Building the Project

1. Open a terminal or command prompt.

2. Navigate to the root directory of the project where the `pom.xml` file is located.

3. Run the following Maven command to build the project:

   ```bash
   mvn clean install
   ```

## Running the Application

1. After successfully building the project, start the application with:

   ```bash
   mvn spring-boot:run
   ```

2. The application will start and be accessible on `http://localhost:8080`.  
  
  


# Installation Instructions for React Vite Frontend

## Prerequisites

Ensure you have the following prerequisites installed:

1. **Node.js**: The frontend requires Node.js. Download and install it from [Node.js official website](https://nodejs.org/).

2. **npm**: Usually comes bundled with Node.js. It's used for managing JavaScript packages.

## Setting Up the Project

1. Clone or download your React Vite project from its repository.

2. Open a terminal or command prompt.

3. Navigate to the root directory of your React project.

## Installing Dependencies

1. In the root directory of your project, run the following command to install all required dependencies:

   ```bash
   npm install
   ```

   This command reads the `package.json` file and installs all the dependencies listed there.

## Configuring the Environment

1. If your frontend needs to interact with the backend, ensure to set the backend URL in an `.env` file or directly in your code. For example, if your backend is running on `http://localhost:8080`, set it accordingly.

2. To run the frontend on a specific port (e.g., 5174), create a `.env.local` file in the root directory and add the following line:

   ```env
   VITE_PORT=5174
   ```

## Running the Application

1. To start the development server, run:

   ```bash
   npm run dev
   ```

2. The application will start and be accessible on `http://localhost:5174`.

## Building for Production

1. To build the application for production, run:

   ```bash
   npm run build
   ```

2. This command will create a `dist` directory with all the compiled and minified files.
