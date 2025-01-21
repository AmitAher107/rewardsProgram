package com.infosys.rewardsProgram.services;

import com.infosys.rewardsProgram.model.Customer;
import com.infosys.rewardsProgram.model.Transaction;
import com.infosys.rewardsProgram.repo.CustomerRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterCustomerSuccess() {
        Customer customer = new Customer();
        customer.setEmail("amit@gmail.com");

        when(customerRepository.findByEmail(customer.getEmail())).thenReturn(Optional.empty());
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer result = customerService.registerCustomer(customer);

        assertNotNull(result);
        verify(customerRepository).save(customer);
    }

    @Test
    public void testRegisterCustomerEmailAlreadyRegistered() {
        Customer customer = new Customer();
        customer.setEmail("amit@gmail.com");

        when(customerRepository.findByEmail(customer.getEmail())).thenReturn(Optional.of(customer));

        assertThrows(IllegalArgumentException.class, () -> customerService.registerCustomer(customer));
    }

    @Test
    public void testLoginCustomerSuccess() {
        String email = "amit@gmail.com";
        String password = "amit";
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setPassword(password);

        when(customerRepository.findByEmail(email)).thenReturn(Optional.of(customer));

        Customer result = customerService.loginCustomer(email, password);

        assertNotNull(result);
        assertEquals(email, result.getEmail());
        assertEquals(password, result.getPassword());
    }

    @Test
    public void testLoginCustomerInvalidCredentials() {
        String email = "amit@gmail.com";
        String password = "amit";

        when(customerRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> customerService.loginCustomer(email, password));
    }

    @Test
    public void testGetCustomerSuccess() {
        Long id = 1L;
        Customer customer = new Customer();
        customer.setId(id);

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        Customer result = customerService.getCustomer(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    public void testGetCustomerNotFound() {
        Long id = 1L;

        Customer customer = new Customer();
        customer.setId(2L);
        customer.setEmail("xy@gmail.com");

        List<Customer> customers = List.of(customer);
        when(customerRepository.findAll()).thenReturn(customers);
//        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> customerService.getCustomer(id));
    }
}