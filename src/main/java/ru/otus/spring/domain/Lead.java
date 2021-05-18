package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lead {
    private String leadNumber = UUID.randomUUID().toString();
    private String phoneNumber = UUID.randomUUID().toString();
    private LoanProduct loan = new LoanProduct();
    private LeadStatus status = LeadStatus.NEW;

    public Boolean isPersonalLoan() {
        return this.getLoan().getLoanType() == LoanType.PERSONAL_LOAN;
    }

    public Boolean isCreditCard() {
        return this.getLoan().getLoanType() == LoanType.CREDIT_CARD;
    }

    public Boolean isApproved() {
        return this.getStatus() == LeadStatus.APPROVE;
    }


}
