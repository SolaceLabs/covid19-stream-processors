# CovidTracking_StateSplitterCurrentValuePublisher
This Application Splits the Raw Data into each state and publishes back to the broker in smaller updates so you can consume just the state of interest on the following topics

`com/covidtracking/states/current/update/{state}`

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
