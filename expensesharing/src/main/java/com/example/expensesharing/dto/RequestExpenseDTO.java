package com.example.expensesharing.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonDeserialize(using = RequestExpenseDTODeserializer.class)
public class RequestExpenseDTO {
    @Positive(message = "Amount must be positive")
    private double amount;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @NotEmpty(message = "Participant emails cannot be empty")
    private List<String> participantEmails;

    @NotNull(message = "Split strategy cannot be null")
    private SplitStrategyType splitStrategy;
}