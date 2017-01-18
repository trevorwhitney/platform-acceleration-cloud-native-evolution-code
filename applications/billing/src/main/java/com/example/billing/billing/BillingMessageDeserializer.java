package com.example.billing.billing;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class BillingMessageDeserializer extends StdDeserializer<BillingMessage> {
    public BillingMessageDeserializer() {
        this(null);
    }

    public BillingMessageDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public BillingMessage deserialize(
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        final String userId = node.get("userId").asText();
        final Integer paymentAmount = node.get("paymentAmount").asInt();

        return new BillingMessage(userId, paymentAmount);
    }
}
