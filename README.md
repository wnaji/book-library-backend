**_Running a PostgreSQL database using Docker Compose**_

This repository provides a docker-compose.yml file that can be used to start a PostgreSQL database container using Docker Compose.

**_Prerequisites_**

To use this Docker Compose file, you must have Docker and Docker Compose installed on your system. Please refer to the Docker documentation for instructions on how to install Docker and Docker Compose.

Starting the PostgreSQL container

To start the PostgreSQL container using Docker Compose, navigate to the directory containing the docker-compose.yml file and run the following command:

docker-compose up

This command will start the container in the background (-d flag) and map the container's port 5432 to the host's port 5432 (specified in the ports section of the docker-compose.yml file). The database will be created with the username postgres and the password 19931993 (specified in the environment section of the docker-compose.yml file).

**_Verifying the database password**_

To verify that the password in the docker-compose.yml file matches the one in your Spring Boot application's configuration, you can check the application.properties file in your Spring Boot application.

In the application.properties file, there should be a line that looks like this:

**spring.datasource.password=19931993**

If the password in this line matches the password specified in the docker-compose.yml file, then your Spring Boot application will be able to connect to the database using the default username and password.

_**Starting your Spring Boot application**_

To start your Spring Boot application and connect it to the PostgreSQL database, you can simply run your application as you normally would. The application should automatically use the database settings specified in the application.properties file.



