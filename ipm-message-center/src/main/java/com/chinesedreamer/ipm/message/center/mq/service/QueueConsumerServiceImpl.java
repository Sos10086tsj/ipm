package com.chinesedreamer.ipm.message.center.mq.service;

import javax.jms.Destination;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * Description: 
 * @author Paris
 * @date Dec 23, 201410:32:23 AM
 * @version beta
 */
@Service
public class QueueConsumerServiceImpl implements QueueConsumerService{
	@Autowired
	private JmsTemplate jmsQueueTemplate;
	@Autowired
	private Destination queueDest;
	
	@Override
	public void consume() {
		TextMessage message = (TextMessage) jmsQueueTemplate.receive();
		try {
			System.err.println("QueueConsumerService 消费：" + message.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
