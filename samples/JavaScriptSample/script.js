const {
    SolclientFactory,
    SDTField,
    SDTFieldType,
    Session,
    StatType,
    SessionEventCode,
    TransportProtocol
} =
solace;
const {
    createSession,
    createTopicDestination,
    createMessage
} =
SolclientFactory;
const {
    getCurrentService
} =
SolaceCloud || {};

const MAX_MESSAGES = 300;

const app = new Vue({

    el: '#app',

    data: {
        tabs: {
            session: {
                active: true,
                form: {
                    url: "ws://mr2r9za6fwi0wf.messaging.solace.cloud:80",
                    userName: "covid-public-client",
                    vpnName: "covid-19-streams",
                    password: "covid19",
                    transportProtocol: TransportProtocol.HTTP_BINARY_STREAMING,
                    button: {
                        connect: 'Connect',
                        disconnect: 'Disconnect'
                    }
                },


                status: {
                    show: false,
                    message: ''
                }
            },


            subscribe: {
                form: {
                    topic: 'jhu/csse/covid19/raw',
                    buttons: {
                        subscribe: 'Subscribe'
                    }
                },


                status: {
                    show: false,
                    message: ''
                }
            }
        },




        connected: false,
        session: null,
        subscriptions: new Set(),
        messages: [],
        pendingSession: null,
        showAdvancedSettings: false
    },


    methods: {

        // Initialize the Solace API factory.
        init() {
            SolclientFactory.init({
                profile: SolclientFactory.profiles.version7
            });

            // If embedded in Solace Cloud (as a demo), ask the console for service credentials.
            getCurrentService && getCurrentService(r => app.setSessionCredentials(r.data.service));
        },

        // Reset the application state.
        reset() {
            this.session.dispose();
            this.session = null;
            this.subscriptions = [];
            this.connected = false;
        },

        // Connect a session and listen for events.
        connect() {
            if (this.session) this.reset();

            // Create a session with the given session properties
            try {
                this.session = createSession(this.tabs.session.form);
            } catch (ex) {
                this.setStatus('session', 'Error creating session: ' + ex.message);
                return;
            }

            // Listen for events
            this.session.on(SessionEventCode.UP_NOTICE, () => {
                this.connected = true;
                this.sessionRequestCompleted();
                this.setActiveTab('subscribe');
            });
            this.session.on(SessionEventCode.DISCONNECTED, () => {
                this.sessionRequestCompleted();
                this.reset();
            });
            this.session.on(SessionEventCode.DOWN_ERROR, (...args) => {
                this.setStatus('session', 'Session down with error ' + event);
                this.reset();
            });
            this.session.on(SessionEventCode.MESSAGE, message => {
                this.messageAdd(message);
            });

            // Connect the session
            this.showSessionRequestPending();
            try {
                this.session.connect();
            } catch (ex) {
                this.setStatus('session', 'Error connecting session: ' + ex.message);
                this.reset();
            }
        },

        // Disconnect the session.
        disconnect() {
            if (!this.session) return;
            this.showSessionRequestPending();
            this.session.disconnect();
        },

        // Subscribe to a topic.
        subscribe() {
            const { session } = this;
            const { form } = this.tabs.subscribe;
            try {
                session.subscribe(createTopicDestination(form.topic));
            } catch (ex) {
                this.setStatus('subscribe', 'Error subscribing: ' + ex.message);
            }

            this.subscriptions = Array.from(new Set([...this.subscriptions, form.topic]));
            form.topic = '';
        },

        // Unsubscribe from a topic.
        unsubscribe(topic) {
            const { session } = this;
            try {
                session.unsubscribe(createTopicDestination(topic));
            } catch (ex) {
                this.setStatus('subscribe', 'Error unsubscribing: ' + ex.message);
            }

            this.subscriptions = this.subscriptions.filter(x => x != topic);
        },

        // Wrap a received message and add it to the display.
        // Often you'll simply put your own payload, e.g. JSON, in the binaryAttachment,
        // so you won't need to inspect the message contents like this.
        messageAdd(message) {
            this.messages.unshift({
                date: new Date(),
                format: message.getSdtContainer() ? 'sdtText' : 'binaryAttachment',
                message
            });

            if (this.messages.length > MAX_MESSAGES) this.messages.length = MAX_MESSAGES;
        },

        // Display the message content using the message's selected format.
        messageContent(messageWrapper) {
            const { format, message } = messageWrapper;
            if (format === 'sdtText') {
                const container = message.getSdtContainer();
                return [container && container.getValue() || '<empty>'];
            }
            return [message.getBinaryAttachment() || ''];
        },

        // Removes a message from the display.
        messageRemove(index) {
            this.messages.splice(index, 1);
        },

        // Sets a status popup message.
        setStatus(type, message) {
            const statusNode = this.tabs[type].status;
            Object.assign(statusNode, {
                show: true,
                message
            });

            this.sessionRequestCompleted();
        },

        showSessionRequestPending() {
            this.sessionRequestPending = true;
        },

        sessionRequestCompleted() {
            this.sessionRequestPending = false;
        },

        // Sets the active tab to the given tab name.
        setActiveTab(selection) {
            Object.keys(this.tabs).forEach(tabName => {
                const tab = this.tabs[tabName];
                tab.active = tabName === selection;
            });
        },

        // toggle the advanced settings
        toggleAdvancedSettings() {
            this.showAdvancedSettings = !this.showAdvancedSettings;
        },

        // Sets the session credentials using the enumerable properties provided.
        setSessionCredentials(properties) {
            Object.assign(this.tabs.session.form, properties);
        }
    },



    mounted() {
        document.getElementById('vue-loading').remove();
        this.init();
    }
});