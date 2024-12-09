package com.adri.pfm.commons.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;

@Slf4j
public abstract class BaseProducer<Message> {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private MessageConverter messageConverter;

    public void send(Message message){
        validateMessage(message);
        log.info("Send message {} to {}", message, getProducerQueue());
        jmsTemplate.setMessageConverter(messageConverter);
        jmsTemplate.convertAndSend(getProducerQueue(), message);
    }

    protected abstract void validateMessage(Message message) throws IllegalArgumentException;
    protected abstract String getProducerQueue();
}
