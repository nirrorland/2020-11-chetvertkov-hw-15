package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.integration.IncomingLead;
import ru.otus.spring.service.ConsoleIOService;
import ru.otus.spring.service.LeadService;


@ShellComponent
public class ConsoleCmd {


    private static final String NOVALUE = "NOVALUE";
    final private ConsoleIOService consoleIOService;
    final private LeadService leadService;
    final private IncomingLead incomingLead;


    public ConsoleCmd(ConsoleIOService consoleIOService, LeadService leadService, IncomingLead incomingLead) {
        this.consoleIOService = consoleIOService;
        this.leadService = leadService;
        this.incomingLead = incomingLead;

    }

    @ShellMethod(value = "start pl lead", key = {"pl", "1"})
    private void runMigration() {
        consoleIOService.out("Personal loan lead started.");
        incomingLead.processLead(leadService.createPersonalLoanLead());

    }

    @ShellMethod(value = "start cc lead", key = {"cc", "2"})
    private void restartMigration() {
        consoleIOService.out("Credit card lead started.");
        incomingLead.processLead(leadService.createCreditCardLead());
    }

}
