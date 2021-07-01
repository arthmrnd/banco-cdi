package com.bancocdi.java.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {

    private String accountUser;
    private String password;
    private AccountType accountType;
    private BigDecimal balance;
}
