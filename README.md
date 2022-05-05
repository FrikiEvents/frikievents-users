# Users Microservice
Users Microservice using Event Sourcing & CQRS with Spring Boot & Axon Framework

## Download Axon Server
https://download.axoniq.io/quickstart/AxonQuickStart.zip

## Running Axon Server
Unzip AxonQuickStart.zip and execute the following command:
```
$ cd documents/tools/axonquickstart-4.5.11/AxonServer
$ java -jar axonserver-4.5.11.jar
```

## Axon Server URL
http://localhost:8024

## Set Based Consistency Validation
https://axoniq.io/blog-overview/set-based-validation

## Environment Variables
### MYSQL_USERS_AXON_JDBC_URL
```
jdbc:mysql://localhost:3306/users?verifyServerCertificate=false&useSSL=false&useTimezone=true&serverTimezone=UTC
```

## Swagger
http://localhost:8081/v3/api-docs
http://localhost:8081/api-docs/swagger-ui/index.html

## Maven
$ mvn clean package