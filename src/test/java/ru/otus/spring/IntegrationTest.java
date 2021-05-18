package ru.otus.spring;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.spring.domain.Lead;
import ru.otus.spring.domain.LeadStatus;
import ru.otus.spring.domain.LoanType;
import ru.otus.spring.integration.IncomingLead;
import ru.otus.spring.service.LeadService;
import ru.otus.spring.service.RandomService;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT + ".enabled=false"
})
public class IntegrationTest {

    @Autowired
    private LeadService leadService;

    @Autowired
    private IncomingLead incomingLead;

    @MockBean
    private RandomService randomService;

    @Test
    public void personalLoanHappyPathTest() throws InterruptedException {
        Mockito.when(randomService.nextBoolean()).thenReturn(false);

        Lead lead = incomingLead.processLead(leadService.createPersonalLoanLead());
        System.out.println(lead.getStatus());
        Assert.assertSame(lead.getLoan().getLoanType(), LoanType.PERSONAL_LOAN);
        Assert.assertSame(lead.getStatus(), LeadStatus.APPROVE);
        Assert.assertTrue(lead.getLoan().getInterestRate() > 0);
        Assert.assertTrue(lead.getLoan().getPeriod() > 0);
        Assert.assertNotNull(lead.getLoan().getAccountNumber());
        Assert.assertNull(lead.getLoan().getCardNumber());

    }

    @Test
    public void personalLoanDeclineTest() throws InterruptedException {
        Mockito.when(randomService.nextBoolean()).thenReturn(true);

        Lead lead = incomingLead.processLead(leadService.createPersonalLoanLead());
        System.out.println(lead.getStatus());
        Assert.assertSame(lead.getLoan().getLoanType(), LoanType.PERSONAL_LOAN);
        Assert.assertSame(lead.getStatus(), LeadStatus.DECLINE);
        Assert.assertNull(lead.getLoan().getInterestRate());
        Assert.assertNull(lead.getLoan().getPeriod());
        Assert.assertNull(lead.getLoan().getAccountNumber());
        Assert.assertNull(lead.getLoan().getCardNumber());

    }

    @Test
    public void creditCardHappyPathTest() throws InterruptedException {
        Mockito.when(randomService.nextBoolean()).thenReturn(false);

        Lead lead = incomingLead.processLead(leadService.createCreditCardLead());
        System.out.println(lead.getStatus());
        Assert.assertSame(lead.getLoan().getLoanType(), LoanType.CREDIT_CARD);
        Assert.assertSame(lead.getStatus(), LeadStatus.APPROVE);
        Assert.assertTrue(lead.getLoan().getInterestRate() > 0);
        Assert.assertTrue(lead.getLoan().getPeriod() == 0);
        Assert.assertNotNull(lead.getLoan().getAccountNumber());
        Assert.assertNotNull(lead.getLoan().getCardNumber());

    }

    @Test
    public void creditCardDeclineTest() throws InterruptedException {
        Mockito.when(randomService.nextBoolean()).thenReturn(true);

        Lead lead = incomingLead.processLead(leadService.createCreditCardLead());
        System.out.println(lead.getStatus());
        Assert.assertSame(lead.getLoan().getLoanType(), LoanType.CREDIT_CARD);
        Assert.assertSame(lead.getStatus(), LeadStatus.DECLINE);
        Assert.assertNull(lead.getLoan().getInterestRate());
        Assert.assertNull(lead.getLoan().getPeriod());
        Assert.assertNull(lead.getLoan().getAccountNumber());
        Assert.assertNull(lead.getLoan().getCardNumber());

    }

}
