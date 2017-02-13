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
4. Add a table called 'EMPLOYEE' to the websystique database
    "
    CREATE TABLE EMPLOYEE(
        id INT NOT NULL auto_increment, 
        name VARCHAR(50) NOT NULL,
        joining_date DATE NOT NULL,
        salary DOUBLE NOT NULL,
        ssn VARCHAR(30) NOT NULL UNIQUE,
        PRIMARY KEY (id)
    );
    "
5. Create user 'myuser' with password 'mypasswd'
    - CREATE USER 'myuser'@'localhost' IDENTIFIED BY 'mypasswd';
6. Grant this user privileges on the current database
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

