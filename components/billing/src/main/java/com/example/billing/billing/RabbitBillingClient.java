package com.example.billing.billing;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class RabbitBillingClient implements BillingClient {

    private final RabbitTemplate rabbitTemplate;
    private final String queueName;

    @Autowired
    public RabbitBillingClient(RabbitTemplate rabbitTemplate, String queueName) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueName = queueName;
    }

    @Override
    public void billUser(String userId, Integer amountToBill) {
        BillingMessage billingMessage = new BillingMessage(userId, amountToBill);

        rabbitTemplate.convertAndSend(queueName, billingMessage);
    }
}
