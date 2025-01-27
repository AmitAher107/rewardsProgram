package com.infosys.rewardsProgram.controller;

import com.infosys.rewardsProgram.model.Customer;
import com.infosys.rewardsProgram.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /*
        Api for registration
     */
    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.registerCustomer(customer));
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> loginCustomer(@RequestParam String email, @RequestParam String password) {
//        return ResponseEntity.ok(customerService.loginCustomer(email, password));
//    }

//    @PostMapping("/logoff")
//    public ResponseEntity<Void> logoffCustomer(@RequestParam Long customerId) {
//        customerService.logoffCustomer(customerId);
//        return ResponseEntity.ok().build();
//    }
}