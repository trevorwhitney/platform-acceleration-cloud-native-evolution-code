package com.example.billing.billing;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class BillingMessageSerializer extends JsonSerializer<BillingMessage> {
    @Override
    public void serialize(
            BillingMessage billingMessage,
            JsonGenerator gen,
            SerializerProvider serializers
    ) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeStringField("userId", billingMessage.getUserId());
        gen.writeNumberField("paymentAmount", billingMessage.getPaymentAmount());
        gen.writeEndObject();
    }
}
