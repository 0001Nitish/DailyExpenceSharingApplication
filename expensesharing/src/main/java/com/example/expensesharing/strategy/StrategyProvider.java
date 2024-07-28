package com.example.expensesharing.strategy;

import com.example.expensesharing.dto.RequestExpenseDTO;

public interface StrategyProvider {
    ExpenseSplitStrategy getStrategy(RequestExpenseDTO expenseDTO);
}