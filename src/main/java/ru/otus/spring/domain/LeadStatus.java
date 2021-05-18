package ru.otus.spring.domain;

public enum LeadStatus {
    NEW("NEW"),
    APPROVE("APPROVE"),
    DECLINE("DECLINE");

    private String message;

    LeadStatus(String msg) {
        message = msg;
    }

    public String getMessage() {
        return message;
    }
}
