package com.sample

import org.apache.camel.builder.RouteBuilder

class TopicReceiverRoute extends RouteBuilder {

    @Override
    void configure() throws Exception {
        /*
         * Refer to the camel jms documentation for more detailed information about camel:
         * https://camel.apache.org/jms.html
         *
         * In short you configure a Durable Subscriber via ActiveMQ
         * When you connect you have to supply the clientId and durableSubscriptionName
         */
        from('activemq:topic:TestTopic').to('stream:out')
    }
}
