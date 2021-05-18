package ru.otus.spring.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.domain.Lead;

@MessagingGateway
public interface IncomingLead {
    @Gateway(requestChannel = "incomingLeadChannel", replyChannel = "outcomingLoanChannel")
    Lead processLead(Lead lead);

    @Gateway(requestChannel = "calculateTariffChannel")
    Lead calculateTariff(Lead lead);

    @Gateway(requestChannel = "archiveChannel")
    Lead processArchive(Lead lead);

    @Gateway(requestChannel = "cardChannel")
    Lead createCard(Lead lead);
}
