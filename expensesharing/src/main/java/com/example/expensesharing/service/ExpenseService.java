package com.example.expensesharing.service;

import com.example.expensesharing.dto.RequestExpenseDTO;
import com.example.expensesharing.dto.ResponseExpenseDTO;

import java.io.IOException;
import java.util.List;

public interface ExpenseService {
    ResponseExpenseDTO addExpense(RequestExpenseDTO requestExpenseDTO);
    List<ResponseExpenseDTO> getExpenses();
    String generateBalanceSheet();
    String generateOverallExpenses();
    String downloadBalanceSheet() throws IOException;
    List<ResponseExpenseDTO> getIndividualExpenses(String email);
}