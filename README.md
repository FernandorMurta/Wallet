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
[Swagger api-doc](http://localhost:8080/api/v2/api-docs)

To access the database go to 

[h2-console](http://localhost:8080/api/h2-console)

Properties to access the console
```bash
jdbc url: jdbc:h2:file:~/wallet;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE
user: sa
password: password
```


## Examples

Where is a link to download postman collection with some examples

[Postman Collection](https://www.postman.com/collections/167fe548bbb47553e3ab)


### Usage notes

Remember the first thing to do is create a new Player to use the Wallet
[See in](http://localhost:8080/api/swagger-ui.html#/player-controller/createPlayerUsingPOST)

After that you can start to do some transaction (DEBIT/CREDIT)
[See in - DEBIT](http://localhost:8080/api/swagger-ui.html#/transaction-controller/debitValueOfPlayerUsingPOST)
[See in - CREDIT](http://localhost:8080/api/swagger-ui.html#/transaction-controller/creditValueOfPlayerUsingPOST)
When user was created, he have a balance of 0.00.

To see all Transaction History of the user remember to put the TransactionType in the parameters
[See in](http://localhost:8080/api/swagger-ui.html#/transaction-controller/findAllWithParametersUsingGET)
Ex: 
- To see only Debit  -> transactionType=DEBIT
- To see only Credit -> transactionType=CREDIT
- To see both type of transaction -> transactionType=DEBIT,CREDIT

If you want just to see the balance of one player
[See in](http://localhost:8080/api/swagger-ui.html#/player-controller/getBalanceOfPlayerUsingGET)