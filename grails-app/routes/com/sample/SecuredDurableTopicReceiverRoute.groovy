package com.sample

import org.apache.camel.builder.RouteBuilder

class SecuredDurableTopicReceiverRoute extends RouteBuilder {

    @Override
    void configure() throws Exception {
        /*
         * Refer to the camel jms documentation for more detailed information about camel:
         * https://camel.apache.org/jms.html
         *
         * In short if you want to use a Durable Subscriber via ActiveMQ
         * When you connect you have to supply the clientId and durableSubscriptionName
         */
        from('activemq:topic:SOME.TOPIC?clientId=1234&durableSubscriptionName=activemqtest&username=user&password=password').to('stream:out')
    }
}
