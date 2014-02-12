package com.sample

import org.apache.camel.builder.RouteBuilder

class SecuredQueueReceiverRoute extends RouteBuilder {

    @Override
    void configure() throws Exception {
        from('activemq:FOO.BAR?username=user&password=password').to('stream:out')
    }
}
