Requirements

The project must be written in Java 8 or above.

The project must use Maven OR Gradle to build, test and run.

The project must have unit and integration tests.

The project must be runnable in that the service should be hosted in a container e.g. Tomcat, Jetty, Spring Boot etc.

You may use any frameworks or libraries for support e.g. Spring MVC, Apache CXF etc.

The project must be accessible from Github.


Application requirements

Java 11 or higher

Spring boot 3.1.3 Apache Maven 3.8.2 Postman or any other REST API Testing tool

To get repository download

git clone https://github.com/snbiju/car-park.git

cd car-park

To run the application : mvn spring-boot:run

To run unit test and integration test : mvn test

Below algorithms were addressed, can be extended to others

SWAGGER URL :- http://localhost:8080/swagger-ui/index.html

Allocate Car Park (POST REQUEST) http://localhost:8080/carpark/allocate
request
{
"registrationNumber": "KL 4458"
}

Get All available Slots (GET REQUEST) http://localhost:8080/carpark/available  

Amend car park with extend to alloted hours (PUT REQUEST)  http://localhost:8080/carpark/amend/2 (/amend/{additionalHours})

Remove Car from Parking Slot (DELETE REQUEST) http://localhost:8080/carpark/deallocate/1 (/deallocate/{slotId})
This request will remove car as well as calculate total amount

