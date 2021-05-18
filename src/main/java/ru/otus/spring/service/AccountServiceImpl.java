package ru.otus.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Lead;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final ConsoleIOService consoleIOService;

    @Override
    public Lead open(Lead lead) {
        consoleIOService.out("Account opening stage start.");

        lead.getLoan().setAccountNumber(UUID.randomUUID().toString());
        consoleIOService.out("Account opened with UUID: " + lead.getLoan().getAccountNumber());

        consoleIOService.out("Account opening stage end.");
        return lead;
    }
}
