# flight-tickets

------------Install using Maven-----------------
1 install maven 3.6.0
2 check installation with 'mvn --version' command
3 navigate to project root
4 run 'mvn clean package'
5 navigate to ../broker/target
6 run 'java -jar flight-tickets-broker-0.0.1-SNAPSHOT.jar'



------- Run using Intellij --------------
1 import maven project
2 run spring boot main class FlightTicketsBrokerApplication



--------- How to check if it's working ---------------
1 go to http://localhost:8080/swagger-ui.html#/controller
2 try using swagger REST with 'try it' button
