package com.example.security.controller;

import com.example.security.dto.RegisterDto;
import com.example.security.dto.UserDto;
import com.example.security.service.AuthService;
import com.example.security.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
record AuthController(UserService userService, AuthService authService) {

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterDto registerDto){
        return ResponseEntity.ok(this.userService.createUser(registerDto));
    }


}
