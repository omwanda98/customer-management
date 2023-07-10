package com.practical.PROJECT.service;

import com.practical.PROJECT.dto.CustomerDTO;
import com.practical.PROJECT.model.Account;
import com.practical.PROJECT.model.Customer;
import com.practical.PROJECT.repository.AccountRepository;
import com.practical.PROJECT.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer registerCustomer(CustomerDTO customerDTO) {
        // Create a new Customer entity from the DTO
        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setCustomerId(customerDTO.getCustomerId());

        // Generate random customer PIN and account
        String pin = generateRandomPin();
        String accountNumber = generateRandomAccountNumber();

        customer.setPin(pin);
        customer.setAccountNumber(accountNumber);
        customer.setBalance(0.0); // Initialize balance with zero

        // Save the customer entity
        return customerRepository.save(customer);
    }


    private String generateRandomPin() {
        // Generate and return a random PIN implementation using UUID
        return UUID.randomUUID().toString();
    }

    private String generateRandomAccountNumber() {
        // Generate and return a random account number implementation using UUID
        return UUID.randomUUID().toString();
    }


}