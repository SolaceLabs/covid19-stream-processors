var mqtt = require('mqtt')

var host = "tcp://mr2r9za6fwi0wf.messaging.solace.cloud:1883"
var config = {
    username: "covid-public-client",
    password: "covid19",
}

var topics = [
    "jhu/csse/covid19/test/cases/+/update/Canada/Ontario/#",
    "jhu/csse/covid19/test/cases/recovered/update/Jordan/#",
    "jhu/csse/covid19/test/cases/confirmed/update/United Kingdom/#"
]

var client  = mqtt.connect(host, config)

client.on('connect', () => {
    console.log("Connected")
    topics.forEach( (topic) => {
        console.log("Subscribing to topic: ", topic)
        client.subscribe(topic)
    })
})

var count = 0
client.on('message', (topic, message) => {
    console.log("Received message on Topic: ", topic,"\nMessage:\n", JSON.parse(message.toString()))
    count++
})
