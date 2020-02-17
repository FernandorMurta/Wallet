# WALLET MICROSERVICE

WALLET MICROSERVICE is a Spring Boot Application Written in java

## Tools
- Java 8
- Spring Boot
- Spring Data
- JPA
- Hibernate
- H2 Database
- Swagger Ui
- Maven Checkstyle Plugin
- Lombok
- Maven


## Installation

```bash
mvn clean package
```

After the package was built use

```bash
java -jar target/wallet-0.0.1-SNAPSHOT.jar
```


## Usage

To see the List of Services go to

[Swagger](http://localhost:8080/api/swagger-ui.html)

To access the database go to 

[h2-console](http://localhost:8080/api/h2-console)

Properties to access the console
```bash
jdbc url: jdbc:h2:file:~/wallet;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE
user: sa
password: password
```
