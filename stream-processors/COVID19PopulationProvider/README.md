# Population Provider
This application subscribes to the `jhu/csse/covid19/cases/confirmed/update/{attributes.countryRegion}/{attributes.provinceState}` topic and publishes the percentage of the affected population on the following topic(s)

`jhu/csse/covid19/cases/confirmed/population/update/US/{attributes.provinceState}`

# Prerequisites
1. Event Broker (Software, Cloud)
1. Client username with publish right access publish rights to the Broker

# How to run
1. Run the corresponding spring boot application
```
./mvnw spring-boot:run --host=<PubSub+ Host URL> --msgVPN=<Message VPN> --clientUsername=<Client Username> --clientPasssword=<Client Password>
```