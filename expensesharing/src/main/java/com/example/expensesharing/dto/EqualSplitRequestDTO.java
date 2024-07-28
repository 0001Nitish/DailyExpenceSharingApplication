package com.example.expensesharing.dto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@Getter
@Setter
public class EqualSplitRequestDTO extends RequestExpenseDTO {
    // No additional fields needed for equal split
    public EqualSplitRequestDTO(JsonNode node) {
        // Initialize the fields from the JsonNode
        this.setAmount(node.get("amount").asDouble());
        this.setDescription(node.get("description").asText());
        this.setParticipantEmails(new ObjectMapper().convertValue(node.get("participantEmails"), new TypeReference<List<String>>() {}));
        this.setSplitStrategy(SplitStrategyType.valueOf(node.get("splitStrategy").asText()));
    }
}