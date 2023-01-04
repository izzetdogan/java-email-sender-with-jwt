package com.example.security.service;

import com.example.security.dto.RegisterDto;
import com.example.security.dto.UserDto;
import com.example.security.email.EmailService;
import com.example.security.model.Role;
import com.example.security.model.Token;
import com.example.security.model.User;
import com.example.security.repository.UserRepository;
import com.example.security.security.JwtHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public record UserService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        TokenService tokenService,
        JwtHelper jwtHelper,
        EmailService emailService
) implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("No user Found with email"));
    }

    @Transactional
    public UserDto createUser(RegisterDto request) {
        Optional<User> userfind = this.userRepository.findByEmail(request.getEmail());

        if(userfind.isPresent()){
            throw new RuntimeException("email var");
        }
        System.out.println("User" + userfind);

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(
                request.getFirstname(),
                request.getLastname(),
                request.getEmail(),
                encodedPassword,
                Role.NORMAL
        );
        User saveOne = this.userRepository.save(user);
        String t = jwtHelper.generateToken(saveOne);
        Token token = new Token(t, LocalDateTime.now(),LocalDateTime.now().plusMinutes(15),user);
        tokenService.saveConfirmationToken(token);

        //SendEnmail

        emailService.send(request.getEmail(),);
        return UserDto.convert(saveOne);
    }


    @Transactional
    public String confirmToken(String token) {
        Token confirmationToken = tokenService.getToken(token)
                .orElseThrow(() ->
                        new RuntimeException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new RuntimeException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        tokenService.setConfirmedAt(token);
        return "confirmed";
    }

}
