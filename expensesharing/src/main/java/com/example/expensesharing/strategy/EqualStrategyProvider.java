package com.example.expensesharing.strategy;

import com.example.expensesharing.dto.RequestExpenseDTO;

public class EqualStrategyProvider implements StrategyProvider {
    @Override
    public ExpenseSplitStrategy getStrategy(RequestExpenseDTO expenseDTO) {
        return new EqualSplitStrategy();
    }
}