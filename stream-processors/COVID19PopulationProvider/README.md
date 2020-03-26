# Population Provider
This application subscribes to the `jhu/csse/covid19/cases/confirmed/update/{attributes.countryRegion}/{attributes.provinceState}` topic and publishes the percentage of the affected population on the following topic(s)

`jhu/csse/covid19/cases/confirmed/population/update/US/{attributes.provinceState}`

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