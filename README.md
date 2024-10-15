# Wallets Service, implemented by Antonio Lopez
In Playtomic, we have a service to manage our wallets. Our players can top-up their wallets using a credit card and spend that money on our platform (bookings, racket rentals, ...)

That service has the following operations:
- You can query your balance.
- You can top-up your wallet. In this case, we charge the amount using a third-party payments platform (stripe, paypal, redsys).
- You can spend your balance on purchases in Playtomic. 
- You can return these purchases, and your money is refunded.
- You can check your history of transactions.

This exercise consists of building a proof of concept of that wallet service.
You have to code endpoints for these operations:
1. Get a wallet using its identifier.
1. Top-up money in that wallet using a credit card number. It has to charge that amount internally using a third-party platform.

You don't have to write the following operations, but we will discuss possible solutions during the interview:
1. How to spend money from the wallet.
1. How to refund that money.

The basic structure of a wallet is its identifier and its current balance. If you think you need extra fields, add them. We will discuss it in the interview. 

So you can focus on these problems, you have here a maven project with a Spring Boot application. It already contains
the basic dependencies and an H2 database. There are development and test profiles.

You can also find an implementation of the service that would call to the real payments platform (StripePaymentService).
This implementation is calling to a simulator deployed in one of our environments. Take into account
that this simulator will return 422 http error codes under certain conditions.

Consider that this service must work in a microservices environment in high availability. You should care about concurrency too.

You can spend as much time as you need but we think that 4 hours is enough to show [the requirements of this job.](OFFER.md)
You don't have to document your code, but you can write down anything you want to explain or anything you have skipped.
You don't need to write tests for everything, but we would like to see different types of tests.

### Technology
- Java version 17
- Spring boot version 3.2
- Lombok latest version
- H2 database latest version
- Jaeger (for traceability) version 1.8
- Springdoc (for swagger documentation generation) version 2.6
- Flyway (for database migration) latest version

### Endpoints
There are 2 endpoints in the system

`GET /wallets/{walletId}` to retrieve a wallet by its id

and `POST /wallets/{walletId}/topup` to top up a wallet. It expects a json body with cardNumber and amount

### Endpoint documentation
The service includes the springdoc library that scans the solution to provide readable documentation of the different endpoints.
This documentation can be visited in http://localhost:8090/swagger-ui/index.html when the solution is running

### Traceability
Since it is stated that the solution would run along side other micro-services the jaeger library has been included and configured in this service.
The configuration includes a filter and a custom implementation of restTemplate to make sure that the trace id is transmitted across all communications and included in all the logs.

### Database migration
For the service to be able to run tests or work normally, the database should contain the sql files in resources/db/migration should be executed.
For doing so you can run this command in the terminal:
`mvn flyway:migrate`

### Testing
It has been included both unit testing (with service mocking) and integration testing to test the service's endpoints end to end
You can run the tests from the IDE or executing `mvn test`

### Running
When running the application it will listen in the port 8090.
To make it easy to test the running application, the file Playtomic.postman_collection.json has been included so all the endpoints can be easily tested.
Only in develop profile (default one for now) opens up an endpoint that creates a wallet automatically to make it easier to test during development (included in the Postman collection). 