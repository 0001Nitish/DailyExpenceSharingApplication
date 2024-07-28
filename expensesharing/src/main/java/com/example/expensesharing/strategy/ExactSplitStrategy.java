package com.example.expensesharing.strategy;

import com.example.expensesharing.model.User;

import java.util.List;
import java.util.Map;

public class ExactSplitStrategy implements ExpenseSplitStrategy {
    private List<Double> amounts;

    public ExactSplitStrategy(List<Double> amounts) {
        this.amounts = amounts;
    }

    @Override
    public void split(double totalAmount, List<User> users, Map<String, Double> splitAmounts) {
        for (int i = 0; i < users.size(); i++) {
            splitAmounts.put(users.get(i).getEmail(), amounts.get(i));
        }
    }
}
