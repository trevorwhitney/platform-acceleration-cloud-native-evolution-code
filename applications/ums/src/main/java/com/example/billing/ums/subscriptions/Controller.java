package com.example.billing.ums.subscriptions;

import com.example.billing.billing.BillingClient;
import com.example.billing.email.SendEmail;
import com.example.billing.subscriptions.CreateSubscription;
import com.example.billing.subscriptions.Subscription;
import com.example.billing.subscriptions.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/subscriptions")
public class Controller {

    private final BillingClient billingClient;
    private final SubscriptionRepository subscriptions;

    @Autowired
    public Controller(SubscriptionRepository subscriptions, BillingClient billingClient) {
        this.subscriptions = subscriptions;
        this.billingClient = billingClient;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Subscription> index() {
        return subscriptions.all();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody Map<String, String> params) {
        SendEmail emailSender = new SendEmail();

        new CreateSubscription(billingClient, emailSender, subscriptions)
                .run(params.get("userId"), params.get("packageId"));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
