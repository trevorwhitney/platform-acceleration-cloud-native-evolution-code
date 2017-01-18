package com.example.billing.billing;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class HttpBillingClient implements BillingClient {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RestTemplate restTemplate;

    public HttpBillingClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @HystrixCommand(fallbackMethod = "doNothing")
    public void billUser(String userId, Integer amountToBill) {
        restTemplate.postForEntity(
                "//billing/reocurringPayments",
                amountToBill,
                String.class
        );
    }

    public void doNothing(String userId, Integer amountToBill) {
        logger.error("Failed to post reocurring payment to billing service");
    }
}
