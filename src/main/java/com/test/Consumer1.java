package com.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.naming.InitialContext;

public class Consumer1 {
	
	public static void main(String[] args)  {
		try {
			//Lookup for "java.naming.factory.initial" parameter configured from jndi.properties
			InitialContext initialContext = new InitialContext();
			ConnectionFactory cf = (ConnectionFactory) initialContext.lookup("ConnectionFactory");
			
			//Create a connection
			Connection conn = cf.createConnection();
			
			//Create a session with auto-acknowledge
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			//Lookup for "topic.topic/exampleTopic" parameter from jndi.properties and create a topic reference
			Topic topic = (Topic) initialContext.lookup("topic/exampleTopic");
			
			//Create a message shared consumer for the previously created topic
			MessageConsumer consumer = session.createSharedDurableConsumer(topic, "exampleTopic");
			
			
			//Configure a message listener to this consumer
			consumer.setMessageListener(message -> {
				new ConsumerListener("Consumer1").onMessage(message);
			});
			
			//Start the connection
			conn.start();
		} catch (Exception e) {
			e.printStackTrace();
		} 		
	}

}
