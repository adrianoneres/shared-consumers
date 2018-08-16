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
		TextMessage textMessage = (TextMessage) message;
		try {
			Thread.sleep(1000);
			System.out.println(consumerName + ": " + textMessage.getText());
		} catch (JMSException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
