package com.example.billing.reocurringPayments;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BillingMessage {

    @JsonProperty
    private String userId;
    @JsonProperty
    private Integer paymentAmount;

    public BillingMessage() {
    }

    public BillingMessage(String userId, Integer paymentAmount) {
        this.userId = userId;
        this.paymentAmount = paymentAmount;
    }

    public String getUserId() {
        return userId;
    }

    public Integer getPaymentAmount() {
        return paymentAmount;
    }


    @Override
    public String toString() {
        return "BillingMessage{" +
                "userId='" + userId + '\'' +
                ", paymentAmount=" + paymentAmount +
                '}';
    }
}
