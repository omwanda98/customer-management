package com.practical.PROJECT.repository;

import com.practical.PROJECT.model.Account;
import com.practical.PROJECT.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByCustomerId(String customerId);

    Account findByAccountNumber(String targetAccountNumber);
}
