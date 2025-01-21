package com.infosys.rewardsProgram.controller;


import com.infosys.rewardsProgram.model.Transaction;
import com.infosys.rewardsProgram.services.CustomerTransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class CustomerTransactionController {


    @Autowired
    private CustomerTransactionService transactionService;

    /*
     * Get all transactions for a customer
     */
    @GetMapping("/getTransactions/{customerId}")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable Long customerId) {
        return ResponseEntity.ok(transactionService.getTransactions(customerId));
    }

    /*
     * Add a transaction for a customer
     */
    @PostMapping("/addTransaction")
    public ResponseEntity<Transaction> addTransaction(@Valid @RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionService.addTransaction(transaction));
    }

    /*
     * Edit a transaction for a customer
     */
    @PutMapping("/editTransaction/{id}")
    public ResponseEntity<Transaction> editTransaction(@PathVariable Long id, @Valid @RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionService.editTransaction(id, transaction));
    }

    /*
     * Delete a transaction for a customer
     */
    @DeleteMapping("/deleteTransaction/{id}/customerId/{customerId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id, @PathVariable Long customerId) {
        String result = transactionService.deleteTransaction(customerId,id);
        return ResponseEntity.ok(result);
    }
}