package com.practical.PROJECT.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor

public class TransferRequestDTO {
    private String sourceCustomerId;
    private String targetAccountNumber;
    private double amount;


    // ...
}
