package com.sample

import org.apache.activemq.ActiveMQConnectionFactory

import javax.jms.Connection
import javax.jms.DeliveryMode
import javax.jms.Destination
import javax.jms.MessageProducer
import javax.jms.Session
import javax.jms.TextMessage

class JMSController {

    def index() {}

    /**
     * Simple send message based off of example:
     * http://activemq.apache.org/hello-world.html
     */
    def sendMessage() {
        // Create a ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("system", "manager", "tcp://localhost:61616");

        // Create a Connection
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Create a Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create the destination (Topic or Queue)
        Destination destination = session.createQueue("FOO.BAR");

        // Create a MessageProducer from the Session to the Topic or Queue
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        // Create a messages from messageText input field in index.gsp
        TextMessage message = session.createTextMessage(params.messageText);

        // Tell the producer to send the message
        producer.send(message);

        // Clean up
        session.close();
        connection.close();
        redirect([controller:"JMS", action: "index"])
    }
}
