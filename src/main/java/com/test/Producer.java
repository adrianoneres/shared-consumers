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
			InitialContext initialContext = new InitialContext();
			ConnectionFactory cf = (ConnectionFactory) initialContext.lookup("ConnectionFactory");
			Connection conn = cf.createConnection();
		
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Topic topic = (Topic) initialContext.lookup("topic/exampleTopic");
			MessageProducer producer = session.createProducer(topic);
			
			Message first = session.createTextMessage("First message.");
			Message second = session.createTextMessage("Second message.");
			Message third = session.createTextMessage("Third message.");
			Message fourth = session.createTextMessage("Fourth message.");
			
			producer.send(first);
//			producer.send(second);
//			producer.send(third);
//			producer.send(fourth);
			
			session.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
