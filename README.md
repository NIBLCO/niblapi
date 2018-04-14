# Intro
The NIBL API will be used to view XDCC bots and search for pack listings

This is written in Spring Boot (of which I have little experience) and uses a MySQL database.

Documentation on RESTful calls is handled in Swagger

#### Swagger URL
http://{Server IP}:8080/swagger-ui.html

# Setup

## Required Software
	1) Java 1.8
	2) Maven
	3) MySQL

## Database
	1) Import the database schema (db/schema.sql) and data (db/data.sql)
	2) Update the mysql username and password (src/main/resources/application.properties)

Yes, I know the schema is not perfect, it was written in 2007.
I will eventually update the schema to JPA standards while rewriting the software that populates it.

## App
	1) mvn clean install
	2) java -jar target/nibl-api.jar

# Debug

## Database
JPA is good about throwing errors if the database structure does not map to annotations
or Java objects properly.

You should see errors during start-up which tell you what is wrong.

## App
Log levels can be adjusted in the application.yml file.

Defaults are;

    org.springframework.boot.env.PropertySourcesLoader: ERROR
    org.springframework.web: ERROR
    com.nibl: ERROR
    org.hibernate.SQL: ERROR


