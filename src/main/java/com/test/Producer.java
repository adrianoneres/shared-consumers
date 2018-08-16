package com.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.naming.InitialContext;

public class Producer {
	
	public static void main(String[] args) {
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
			
			//Create a producer for the previously created topic
			MessageProducer producer = session.createProducer(topic);
			
			//Send a message to the topic
			Message first = session.createTextMessage("First message.");
//			Message second = session.createTextMessage("Second message.");
			
			producer.send(first);
//			producer.send(second);
			
			//Close session and connection
			session.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
