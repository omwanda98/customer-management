package com.practical.PROJECT.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

public class CustomerDTO {

    private String firstName;
    private String lastName;
    private String email;
    private  String customerId;
}
