package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Lead;
import ru.otus.spring.domain.LoanType;

@Service
public class LeadServiceImpl implements LeadService {

    @Override
    public Lead createPersonalLoanLead() {
        Lead lead = new Lead();
        lead.getLoan().setAmount(100000L);
        lead.getLoan().setLoanType(LoanType.PERSONAL_LOAN);
        return lead;
    }

    @Override
    public Lead createCreditCardLead() {
        Lead lead = new Lead();
        lead.getLoan().setAmount(40000L);
        lead.getLoan().setLoanType(LoanType.CREDIT_CARD);
        return lead;
    }
}
