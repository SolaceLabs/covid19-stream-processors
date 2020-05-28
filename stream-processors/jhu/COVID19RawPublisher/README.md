# Regional Splitter
This application publishes raw JHU data to the broker smaller regional updates on the following topic     

`jhu/csse/covid19/raw`

# Prerequisites
1. Event Broker (Software, Cloud)
1. Client username with publish right access publish rights to the Broker
1. Install COVID19DataModels

# How to run
1. Install COVID19DataModels
```
./mvnw install -f ../pom.xml
```

1. Run the corresponding spring boot application
```
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Dhost=<host_name> -DmsgVpn=<msg_vpn> -DclientUsername=<client_username> -DclientPassword=<client_password>"
```