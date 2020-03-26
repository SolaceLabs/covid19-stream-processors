# Cases Splitter
This application consumes the JHU raw published COVID data stream, processes the data and publishes back to the broker smaller updates on the following topics     

`jhu/csse/covid19/cases/active/update/{attributes.countryRegion}/{attributes.provinceState}`
`jhu/csse/covid19/cases/confirmed/update/{attributes.countryRegion}/{attributes.provinceState}`
`jhu/csse/covid19/cases/deaths/update/{attributes.countryRegion}/{attributes.provinceState}`
`jhu/csse/covid19/cases/recovered/update/{attributes.countryRegion}/{attributes.provinceState}`

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
