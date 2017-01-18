package com.example.billing.reocurringPayments;

import com.example.billing.payments.Gateway;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BillingMessageConsumer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Gateway paymentGateway;
    private final ObjectMapper objectMapper;

    public BillingMessageConsumer(Gateway paymentGateway) {
        this.paymentGateway = paymentGateway;
        this.objectMapper = new ObjectMapper();
    }

    @RabbitListener(queues = "${billingQueue}")
    public void process(Message message) throws IOException {
        Integer paymentAmount = extractPaymentAmount(message);

        if (paymentGateway.createReocurringPayment(paymentAmount)) {
            logger.info("Successfully posted payment of {} to payment gateway", paymentAmount);
        } else {
            logger.error("Unable to post payment to payment gateway");
        }
    }

    private Integer extractPaymentAmount(Message message) throws IOException {
        return objectMapper
                .readValue(message.getBody(), BillingMessage.class)
                .getPaymentAmount();
    }
}
