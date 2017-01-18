package com.example.billing.billing;

public interface BillingClient {
    void billUser(String userId, Integer amountToBill);
}
