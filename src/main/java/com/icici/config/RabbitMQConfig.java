package com.icici.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Declare a queue
    @Bean
    public Queue queue() {
        return new Queue("transactionQueue", false);
    }

    // Declare a direct exchange
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("transactionExchange");
    }

    // Bind the queue to the exchange
    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("transactionRoutingKey");
    }
}
