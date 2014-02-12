activemq-test
=============

Grails Activemq Test Project


ActiveMQ
========

http://activemq.apache.org/


Setting Up ActiveMQ Locally:
brew install apache-activemq
Installs in:
/usr/local/Cellar/activemq/5.8.0

activemq script in bin directory used to start and stop
bin/activemq start
bin/activemq stop


ActiveMQ Simple Security setup.
In the configuration file you can add code similar to the following to your broker definition to add some simple security.
The default location for the config file using brew is: /usr/local/Cellar/activemq/5.8.0/libexec/conf/activemq.xml

   <destinations>
      <queue physicalName="FOO.BAR" />
      <topic physicalName="SOME.TOPIC" />
    </destinations>

    <plugins>
    <simpleAuthenticationPlugin>
    	<users>
	        <authenticationUser username="system" password="manager" groups="users,admins"/>
		    <authenticationUser username="admin" password="admin" groups="users,admins"/>
        	<authenticationUser username="user" password="password" groups="users"/>
        	<authenticationUser username="guest" password="password" groups="guests"/>
    	</users>
	</simpleAuthenticationPlugin>


      <!--  lets configure a destination based authorization mechanism -->
      <authorizationPlugin>
        <map>
          <authorizationMap>
            <authorizationEntries>
              <authorizationEntry queue="FOO.BAR" read="users" write="users" admin="admins" />
              <authorizationEntry queue=">" read="admins" write="admins" admin="admins" />


              <authorizationEntry topic="SOME.TOPIC" read="users" write="users" admin="admins" />
			  <authorizationEntry topic="ActiveMQ.Advisory.>" read="guests,users" write="guests,users" admin="guests,users"/>
			  <authorizationEntry topic=">" read="admins" write="admins" admin="admins" />
            </authorizationEntries>

            <!-- let's assign roles to temporary destinations. comment this entry if we don't want any roles assigned to temp destinations  -->
            <tempDestinationAuthorizationEntry>
              <tempDestinationAuthorizationEntry read="tempDestinationAdmins" write="tempDestinationAdmins" admin="tempDestinationAdmins"/>
           </tempDestinationAuthorizationEntry>
          </authorizationMap>
        </map>
      </authorizationPlugin>

    </plugins>







Queues and Topics are referred to as "Destinations"

By default Destinations are automatically created when you send a message to them (http://activemq.apache.org/how-do-i-create-new-destinations.html).

You can configure Destinations to be created on startup (http://activemq.apache.org/configure-startup-destinations.html).

You can also prevent Destinations from automatically being created via security settings (http://activemq.apache.org/security.html)


Admin Page:
http://localhost:8161/admin/
default username/password is admin/admin

On the Admin Page you can manage Destinations, Durable Subscribers and send messages.



Grails Communicating With ActiveMQ
==================================

Two Plugins that I used (added in BuildConfig.groovy plugins section):

    compile ":routing:1.2.9" //provides apache camel integration

    compile ':routing-jms:1.2.0' //routing-jms basically facilitates a connection to activemq through (grails.plugin.routing.jms.brokerURL) property in Config.groovy



