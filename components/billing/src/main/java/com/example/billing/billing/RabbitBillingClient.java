package com.example.billing.billing;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class RabbitBillingClient implements BillingClient {

    private final RabbitMessagingTemplate rabbitTemplate;
    private final String queueName;

    @Autowired
    public RabbitBillingClient(RabbitMessagingTemplate rabbitTemplate, String queueName) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueName = queueName;
    }

    @Override
    public void billUser(String userId, Integer amountToBill) {
        BillingMessage billingMessage = new BillingMessage(userId, amountToBill);

        rabbitTemplate.convertAndSend(queueName, billingMessage);
    }
}
