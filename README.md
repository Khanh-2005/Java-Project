# Job Portal Project

## Introduction

This is a web-based Job Portal project that allows users to search for jobs and employers to post job listings. The project is built using Java Servlet, JSP, JDBC, and MySQL.

## Features

- User registration and login
- Profile management
- Job posting (Admin)
- View, edit, and delete job postings (Admin)
- Job search by location and category
- Apply for jobs and view job details

## Technologies Used

- Java Servlet & JSP
- JDBC (MySQL)
- Bootstrap 4, FontAwesome
- Maven

## Project Structure

- `src/main/java`: Java source code (Servlets, DAO, Entity)
- `src/main/webapp`: JSP pages, CSS, JS
- `target/`: Build directory and WAR file

## How to Run

1. Clone the project to your machine
2. Set up a MySQL database named `job_portal` and import the required tables
3. Edit the database connection info in [`com.DB.DBConnect`](src/main/java/com/DB/DBConnect.java)
4. Build the project:
   ```sh
   mvn clean package
   ```
5. Deploy the `jobportal.war` file to your Tomcat server

## Sample Account

- Admin:  
  Email: `admin@gmail.com`  
  Password: `admin123`

## Author

Khanh Ta
