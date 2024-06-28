# API only implementation of the following task:

## TASK:

The mobile software testing team has 10 mobile phones that it needs
to share for testing purposes

- Samsung Galaxy S9
- 2x Samsung Galaxy S8
- Motorola Nexus 6
- Oneplus 9
- Apple iPhone 13
- Apple iPhone 12
- Apple iPhone 11
- iPhone X
- Nokia 3310

Please create an application that allows a phone to be booked / returned.
The following information should also be available for each phone

- Availability (Yes / No)
- When it was booked
- Who booked the phone

## Details of implementation:

This is a spring boot API only application using H2 db. It identifies 2 domain classes - phone and phone reservation and
has services to manage access to them through repositories.
As a new phone booking results in a new record in phone reservation it uses the unique constraint to protect from
duplicate bookings.
Application doesn't have any login/customer/security concept, so anyone can book and delete reservatopns.

## How to run

- you need java 17+
- if you have maven already setup, execute from the root of the project folder:
  ```mvn spring-boot:run```
- if you don't have maven use this command from the root of the project folder:
  ```mvnw spring-boot:run```

## How to test
### With postman
Import the postman collection from the file [Beamtrail.postman_collection.json](Beamtrail.postman_collection.json)
- It has 2 get requests to fetch either all phones or "phone availability" as stated in the task
- It has 1 post request to book a phone(you need a phone id and random name)
- It has 2 delete requests to delete booking either by reservation id returned from the post request or by phone id

### With swagger
In browser try `http://localhost:8080/swagger-ui.html`