package com.chinesedreamer.ipm.message.center.mq.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.chinesedreamer.ipm.common.base.SpringTest;

/**
 * Description: 
 * @author Paris
 * @date Dec 23, 201410:35:21 AM
 * @version beta
 */
public class QueueConsumerServiceImplTest extends SpringTest{
	@Resource
	private QueueConsumerService queueConsumerService;
	@Resource
	private QueueProducerService queueProducerService;
	@Test
	public void testConsume() {
		this.queueProducerService.produce();
		this.queueConsumerService.consume();
	}

}
