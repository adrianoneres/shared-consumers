package com.test;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ConsumerListener implements MessageListener {
	private String consumerName;

	public ConsumerListener(String consumerName) {
		this.consumerName = consumerName;
	}

	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			System.out.println(consumerName + ": " + textMessage.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
