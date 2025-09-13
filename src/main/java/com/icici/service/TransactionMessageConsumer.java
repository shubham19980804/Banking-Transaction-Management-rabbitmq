package com.icici.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionMessageConsumer {

    @RabbitListener(queues = "transactionQueue")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
        // You can process the message or trigger some actions based on the message
    }
}
