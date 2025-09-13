package com.icici.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionMessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendTransactionMessage(String message) {
        rabbitTemplate.convertAndSend("transactionExchange", "transactionRoutingKey", message);
    }
}

