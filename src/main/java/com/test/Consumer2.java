package com.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.naming.InitialContext;

public class Consumer2 {
	
	public static void main(String[] args)  {
		try {
			InitialContext initialContext = new InitialContext();
			ConnectionFactory cf = (ConnectionFactory) initialContext.lookup("ConnectionFactory");
			Connection conn = cf.createConnection();
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Topic topic = (Topic) initialContext.lookup("topic/exampleTopic");
			MessageConsumer consumer = session.createSharedDurableConsumer(topic, "exampleTopic");
			
			consumer.setMessageListener(message -> {
				new ConsumerListener("C2").onMessage(message);
			});
			
			conn.start();
		} catch (Exception e) {
			e.printStackTrace();
		} 		
	}

}
