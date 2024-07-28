package com.example.expensesharing.strategy;

import com.example.expensesharing.dto.ExactSplitRequestDTO;
import com.example.expensesharing.dto.RequestExpenseDTO;

public class ExactStrategyProvider implements StrategyProvider {
    @Override
    public ExpenseSplitStrategy getStrategy(RequestExpenseDTO expenseDTO) {
        if (expenseDTO instanceof ExactSplitRequestDTO) {
            return new ExactSplitStrategy(((ExactSplitRequestDTO) expenseDTO).getAmounts());
        } else {
            throw new IllegalArgumentException("Invalid DTO type for EXACT strategy");
        }
    }
}