Airline Reservation System
--------------------------
Mallory Tollefson
Thomas Miksch
Peter Li

Setting up the environment:
--------------------------
1. Install MySQL 
2. Go to the mysql command line
   - mysql
   (a) If you setup a user account while installing MySQL, go to the command line like so
       - mysql -u<your_user> -p
       - where '-p' means "enter user password after command to login"

2. Create the 'websystique' database on the mysql command line
    - CREATE DATABASE websystique;

3. Switch to the websystique database as the active database for the current session
    - USE websystique;

4. Add a table called 'USER' to the websystique database
    "
    CREATE TABLE USER(
        id INT NOT NULL auto_increment, 
        user_type VARCHAR(10) NOT NULL,
        first_name VARCHAR(20) NOT NULL,
        last_name VARCHAR(20) NOT NULL,
        email VARCHAR(50) NOT NULL,
        password VARCHAR(40) NOT NULL,
        passwordHolder VARCHAR(40) NOT NULL,
        dobY VARCHAR(4) NOT NULL,
        dobM VARCHAR(2) NOT NULL,
        dobD VARCHAR(2) NOT NULL,
        gender VARCHAR(6) NOT NULL,
        phone_number BIGINT NOT NULL,
        PRIMARY KEY (id)
    );
    "
    
5. Add a table called 'AIRCRAFT' to the websystique database
    "
    CREATE TABLE AIRCRAFT(
        id INT NOT NULL auto_increment,
        symbol VARCHAR(10) NOT NULL,
        aircraft_type VARCHAR(15) NOT NULL,
        first_class_seats INT NOT NULL,
        business_class_seats INT NOT NULL,
        economy_class_seats INT NOT NULL,
        PRIMARY KEY (id)
    );
    "

6. Add a table called 'FLIGHT_ROUTE' to the websystique database.
    "
    CREATE TABLE FLIGHT_ROUTE(
        id INT NOT NULL auto_increment,
        symbol VARCHAR(10) NOT NULL,
        aircraft VARCHAR(10) NOT NULL,
        first_class_price INT NOT NULL,
        business_class_price INT NOT NULL,
        economy_class_price INT NOT NULL,
        origin VARCHAR(50) NOT NULL,
        destination VARCHAR(50) NOT NULL,
        start_time VARCHAR(20) NOT NULL,
        end_time VARCHAR(20) NOT NULL,
        frequency VARCHAR(10) NOT NULL,
        begin_date VARCHAR(10) NOT NULL,
        end_date VARCHAR(10) NOT NULL,
        PRIMARY KEY (id) 
    );
    "

7. Add a table called 'FLIGHT' to the websystique database.
    "
    CREATE TABLE FLIGHT(
        id INT NOT NULL auto_increment,
        flight_route_id INT NOT NULL,
        date VARCHAR(10) NOT NULL,
        origin VARCHAR(50) NOT NULL,
        destination VARCHAR(50) NOT NULL,
        first_class_price INT NOT NULL,
        business_class_price INT NOT NULL,
        economy_class_price INT NOT NULL,
        start_time VARCHAR(20) NOT NULL,
        end_time VARCHAR(20) NOT NULL,
        seats INT NOT NULL,
        flight_class VARCHAR(10) NOT NULL,
        PRIMARY KEY (id)
    );
    "

7. Add the 'Booking' table
    CREATE TABLE BOOKING(
        id INT NOT NULL auto_increment,
        user_email VARCHAR(50) NOT NULL,
        flight_number INT NOT NULL,
        class VARCHAR(20) NOT NULL,
        seats INT NOT NULL,
        PRIMARY KEY (id)
    );

6. Create user 'myuser' with password 'mypasswd'
    - CREATE USER 'myuser'@'localhost' IDENTIFIED BY 'mypasswd';

7. Grant this user privileges on the current database
    - GRANT ALL ON websystique.* TO 'myuser'@'localhost';


Running the code:
-----------------
1. Create a new .war (Web application ARchive) file and deploy it to the Jetty server via the maven-jetty plugin
    - mvn clean install jetty:run
2. Open up a web browser and go to http://localhost:8080


References:
-----------
Example code and explanations:
    - http://websystique.com/springmvc/spring-4-mvc-and-hibernate4-integration-example-using-annotations/

