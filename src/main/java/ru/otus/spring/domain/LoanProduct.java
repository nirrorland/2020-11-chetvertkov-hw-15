package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanProduct {
    private LoanType loanType;
    private Long amount;
    private Long interestRate;
    private Integer period;
    private String accountNumber;
    private String cardNumber;
}
