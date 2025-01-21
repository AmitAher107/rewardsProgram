package com.infosys.rewardsProgram.services;

import com.infosys.rewardsProgram.model.Customer;
import com.infosys.rewardsProgram.model.Transaction;
import com.infosys.rewardsProgram.repo.CustomerRepository;
import com.infosys.rewardsProgram.repo.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerTransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerTransactionService transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTransactionsSuccess() {
        Long customerId = 1L;
        Transaction transaction1 = new Transaction(1L, customerId, 120.0, LocalDate.of(2023, 1, 15));
        Transaction transaction2 = new Transaction(2L, customerId, 80.0, LocalDate.of(2023, 2, 20));

        List<Transaction> transactions = Arrays.asList(transaction1, transaction2);
        when(transactionRepository.findByCustomerId(customerId)).thenReturn(transactions);

        List<Transaction> result = transactionService.getTransactions(customerId);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(transactionRepository, times(1)).findByCustomerId(customerId);
    }

    @Test
    public void testGetTransactionsNoTransactionsFound() {
        Long customerId = 1L;
        when(transactionRepository.findByCustomerId(customerId)).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            transactionService.getTransactions(customerId);
        });

        assertEquals("No transactions found for customer", exception.getMessage());
        verify(transactionRepository, times(1)).findByCustomerId(customerId);
    }


    @Test
    public void testAddTransaction_Success() {
        Transaction transaction = new Transaction();
        transaction.setCustomerId(1L);
        when(customerRepository.findById(transaction.getCustomerId())).thenReturn(Optional.of(new Customer()));
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        Transaction result = transactionService.addTransaction(transaction);

        assertNotNull(result);
        assertEquals(transaction, result);
        verify(customerRepository, times(1)).findById(transaction.getCustomerId());
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    void editTransaction() {

    }

    @Test
    void deleteTransaction() {
    }
}