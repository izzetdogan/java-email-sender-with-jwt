package com.example.security.service;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidateService implements Predicate<String> {
    @Override
    public boolean test(String s) {
        return true;
    }
}
