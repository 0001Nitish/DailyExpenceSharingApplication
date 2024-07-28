package com.example.expensesharing.strategy;

import com.example.expensesharing.model.User;

import java.util.List;
import java.util.Map;

public interface ExpenseSplitStrategy {
    void split(double totalAmount, List<User> users, Map<String, Double> splitAmounts);
}
