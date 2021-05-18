package ru.otus.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Lead;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {

    private final ConsoleIOService consoleIOService;

    @Override
    public Lead open(Lead lead) {

        consoleIOService.out("Card opening stage start.");
        lead.getLoan().setCardNumber(UUID.randomUUID().toString());
        consoleIOService.out("Card linked to account. Card UUID " + lead.getLoan().getCardNumber());
        consoleIOService.out("Card opening stage end.");
        return lead;
    }
}
