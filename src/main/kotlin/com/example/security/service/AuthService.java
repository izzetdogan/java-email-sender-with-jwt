package com.example.security.service;

import com.example.security.dto.RegisterDto;
import com.example.security.dto.UserDto;
import com.example.security.model.User;
import com.example.security.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public record AuthService(UserService userService, EmailValidateService emailValidateService) {

    public UserDto register(RegisterDto request)  {
        boolean isValidEmail = emailValidateService.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("email not valid");
        }


        return null;
    }

    public UserDto signup(RegisterDto registerDto){
        return this.userService.createUser(registerDto);
    }


}
