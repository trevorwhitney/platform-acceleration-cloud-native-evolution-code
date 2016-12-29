package com.example.billing;

import com.example.payments.Gateway;
import com.example.payments.RecurlyGateway;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCircuitBreaker
public class BillingApplication {

    @Bean
    BillingMessageReceiver receiver(Gateway paymentGateway) {
        return new BillingMessageReceiver(paymentGateway);
    }

    // The consumer owns the queue and should be started before the producer.
    @Bean
    Queue queue(@Value("${queueName}") String queueName) {
        return new Queue(queueName, false);
    }

    @Bean
    Gateway paymentGateway() {
        return new RecurlyGateway();
    }

    public static void main(String[] args) {
        SpringApplication.run(BillingApplication.class, args);
    }

}
