package com.example.userservice.controller;

import com.example.userservice.dtos.SignUpRequestDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    public void signUp(SignUpRequestDto signUpRequestDto){

    }
}
