package com.example.gvdw.services;

import com.example.gvdw.dtos.RegisterRequestDto;
import com.example.gvdw.models.NotificationEmail;
import com.example.gvdw.models.User;
import com.example.gvdw.models.VerificationToken;
import com.example.gvdw.repositories.UserRepository;
import com.example.gvdw.repositories.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {

  private final MailService mailService;
  private final UserRepository userRepository;
  private VerificationTokenRepository verificationTokenRepository;
  private final PasswordEncoder passwordEncoder;

  // User Signup
  public void signup(RegisterRequestDto registerRequestDto){
    User user = new User();
    // Set User Details and Register
    user.setUsername(registerRequestDto.getUsername());
    // Encode Password
    user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
    user.setEmail(registerRequestDto.getEmail());
    user.setCreated(Instant.now());
    user.setEnabled(false);

    userRepository.save(user);

    String token = generateVerificationToken(user);

  // Activate User Account with verification token
    mailService.sendMail(new NotificationEmail("Please Activate your Account",
      user.getEmail(),
      "Thank you for signing up to Spring Reddit, " +
        "please click on the below url to activate your account : " +
        "http://localhost:8080/api/auth/accountVerification/" + token));
  }

  // Generate Verification Token
  private String generateVerificationToken(User user){
      // Generate Unique 128-bit value
    String token = UUID.randomUUID().toString();
      VerificationToken verificationToken = new VerificationToken();
      verificationToken.setToken(token);
      verificationToken.setUser(user);
      // Save in DB
      verificationTokenRepository.save(verificationToken);

      return token;

  }
}
