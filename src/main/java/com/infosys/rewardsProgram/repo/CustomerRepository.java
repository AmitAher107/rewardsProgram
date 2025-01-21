package com.infosys.rewardsProgram.repo;

import com.infosys.rewardsProgram.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByEmail(String email);

}
