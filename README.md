# Prices-API

Basic API Rest for product prices.

#### Technology stack

- Java 8
- Maven
- SpringBoot
- H2 Database (in memory)

#### Requirements

- Maven
- JDK 1.8

## Run the application

```
$ cd prices-api
$ mvn spring-boot:run
```
SpringBoot application up in port 5000, check health endpoint:

    GET http://localhost:5000/prices-api/actuator/health

## Run the tests

```
$ cd prices-api
$ mvn test
```



