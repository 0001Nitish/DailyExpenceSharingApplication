package com.example.expensesharing.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class RequestExpenseDTODeserializer extends JsonDeserializer<RequestExpenseDTO> {

    @Override
    public RequestExpenseDTO deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode root = p.getCodec().readTree(p);

        if (root == null) {
            throw new IOException("Root node is null");
        }

        // Determine the type based on the presence of certain fields
        if (root.has("percentages")) {
            // Deserialize into PercentageSplitRequestDTO
            JsonNode percentagesNode = root.get("percentages");
            // Assuming PercentageSplitRequestDTO has a constructor or method to handle JsonNode
            return new PercentageSplitRequestDTO(root);
        } else if (root.has("amounts")) {
            // Deserialize into ExactSplitRequestDTO
            JsonNode amountsNode = root.get("amounts");
            // Assuming ExactSplitRequestDTO has a constructor or method to handle JsonNode
            return new ExactSplitRequestDTO(root);
        } else {
            // Default to EqualSplitRequestDTO
            return new EqualSplitRequestDTO(root);
        }
    }
}