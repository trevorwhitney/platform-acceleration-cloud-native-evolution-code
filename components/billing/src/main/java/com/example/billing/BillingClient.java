package com.example.billing;

import org.springframework.web.client.RestTemplate;

public class BillingClient {

    private final RestTemplate restTemplate;

    public BillingClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void billUser(String userId, Integer amountToBill) {
        restTemplate.postForEntity(
                "//billing/reocurringPayments",
                amountToBill,
                String.class
        );
    }
}
