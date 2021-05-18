package ru.otus.spring.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.spring.domain.Lead;
import ru.otus.spring.service.*;

@RequiredArgsConstructor
@Configuration
public class IntegrationFlowConfig {

    private final CreditHistoryService creditHistoryService;
    private final CalculateTariffService calculateTariffService;
    private final AccountService accountService;
    private final CreditCardService creditCardService;
    private final ArchiveService archiveService;

    @Bean
    public QueueChannel incomingLeadChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public QueueChannel calculateTariffChannel() {
        return MessageChannels.queue().get();
    }

    @Bean
    public QueueChannel archiveChannel() {
        return MessageChannels.queue().get();
    }

    @Bean
    public QueueChannel cardChannel() {
        return MessageChannels.queue().get();
    }


    @Bean
    public PublishSubscribeChannel outcomingLoanChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedDelay(1000).get();
    }

    @Bean
    public IntegrationFlow workFlow() {
        return IntegrationFlows.from("incomingLeadChannel")
                .handle(creditHistoryService, "checkCreditHistory")
                .<Lead, Boolean>route(lead -> lead.isApproved(),
                        mapping -> mapping
                                .channelMapping(true, "calculateTariffChannel")
                                .channelMapping(false, "archiveChannel"))
                .get();
    }

    @Bean
    public IntegrationFlow tariffAndAccFlow() {
        return IntegrationFlows.from("calculateTariffChannel")
                .handle(calculateTariffService, "calculateTariffService")
                .handle(accountService, "open")
                .<Lead, Boolean>route(lead -> lead.isCreditCard(),
                        mapping -> mapping
                                .channelMapping(true, "cardChannel")
                                .channelMapping(false, "archiveChannel"))
                .get();
    }

    @Bean
    public IntegrationFlow cardFlow() {
        return IntegrationFlows.from("cardChannel")
                .handle(creditCardService, "open")
                .channel("archiveChannel")
                .get();
    }

    @Bean
    public IntegrationFlow archiveFlow() {
        return IntegrationFlows.from("archiveChannel")
                .handle(archiveService, "archive")
                .channel("outcomingLoanChannel")
                .get();
    }
}
