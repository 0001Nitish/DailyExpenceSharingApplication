package com.example.expensesharing.controller;

import com.example.expensesharing.dto.RequestExpenseDTO;
import com.example.expensesharing.dto.ResponseExpenseDTO;
import com.example.expensesharing.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    /**
     * Adds a new expense.
     * @param expenseDTO the expense details
     * @return the added expense details
     */
    @PostMapping
    public ResponseEntity<ResponseExpenseDTO> addExpense(@Valid @RequestBody RequestExpenseDTO expenseDTO) {
        ResponseExpenseDTO responseExpenseDTO = expenseService.addExpense(expenseDTO);
        return ResponseEntity.ok(responseExpenseDTO);
    }

    /**
     * Retrieves all expenses.
     * @return a list of all expenses
     */
    @GetMapping
    public ResponseEntity<List<ResponseExpenseDTO>> getExpenses() {
        List<ResponseExpenseDTO> expenses = expenseService.getExpenses();
        return ResponseEntity.ok(expenses);
    }

    /**
     * Retrieves individual expenses for a specific user.
     * @param email the user's email
     * @return a list of expenses for the user
     */
    @GetMapping("/individual/{email}")
    public ResponseEntity<List<ResponseExpenseDTO>> getIndividualExpenses(@PathVariable String email) {
        List<ResponseExpenseDTO> individualExpenses = expenseService.getIndividualExpenses(email);
        return ResponseEntity.ok(individualExpenses);
    }

    /**
     * Retrieves the overall expenses for all users.
     * @return the total expenses
     */
    @GetMapping("/overall")
    public ResponseEntity<String> getOverallExpenses() {
        String overallExpenses = expenseService.generateOverallExpenses();
        return ResponseEntity.ok(overallExpenses);
    }

    /**
     * Downloads the balance sheet.
     * @return a message indicating the download status
     * @throws IOException if an I/O error occurs
     */
    @GetMapping("/download")
    public ResponseEntity<String> downloadBalanceSheet() throws IOException {
        String message = expenseService.downloadBalanceSheet();
        return ResponseEntity.ok(message);
    }
}