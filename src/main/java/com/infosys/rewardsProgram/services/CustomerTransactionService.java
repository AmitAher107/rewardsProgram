package com.infosys.rewardsProgram.services;

import com.infosys.rewardsProgram.model.Transaction;
import com.infosys.rewardsProgram.repo.CustomerRepository;
import com.infosys.rewardsProgram.repo.TransactionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerTransactionService {

    @Autowired
    private final TransactionRepository transactionRepository;

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerTransactionService(TransactionRepository transactionRepository, CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * Get all transactions for a customer.
     *
     * @param customerId the ID of the customer whose transactions are to be retrieved.
     * @return a list of transactions for the specified customer.
     * @throws IllegalArgumentException if no transactions are found for the customer.
     */

    public List<Transaction> getTransactions(Long customerId) {
        List<Transaction> byCustomerId = transactionRepository.findByCustomerId(customerId);
        if (byCustomerId.isEmpty()) {
            throw new IllegalArgumentException("No transactions found for customer");
        }
        return byCustomerId;
    }

    /**
     * Add a transaction for a customer.
     *
     * @param transaction the transaction to be added.
     * @return the added transaction.
     * @throws IllegalArgumentException if the customer is not found.
     */
    public Transaction addTransaction(Transaction transaction) {
        customerRepository.findById(transaction.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found,please register"));
        return transactionRepository.save(transaction);
    }

    /**
     * Edit a transaction for a customer.
     *
     * @param id the ID of the transaction to be edited.
     * @param transaction the transaction details to update.
     * @return the edited transaction.
     * @throws IllegalArgumentException if the customer or transaction is not found.
     */

    public Transaction editTransaction(Long id, Transaction transaction) {
        customerRepository.findById(transaction.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found,please register"));
        Transaction existingTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));
        existingTransaction.setAmount(transaction.getAmount());
        existingTransaction.setDate(transaction.getDate());
        return transactionRepository.save(existingTransaction);
    }

    /**
     * Delete a transaction for a customer.
     *
     * @param customerId the ID of the customer whose transaction is to be deleted.
     * @param id the ID of the transaction to be deleted.
     * @return a confirmation message indicating successful deletion.
     * @throws IllegalArgumentException if the customer or transaction is not found.
     */

    public String deleteTransaction(Long customerId, Long id) {
        customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found,please register"));
        transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));
        transactionRepository.deleteById(id);
        return "Transaction deleted successfully";
    }
}
