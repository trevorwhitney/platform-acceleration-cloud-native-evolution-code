package com.example.billing.billing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class BillingMessage implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BillingMessage that = (BillingMessage) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        return paymentAmount != null ? paymentAmount.equals(that.paymentAmount) : that.paymentAmount == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (paymentAmount != null ? paymentAmount.hashCode() : 0);
        return result;
    }
}
