start cmd /k java -jar ./broker/target/flight-tickets-broker-0.0.1-SNAPSHOT.jar
timeout /t 7
start cmd /k java -Dserver.port=8081 -Dinit.file="WizAir.json"  -Dconfig.file="WizAir.conf" -jar ./distributor/target/flight-tickets-distributor-0.0.1-SNAPSHOT.jar
start cmd /k java -Dserver.port=8082 -Dinit.file="LOT.json"  -Dconfig.file="LOT.conf" -jar ./distributor/target/flight-tickets-distributor-0.0.1-SNAPSHOT.jar
start cmd /k java -Dserver.port=8083 -Dinit.file="Lufthansa.json"  -Dconfig.file="Lufthansa.conf" -jar ./distributor/target/flight-tickets-distributor-0.0.1-SNAPSHOT.jar