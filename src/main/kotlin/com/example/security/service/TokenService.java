package com.example.security.service;

import com.example.security.model.Token;
import com.example.security.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public record TokenService(TokenRepository tokenRepository) {

    public void saveConfirmationToken(Token token){
        tokenRepository.save(token);
    }
    public Optional<Token> getToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return tokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
