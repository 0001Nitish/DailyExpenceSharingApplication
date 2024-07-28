package com.example.expensesharing.dto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExactSplitRequestDTO extends RequestExpenseDTO {
    @NotEmpty(message = "Amounts cannot be empty")
    private List<@NotNull Double> amounts;

    public ExactSplitRequestDTO(JsonNode node) {
        // Initialize the fields from the JsonNode
        this.amounts = new ObjectMapper().convertValue(node.get("amounts"), new TypeReference<List<Double>>() {});
        this.setAmount(node.get("amount").asDouble());
        this.setDescription(node.get("description").asText());
        this.setParticipantEmails(new ObjectMapper().convertValue(node.get("participantEmails"), new TypeReference<List<String>>() {}));
        this.setSplitStrategy(SplitStrategyType.valueOf(node.get("splitStrategy").asText()));
    }
}