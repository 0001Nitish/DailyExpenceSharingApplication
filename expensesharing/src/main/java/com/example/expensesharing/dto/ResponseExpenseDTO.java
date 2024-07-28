package com.example.expensesharing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseExpenseDTO {
    private double amount;
    private String description;
    private List<String> participantEmails;
    private SplitStrategyType splitStrategy;
    private Map<String, Double> splitAmounts;
}