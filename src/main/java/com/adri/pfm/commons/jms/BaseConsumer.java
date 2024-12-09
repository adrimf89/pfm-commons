package com.adri.pfm.commons.jms;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;
import org.springframework.jms.support.converter.MessageConversionException;

@Slf4j
public abstract class BaseConsumer implements JmsListenerConfigurer, MessageListener {

    public static final String ENDPOINT_ID = "ConsumerJmsEnpoint-";

    @Override
    public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
        SimpleJmsListenerEndpoint simpleJmsListenerEndpoint = new SimpleJmsListenerEndpoint();
        simpleJmsListenerEndpoint.setId(ENDPOINT_ID+getConsumerQueue());
        simpleJmsListenerEndpoint.setDestination(getConsumerQueue());
        simpleJmsListenerEndpoint.setMessageListener(this);
        registrar.registerEndpoint(simpleJmsListenerEndpoint);
        log.info("Registered endpoint [{}]", getConsumerQueue());
    }

    @Override
    public void onMessage(Message message) {
        log.info("JmsMessage received for queue {}", getConsumerQueue());
        log.debug("Message: {}", message);
        try {
            processMessage(message);
        } catch (JMSException e) {
            log.error("Error JMS exception on message: {}", message, e);
        } catch (MessageConversionException e) {
            log.error("Error MessageConversion exception parsing message: {}", message, e);
        }
    }

    protected abstract String getConsumerQueue();
    protected abstract void processMessage(Message message) throws JMSException, MessageConversionException;
}
