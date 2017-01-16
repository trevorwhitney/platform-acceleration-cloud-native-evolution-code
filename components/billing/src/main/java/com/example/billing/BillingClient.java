package com.example.billing;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static java.util.Arrays.asList;

public class BillingClient {

    private final String billingUrl;

    public BillingClient(String billingUrl) {
        this.billingUrl = billingUrl;
    }

    public void billUser(String userId, Integer amountToBill) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(asList(MediaType.APPLICATION_JSON));

        RequestEntity<String> request = new RequestEntity<>(
                Integer.toString(amountToBill),
                httpHeaders,
                HttpMethod.POST,
                URI.create(billingUrl)
        );

        restTemplate.postForEntity(billingUrl, request, String.class);
    }
}
