package ru.otus.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Lead;

@Service
@AllArgsConstructor
public class CalculateTariffServiceImpl implements CalculateTariffService {
    private final ConsoleIOService consoleIOService;

    @Override
    public Lead calculateTariffService(Lead lead) {

        consoleIOService.out("Tariff stage start.");

        switch (lead.getLoan().getLoanType()) {
            case CREDIT_CARD:
                lead.getLoan().setInterestRate(30L);
                lead.getLoan().setPeriod(0);
                break;
            default:
                lead.getLoan().setInterestRate(17L);
                lead.getLoan().setPeriod(712);
                ;
        }
        consoleIOService.out("Interest rate = " + lead.getLoan().getInterestRate());
        consoleIOService.out("Tariff stage end.");

        return lead;
    }
}
