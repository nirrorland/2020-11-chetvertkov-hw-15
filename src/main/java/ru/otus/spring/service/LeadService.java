package ru.otus.spring.service;

import ru.otus.spring.domain.Lead;

public interface LeadService {

    Lead createPersonalLoanLead();

    Lead createCreditCardLead();
}
