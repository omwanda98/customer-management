package com.practical.PROJECT.service;

import com.practical.PROJECT.dto.DepositDTO;
import com.practical.PROJECT.exceptions.InsufficientBalanceException;
import com.practical.PROJECT.model.Account;
import com.practical.PROJECT.model.Customer;
import com.practical.PROJECT.model.Transaction;
import com.practical.PROJECT.repository.AccountRepository;
import com.practical.PROJECT.repository.CustomerRepository;
import com.practical.PROJECT.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Transactional
public class AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    public Double getCustomerBalance(String customerId) throws NoSuchElementException {
        Customer account = customerRepository.findByCustomerId(String.valueOf(Long.valueOf(customerId)));
        if (account != null) {
            return account.getBalance();
        }
        throw new NoSuchElementException("Account not found for customer: " + customerId);
    }


    public List<Transaction> getCustomerMiniStatement(String customerId) {
        Customer account = customerRepository.findByCustomerId(String.valueOf(Long.valueOf(customerId)));
        if (account != null) {
            List<Transaction> transactions = transactionRepository.findTop10ByAccountIdOrderByTimestampDesc(account.getId());
            return transactions;
        }
        throw new NoSuchElementException("Account not found for customer: " + customerId);
    }

    public Double deposit(String customerId, DepositDTO depositDTO) {
        Customer account = customerRepository.findByCustomerId(String.valueOf(Long.valueOf(customerId)));
        if (account != null) {
            double amount = Double.parseDouble(depositDTO.getAmount());
            double newBalance = account.getBalance() + amount;
            account.setBalance(newBalance);
            customerRepository.save(account);

//            // Create and save the transaction
//            Transaction transaction = new Transaction();
//            transaction.setTransactionId(generateTransactionId());
//            transaction.setAmount(amount);
//            transaction.setTimestamp(new Date());
//            transaction.setAccount(account.getAccount()); // Assuming the Account object is accessible via getAccount() method
//            transactionRepository.save(transaction);

            return newBalance;
        }
        throw new NoSuchElementException("Account not found for customer: " + customerId);
    }



    private String generateTransactionId() {
        return UUID.randomUUID().toString();
    }


    public Double withdraw(String customerId, double amount) throws InsufficientBalanceException {
        Customer account = customerRepository.findByCustomerId(String.valueOf(Long.valueOf(customerId)));
        if (account != null) {
            if (account.getBalance() >= amount) {
                double newBalance = account.getBalance() - amount;
                account.setBalance(newBalance);
                customerRepository.save(account);
                return newBalance;
            } else {
                throw new InsufficientBalanceException("Insufficient balance for withdrawal");
            }
        }
        throw new NoSuchElementException("Account not found for customer: " + customerId);
    }


    public Double transferFunds(String sourceCustomerId, String targetAccountNumber, double amount) throws InsufficientBalanceException {
        Logger logger = LoggerFactory.getLogger(getClass());

        logger.info("Transferring funds from sourceCustomerId: {} to targetAccountNumber: {} with amount: {}", sourceCustomerId, targetAccountNumber, amount);

        Customer sourceAccount = customerRepository.findByCustomerId(String.valueOf(Long.valueOf(sourceCustomerId)));
        if (sourceAccount != null) {
            Customer targetAccount = customerRepository.findByAccountNumber(targetAccountNumber).getCustomer();
            if (targetAccount != null) {
                if (sourceAccount.getBalance() >= amount) {
                    double sourceNewBalance = sourceAccount.getBalance() - amount;
                    sourceAccount.setBalance(sourceNewBalance);
                    targetAccount.setBalance(targetAccount.getBalance() + amount);
                    customerRepository.saveAll(List.of(sourceAccount, targetAccount));

                    logger.info("Funds transferred successfully. Source account balance: {}, Target account balance: {}", sourceNewBalance, targetAccount.getBalance());

                    return sourceNewBalance;
                } else {
                    throw new InsufficientBalanceException("Insufficient balance for transfer");
                }
            } else {
                throw new NoSuchElementException("Target account not found: " + targetAccountNumber);
            }
        }

        throw new NoSuchElementException("Source account not found for customer: " + sourceCustomerId);
    }
}




