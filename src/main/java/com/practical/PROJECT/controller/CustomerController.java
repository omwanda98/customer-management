package com.practical.PROJECT.controller;

import com.practical.PROJECT.dto.CustomerDTO;
import com.practical.PROJECT.model.Customer;
import com.practical.PROJECT.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@Api(tags = "Customer API")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    @ApiOperation("register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            Customer registeredCustomer = customerService.registerCustomer(customerDTO);
            return ResponseEntity.ok(registeredCustomer);
        } catch (Exception e) {
            // Handle exception and return appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

//         @PostMapping("/login")
//           public ResponseEntity<String> login(@RequestParam String customerId, @RequestParam String pin) {
//          String token = authenticationService.authenticateAndGetToken(customerId, pin);
//        return ResponseEntity.ok(token);
       }
  }




