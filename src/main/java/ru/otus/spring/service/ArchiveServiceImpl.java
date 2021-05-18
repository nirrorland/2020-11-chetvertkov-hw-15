package ru.otus.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Lead;

@Service
@AllArgsConstructor
public class ArchiveServiceImpl implements ArchiveService {
    private final ConsoleIOService consoleIOService;

    @Override
    public Lead archive(Lead lead) {

        consoleIOService.out("Archive stage start.");
        consoleIOService.out("Lead archived with status: " + lead.getStatus().getMessage());
        consoleIOService.out("Archive stage end.");

        return lead;

    }
}
