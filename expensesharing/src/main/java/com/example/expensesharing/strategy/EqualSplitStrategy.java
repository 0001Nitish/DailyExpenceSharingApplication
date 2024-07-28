package com.example.expensesharing.strategy;

import com.example.expensesharing.model.User;

import java.util.List;
import java.util.Map;

public class EqualSplitStrategy implements ExpenseSplitStrategy {
    @Override
    public void split(double totalAmount, List<User> users, Map<String, Double> splitAmounts) {
        double share = totalAmount / users.size();
        for (User user : users) {
            splitAmounts.put(user.getEmail(), share);
        }
    }
}
