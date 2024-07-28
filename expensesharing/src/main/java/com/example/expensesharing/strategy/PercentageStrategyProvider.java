package com.example.expensesharing.strategy;

import com.example.expensesharing.dto.PercentageSplitRequestDTO;
import com.example.expensesharing.dto.RequestExpenseDTO;

public class PercentageStrategyProvider implements StrategyProvider {
    @Override
    public ExpenseSplitStrategy getStrategy(RequestExpenseDTO requestExpenseDTO) {
        if (requestExpenseDTO instanceof PercentageSplitRequestDTO) {
            return new PercentageSplitStrategy(((PercentageSplitRequestDTO) requestExpenseDTO).getPercentages());
        } else {
            throw new IllegalArgumentException("Invalid DTO type for PERCENTAGE strategy");
        }
    }
}