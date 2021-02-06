import paho.mqtt.client as mqtt
import json

def on_connect(client, data, flags, rc):
    assert (rc == 0), "Error Connecting. Return code: " + str(rc)
    print("Connected to COVID PubSub+ Solace Broker!")

    for t in topics:
        print("Subscribing to: " + t)
        client.subscribe(t)

def on_message(client, data, msg):
    msg_json_object = json.loads(msg.payload.decode('ascii'))
    msg_json_pretty = json.dumps(msg_json_object, indent=2)
    print("Received message on: %s\n %s" % (msg.topic, msg_json_pretty))

# Broker Info
url = "covid.messaging.solace.cloud"
username = "covid-public-client"
password = "covid19"


topics = [
    "jhu/csse/covid19/test/cases/+/update/Canada/Ontario/#",
    "jhu/csse/covid19/test/cases/recovered/update/Jordan/#",
    "jhu/csse/covid19/test/cases/confirmed/update/United Kingdom/#",
    "com/covidtracking/states/current/update/New York",
]

client = mqtt.Client()
client.username_pw_set(username=username, password=password)

client.on_connect = on_connect
client.on_message = on_message

client.connect(url)
client.loop_forever()
