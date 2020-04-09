const LINES_TO_SHOW = 100;
var isPaused = false;
var host = "mr2r9za6fwi0wf.messaging.solace.cloud";
var port = 8443
var username = "covid-public-client";
var password = "covid19";
var useSSL = true;
var client_id = "" + (Math.random() + 1).toString(36).substring(2, 10);
var msgs = 0

client = new Paho.MQTT.Client(host, Number(port), client_id);

// set callback handlers
client.onConnectionLost = onConnectionLost;
client.onMessageArrived = onMessageArrived;

// connect the client
connectOptions = {};
connectOptions["useSSL"] = useSSL;
connectOptions["userName"] = username;
connectOptions["password"] = password;
connectOptions["onSuccess"] = onConnect;
writeToScreen("Connecting to Solace PubSub+ ...");
client.connect(connectOptions);

// called when the client connects
function onConnect() {
    writeToScreen("Connected and ready to receive updates on specified topics");
    document.getElementById("div_tl").style.display = "block"
}

function topicUnsubscribe() {
    var topicSub = document.getElementById("unsub-topic").value;
    writeToScreen("Unsubscribing from topic: " + topicSub);
    client.unsubscribe(topicSub);
    document.getElementById("unsub-topic").value = "";
}

function topicSubscribe() {
    var topicSub = document.getElementById("sub-topic").value;
    if (topicSub != "") {
        writeToScreen("Subscribing to topic: " + topicSub);
        client.subscribe(topicSub);
        document.getElementById("unsub-topic").value = topicSub;
        document.getElementById("sub-topic").value = "";
    }
}

// called when the client loses its connection
function onConnectionLost(responseObject) {
    if (responseObject.errorCode !== 0) {
        writeToScreen("onConnectionLost:" + responseObject.errorMessage);
    }
}

// called when a message arrives
function onMessageArrived(message) {
    if (!isPaused) {
        // only write to the screen if we're not paused; otherwise, throw away
        msgs++;
        document.getElementById("msgs").innerHTML = "Message count: " + msgs
        var parsed = JSON.parse(message.payloadString);
        writeToScreen("Message arrived on topic:'" + message.destinationName);
        writeToScreen("<pre>" + JSON.stringify(parsed, undefined, 2) + "</pre>", "white");
    }
}

// write to screen
function writeToScreen(str, color) {
    var node = document.createElement("p");
    node.innerHTML = str;
    if (color) {
        node.style.color = color
    }
    document.body.appendChild(node);
    // do we need to trim the list?
    var paragraphList = document.body.getElementsByTagName("p");
    if (paragraphList.length > LINES_TO_SHOW) {
        document.body.removeChild(paragraphList[0]);
    }
}

function pause() {
    if (isPaused) {
        //writeToScreen('...unpaused.'); 
        isPaused = false;
        document.getElementById('pause').innerHTML = 'Pause';
        document.getElementById('pause').style.backgroundColor = "red";
    } else {
        isPaused = true;
        document.getElementById('pause').innerHTML = 'Unpause';
        document.getElementById('pause').style.backgroundColor = "#00CB95";
    }
}
