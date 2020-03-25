# Regional Splitter
This application consumes the JHU raw published COVID data stream, processes the data and publishes back to the broker smaller regional updates on the following topic     

`jhu/csse/covid19/cases/region/update/{attributes.countryRegion}/{attributes.provinceState}`

# Prerequisites
1. Event Broker (Software, Cloud)
1. Client username with publish right access publish rights to the Broker

# How to run
1. Run the corresponding spring boot application
```
./mvnw spring-boot:run --host=<PubSub+ Host URL> --msgVPN=<Message VPN> --clientUsername=<Client Username> --clientPasssword=<Client Password>
```