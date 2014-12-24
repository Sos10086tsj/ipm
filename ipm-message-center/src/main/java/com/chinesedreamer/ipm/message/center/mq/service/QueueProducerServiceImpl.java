package com.chinesedreamer.ipm.message.center.mq.service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

/**
 * Description: 
 * @author Paris
 * @date Dec 23, 201410:27:20 AM
 * @version beta
 */
@Service
public class QueueProducerServiceImpl implements QueueProducerService{
	
	@Autowired
	private JmsTemplate jmsQueueTemplate;
	@Autowired
	private Destination queueDest;
	
	@Override
	public void produce() {
		MessageCreator messageCreator = new MessageCreator() {
			
			@Override
			public Message createMessage(Session arg0) throws JMSException {
				TextMessage message = arg0.createTextMessage();
				message.setText("QueueProducerService is producing..." + System.currentTimeMillis());
				return message;
			}
		};
		jmsQueueTemplate.send(this.queueDest, messageCreator);
	}

}
