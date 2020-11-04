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

## API

#### Get active product price 

    GET http://localhost:5000/prices-api/products/{productId}/price?utc_date={utcDate}
    
- The Brand header must be included. For example: x-brand:ZARA
- The id of the product
- The utcDate to check if exists active prices

NOTE: start_date and end_date of prices are stored in database in UTC timezone, for that reason the external consumers of prices-api must use utc_date param in UTC too.

##### Response OK (with status 200 if exists active price)

``` json
{
    "product_id": 35455,
    "brand_id": 1,
    "price_list": 4,
    "start_date": "2020-06-15T16:00:00Z",
    "end_date": "2020-12-31T23:59:59Z",
    "amount": 38.95,
    "curr": "EUR"
}
```

##### Response not found (with status 404 if not exists active price)

``` json
{
    "status": "NOT_FOUND",
    "body": "No active price found for date [2020-06-16T21:00:00.000Z], product_id [354556] and brand [ZARA]"
}
```

##### Response bad request (with status 400 if date is not in ISO 8601 format and UTC timezone)

``` json
{
    "status": "BAD_REQUEST",
    "body": "Invalid date, please use ISO 8601 format in UTC timezone. e.g.: 2000-10-31T01:30:00.000Z"
}
```

##### Curl example

``` curl
curl --location --request GET 'http://localhost:5000/prices-api/products/35455/price?utc_date=2020-06-16T21:00:00.000Z' \
--header 'x-brand: ZARA'
```

## ToDo

List of possible improvements:

- [ ] Create entities: product, brand and priceList to complete the API
- [ ] Securize API
- [ ] Building a deployment process


## Bibliography

- [H2 Database in memory](https://www.baeldung.com/spring-boot-h2-database)
- [Open API documentation](https://www.baeldung.com/spring-rest-openapi-documentation)
- [Restful basics](https://www.baeldung.com/building-a-restful-web-service-with-spring-and-java-based-configuration)
- [Entity to DTO using ModelMapper](https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application)
- [ISO 8601 date format](https://www.digi.com/resources/documentation/digidocs/90001437-13/reference/r_iso_8601_date_format.htm)
