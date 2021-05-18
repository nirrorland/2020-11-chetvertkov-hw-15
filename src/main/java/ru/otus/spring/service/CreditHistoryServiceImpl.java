package ru.otus.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Lead;
import ru.otus.spring.domain.LeadStatus;

@Service
@AllArgsConstructor
public class CreditHistoryServiceImpl implements CreditHistoryService {
    private final RandomService randomService;
    private final ConsoleIOService consoleIOService;

    @Override
    public Lead checkCreditHistory(Lead lead) throws InterruptedException {
        consoleIOService.out("Credit history check started.");
        Boolean isHistoryBad = randomService.nextBoolean();
        Thread.sleep(2000);
        if (isHistoryBad) {
            consoleIOService.out("History is bad");
            lead.setStatus(LeadStatus.DECLINE);
        } else {
            consoleIOService.out("History good");
            lead.setStatus(LeadStatus.APPROVE);
        }
        consoleIOService.out("Credit history check ended.");
        return lead;
    }
}
