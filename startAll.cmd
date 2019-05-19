start cmd /k java -jar ./broker/target/flight-tickets-broker-0.0.1-SNAPSHOT.jar
timeout /t 7
start cmd /k java -Dserver.port=8081 -Dinit.file="WizAir.json" -jar ./distributor/target/flight-tickets-distributor-0.0.1-SNAPSHOT.jar
start cmd /k java -Dserver.port=8082 -Dinit.file="LOT.json" -jar ./distributor/target/flight-tickets-distributor-0.0.1-SNAPSHOT.jar
start cmd /k java -Dserver.port=8083 -Dinit.file="Lufthansa.json" -jar ./distributor/target/flight-tickets-distributor-0.0.1-SNAPSHOT.jar