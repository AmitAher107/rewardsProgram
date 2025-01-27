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
    /**
     * Get all transactions for a customer.
     *
     * @param customerId the ID of the customer whose transactions are to be retrieved.
     * @return ResponseEntity containing a list of transactions for the specified customer.
     */
    @GetMapping("/getTransactions/{customerId}")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable Long customerId) {
        return ResponseEntity.ok(transactionService.getTransactions(customerId));
    }

    /**
     * Add a transaction for a customer.
     *
     * @param transaction the transaction to be added.
     * @return ResponseEntity containing the added transaction.
     */
    @PostMapping("/addTransaction")
    public ResponseEntity<Transaction> addTransaction(@Valid @RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionService.addTransaction(transaction));
    }

    /**
     * Edit a transaction for a customer.
     *
     * @param id the ID of the transaction to be edited.
     * @param transaction the transaction details to update.
     * @return ResponseEntity containing the edited transaction.
     */
    @PutMapping("/editTransaction/{id}")
    public ResponseEntity<Transaction> editTransaction(@PathVariable Long id, @Valid @RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionService.editTransaction(id, transaction));
    }

    /**
     * Delete a transaction for a customer.
     *
     * @param id the ID of the transaction to be deleted.
     * @param customerId the ID of the customer whose transaction is to be deleted.
     * @return ResponseEntity containing the result of the deletion.
     */
    @DeleteMapping("/deleteTransaction/{id}/customerId/{customerId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id, @PathVariable Long customerId) {
        String result = transactionService.deleteTransaction(customerId,id);
        return ResponseEntity.ok(result);
    }
}