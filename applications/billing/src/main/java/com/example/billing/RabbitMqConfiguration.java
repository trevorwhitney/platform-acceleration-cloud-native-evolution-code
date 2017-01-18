package com.example.billing;

import com.example.billing.payments.Gateway;
import com.example.billing.payments.RecurlyGateway;
import com.example.billing.reocurringPayments.BillingMessageConsumer;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

    @Value("${billingQueue}")
    private String queueName;

    @Bean
    public Queue simpleQueue() {
        return new Queue(queueName, false);
    }

    @Bean
    public Gateway paymentGateway() {
        return new RecurlyGateway();
    }

    @Bean
    public BillingMessageConsumer billingMessageConsumer(@Autowired Gateway paymentGateway) {
        return new BillingMessageConsumer(paymentGateway);
    }
}
