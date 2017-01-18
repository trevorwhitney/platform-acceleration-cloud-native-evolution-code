package com.example.billing.payments;

public class RecurlyGateway implements Gateway {
    public boolean createReocurringPayment(int paymentMonthlyAmount){
        return true;
    }
}
