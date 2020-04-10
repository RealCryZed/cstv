# CSTV

I built a Spring Boot web application that uses cloud databases - MongoDB Atlas and PostgreSQL - to store data about counter-strike news, matches etc.
User authorization works along with Spring Security and provides BQrypt encoding for better security.
CSTV is a site that gives you news about Counter-Strike Scene, ended or upcoming matches and top teams/players.

## Built with

* Java 12 (JDK 12.0.2)
* Maven
* Spring Boot
* MongoDB and PostgreSQL
* Caffeine Cache
* JavaScript
* HTML5
* CSS3

## To run the project

* Clone the repository using `git clone` and paste it in your terminal.
* Run `mvn clean package` from the project directory
* Run project using `java -jar target/cstv.jar`, then open a browser window and go to `localhost:8080`.
