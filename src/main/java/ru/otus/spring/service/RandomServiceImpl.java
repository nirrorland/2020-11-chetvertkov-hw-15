package ru.otus.spring.service;

import org.springframework.stereotype.Service;

@Service
public class RandomServiceImpl implements RandomService {
    @Override
    public boolean nextBoolean() {
        return Math.random() < 0.5;
    }
}
