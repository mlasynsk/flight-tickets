# flight-tickets

------------Install using Maven----------------- </br>
1 install maven 3.6.0  </br>
2 check installation with 'mvn --version' command  </br>
3 navigate to project root  </br>
4 run 'mvn clean package'  </br>
5 navigate to ../broker/target  </br>
6 run 'java -jar flight-tickets-broker-0.0.1-SNAPSHOT.jar'  </br>
 </br> </br>


------- Run using Intellij --------------  </br>
1 import maven project  </br>
2 run spring boot main class FlightTicketsBrokerApplication  </br>

 </br> </br>

--------- How to check if it's working ---------------  </br>
1 go to http://localhost:8080/swagger-ui.html#/controller  </br>
2 try using swagger REST with 'try it' button </br>


--- RUN All ---------------------------- <br/>
1 naviagate to root <br/>
2 run 'mvn clean package -DskipTests' <br/>
3 run 'startAll.cmd' <br/>
4 check if working http://localhost:8081 <br/>
5 check if working http://localhost:8082 <br/>
6 check if working http://localhost:8083 <br/>

---- Params to start for Intellij----- <br/>
-Dserver.port=8081 -Dinit.file="WizAir.json" -Dconfig.file="WizAir.conf"