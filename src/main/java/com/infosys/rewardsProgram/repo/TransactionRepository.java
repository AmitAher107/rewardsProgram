package com.infosys.rewardsProgram.repo;

import com.infosys.rewardsProgram.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    List<Transaction> findByCustomerId(Long customerId);

    @Query("SELECT DISTINCT t.customerId FROM Transaction t")
    List<Long> findAllUniqueCustomerIds();



}
