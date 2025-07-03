# Position Book System API
A demontstration on Position Book System using SpringBoot, Java,  

## Setup
The API runs on ``8080`` port.
Please ensure that you have Java 21 or higher installed on your machine.
To run the application, follow these steps after cloning the repo to local machine:

1. ``mvn clean install``
2. ``mvn spring-boot:run``

or

Simply use your favorite IDE to run the application.

## Access to App
###APIs

``http://localhost:8080/api/positions`` -- Get Method --  Lists all available positions if not an empty array.
``http://localhost:8080/api/events`` -- Post Method -- Accepts Events as input and processes them based on Actions.
