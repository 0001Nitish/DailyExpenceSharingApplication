package com.example.expensesharing.service;

import com.example.expensesharing.dto.RequestExpenseDTO;
import com.example.expensesharing.dto.ResponseExpenseDTO;
import com.example.expensesharing.dto.SplitStrategyType;
import com.example.expensesharing.model.Expense;
import com.example.expensesharing.model.User;
import com.example.expensesharing.repository.ExpenseRepository;
import com.example.expensesharing.strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private UserService userService;

    private final Map<SplitStrategyType, StrategyProvider> strategyProviders = new HashMap<>();

    public ExpenseServiceImpl() {
        strategyProviders.put(SplitStrategyType.EQUAL, new EqualStrategyProvider());
        strategyProviders.put(SplitStrategyType.EXACT, new ExactStrategyProvider());
        strategyProviders.put(SplitStrategyType.PERCENTAGE, new PercentageStrategyProvider());
    }

    @Override
    public ResponseExpenseDTO addExpense(RequestExpenseDTO expenseDTO) {
        Expense expense = new Expense();
        expense.setAmount(expenseDTO.getAmount());
        expense.setDescription(expenseDTO.getDescription());
        List<User> participants = new ArrayList<>();
        List<String> participantEmails = new ArrayList<>();
        for (String email : expenseDTO.getParticipantEmails()) {
            participants.add(userService.getUser(email));
            participantEmails.add(email);
        }
        ExpenseSplitStrategy strategy = strategyProviders.get(expenseDTO.getSplitStrategy()).getStrategy(expenseDTO);
        Map<String, Double> splitAmounts = new HashMap<>();
        strategy.split(expenseDTO.getAmount(), participants, splitAmounts);
        expense.setParticipants(participants);
        expense.setSplitAmounts(splitAmounts);
        expenseRepository.save(expense);
        ResponseExpenseDTO newExpenseDTO = new ResponseExpenseDTO();
        newExpenseDTO.setAmount(expense.getAmount());
        newExpenseDTO.setDescription(expense.getDescription());
        newExpenseDTO.setSplitAmounts(expense.getSplitAmounts());
        newExpenseDTO.setParticipantEmails(participantEmails);
        newExpenseDTO.setSplitStrategy(expenseDTO.getSplitStrategy());
        return newExpenseDTO;
    }

    @Override
    public List<ResponseExpenseDTO> getExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        List<ResponseExpenseDTO> expenseDTOs = new ArrayList<>();
        for (Expense expense : expenses) {
            ResponseExpenseDTO expenseDTO = new ResponseExpenseDTO();
            expenseDTO.setAmount(expense.getAmount());
            expenseDTO.setDescription(expense.getDescription());
            expenseDTO.setSplitAmounts(expense.getSplitAmounts());
            expenseDTOs.add(expenseDTO);
        }
        return expenseDTOs;
    }

    @Override
    public String generateBalanceSheet() {
        List<Expense> expenses = expenseRepository.findAll();
        Map<String, Double> balanceSheet = new HashMap<>();
        for (Expense expense : expenses) {
            for (Map.Entry<String, Double> entry : expense.getSplitAmounts().entrySet()) {
                balanceSheet.put(entry.getKey(), balanceSheet.getOrDefault(entry.getKey(), 0.0) + entry.getValue());
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Double> entry : balanceSheet.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    public List<ResponseExpenseDTO> getIndividualExpenses(String email) {
        List<Expense> expenses = expenseRepository.findAll();
        List<ResponseExpenseDTO> individualExpenses = new ArrayList<>();
        for (Expense expense : expenses) {
            if (expense.getSplitAmounts().containsKey(email)) {
                ResponseExpenseDTO expenseDTO = new ResponseExpenseDTO();
                expenseDTO.setAmount(expense.getAmount());
                expenseDTO.setDescription(expense.getDescription());
                expenseDTO.setSplitAmounts(expense.getSplitAmounts());
                individualExpenses.add(expenseDTO);
            }
        }
        return individualExpenses;
    }

    public String generateOverallExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        double totalAmount = 0.0;
        for (Expense expense : expenses) {
            totalAmount += expense.getAmount();
        }
        return "Total Expenses: " + totalAmount;
    }

    public String downloadBalanceSheet() throws IOException {
        String balanceSheet = generateBalanceSheet();
        try (FileWriter fileWriter = new FileWriter("balance_sheet.txt")) {
            fileWriter.write(balanceSheet);
        }
        return "Balance sheet downloaded successfully.";
    }
}