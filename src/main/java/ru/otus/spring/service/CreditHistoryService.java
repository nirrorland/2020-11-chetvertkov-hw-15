package ru.otus.spring.service;

import ru.otus.spring.domain.Lead;

public interface CreditHistoryService {
    Lead checkCreditHistory(Lead lead) throws InterruptedException;
}
