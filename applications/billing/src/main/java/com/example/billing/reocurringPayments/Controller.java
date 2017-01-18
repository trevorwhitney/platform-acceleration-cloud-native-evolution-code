package com.example.billing.reocurringPayments;

import com.example.billing.payments.Gateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final Gateway paymentGateway;

    @Autowired
    public Controller(Gateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    @RequestMapping(path = "/reocurringPayments", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> createReocurringPayment(@RequestBody Integer paymentMonthlyAmount) {
        return paymentGateway.createReocurringPayment(paymentMonthlyAmount) ?
                new ResponseEntity<>("{errors: []}", HttpStatus.CREATED) :
                new ResponseEntity<>("{errors: []}", HttpStatus.BAD_REQUEST);
    }
}
