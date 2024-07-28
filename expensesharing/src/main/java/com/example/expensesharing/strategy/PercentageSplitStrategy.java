package com.example.expensesharing.strategy;

import com.example.expensesharing.model.User;

import java.util.List;
import java.util.Map;

public class PercentageSplitStrategy implements ExpenseSplitStrategy {
    private final List<Double> percentages;

    public PercentageSplitStrategy(List<Double> percentages) {
        if (percentages.stream().mapToDouble(Double::doubleValue).sum() != 100.0) {
            throw new IllegalArgumentException("Percentages must add up to 100%");
        }
        this.percentages = percentages;
    }

    @Override
    public void split(double totalAmount, List<User> users, Map<String, Double> splitAmounts) {
        for (int i = 0; i < users.size(); i++) {
            splitAmounts.put(users.get(i).getEmail(), (totalAmount * percentages.get(i)) / 100);
        }
    }
}
