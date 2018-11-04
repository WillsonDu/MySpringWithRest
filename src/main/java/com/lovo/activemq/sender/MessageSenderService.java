package com.lovo.activemq.sender;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Repository;

@Repository(value="messageSenderService")
public class MessageSenderService {

	@Resource(name = "demoQueueDestination")
	private Destination destination;

	@Resource(name = "jmsTemplate")
	private JmsTemplate jmsTemplate;

	public void sendMessage(final String message) {
		getJmsTemplate().send(getDestination(), new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

}
