package com.practical.PROJECT.controller;

import com.practical.PROJECT.dto.DepositDTO;
import com.practical.PROJECT.dto.TransferRequestDTO;
import com.practical.PROJECT.dto.WithdrawalRequestDTO;
import com.practical.PROJECT.exceptions.InsufficientBalanceException;
import com.practical.PROJECT.model.Transaction;
import com.practical.PROJECT.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/accounts")
@Api(tags = "Account API")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{customerId}/balance")
    @ApiOperation("Get customer balance")
    public ResponseEntity<Double> getCustomerBalance(@PathVariable String customerId) {
        try {
            Double balance = accountService.getCustomerBalance(customerId);
            return ResponseEntity.ok(balance);
        } catch (Exception e) {
            // Handle exception and return appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{customerId}/mini-statement")
    @ApiOperation("Get customer mini statement")
    public ResponseEntity<List<Transaction>> getCustomerMiniStatement(@PathVariable String customerId) {
        try {
            List<Transaction> transactions = accountService.getCustomerMiniStatement(customerId);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            // Handle exception and return appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{customerId}/deposit")
    @ApiOperation("Deposit funds")
    public ResponseEntity<Double> deposit(@PathVariable String customerId, @RequestBody DepositDTO depositDTO) {
        try {
            Double newBalance = accountService.deposit(customerId, depositDTO);
            return ResponseEntity.ok(newBalance);
        } catch (Exception e) {
            // Handle exception and return appropriate response
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{customerId}/withdraw")
    @ApiOperation("Withdraw funds")
    public ResponseEntity<Double> withdraw(@PathVariable String customerId, @RequestBody WithdrawalRequestDTO requestDTO) {
        try {
            Double newBalance = accountService.withdraw(customerId, requestDTO.getAmount());
            return ResponseEntity.ok(newBalance);
        } catch (NoSuchElementException | InsufficientBalanceException e) {
            // Handle exception and return appropriate response
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{sourceCustomerId}/transfer")
    @ApiOperation("Transfer funds")
    public ResponseEntity<Double> transferFunds(@PathVariable String sourceCustomerId, @RequestBody TransferRequestDTO requestDTO) {
        try {
            Double newBalance = accountService.transferFunds(sourceCustomerId, requestDTO.getTargetAccountNumber(), requestDTO.getAmount());
            return ResponseEntity.ok(newBalance);
        } catch (Exception | InsufficientBalanceException e) {
            // Handle exception and return appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
