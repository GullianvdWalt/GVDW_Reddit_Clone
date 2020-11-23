package com.example.gvdw.controllers;

import com.example.gvdw.dtos.RegisterRequestDto;
import com.example.gvdw.services.AuthService;
import com.example.gvdw.services.MailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity signup(@RequestBody RegisterRequestDto registerRequestDto){
    authService.signup(registerRequestDto);
    return new ResponseEntity<>("User Registration Successful",HttpStatus.OK);
  }

}
