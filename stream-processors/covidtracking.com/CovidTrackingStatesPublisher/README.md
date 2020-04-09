# CovidTracking_StatesCurrentValuePublisher
This application calls https://covidtracking.com/api/states on a 30 second interval, processes the data and publishes back to the broker in smaller updates on the following topics

`com/covidtracking/states/current/get/raw`

# Prerequisites
1. Event Broker (Software, Cloud)
1. Client username with publish right access publish rights to the Broker

# How to run
1. Install COVID19DataModels
```
./mvnw clean install
```

1. Run the corresponding spring boot application filling in: `<host_name`, `<msg_vpn>`, `<client_username>`, `client_password>`
```
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Dhost=<host_name> -DmsgVpn=<msg_vpn> -DclientUsername=<client_username> -DclientPassword=<client_password>"
```
