package com.lovo.activemq.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.lovo.bean.ClassBean;

public class MessageConsumerService implements MessageListener {

	protected static final Logger log = Logger.getLogger(MessageConsumerService.class);

	@Override
	public void onMessage(final Message message) {
		if (message == null) {
			log.warn("Param pMessage is null");
			return;
		}
		log.info("接收到jms queue消息");
		try {
			final String msgStr = ((TextMessage) message).getText();
			log.info("接收到的JMS消息是: " + msgStr);
			final ClassBean classBean = JSON.parseObject(msgStr, ClassBean.class);
			log.info("将其转化为bean为: " + classBean);
		} catch (JMSException e) {
			log.error("JMSException发生", e);
		}
	}

}
