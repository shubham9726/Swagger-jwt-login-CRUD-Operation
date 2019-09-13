package com.decipherzone.studmgmtmongodb.service;

import com.decipherzone.studmgmtmongodb.configuration.JwtTokenProvider;
import com.decipherzone.studmgmtmongodb.exception.StudentMngmtexception;
import com.decipherzone.studmgmtmongodb.model.User;
import com.decipherzone.studmgmtmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Service Provider to User
 */

@Service
public class UserService implements UserServiceImpl {

  private PasswordEncoder passwordEncoder;
  private AuthenticationManager authenticationManager;
  private JwtTokenProvider jwtTokenProvider;
  private UserRepository userRepository;

  @Autowired
  UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
    this.userRepository = userRepository;
    this.jwtTokenProvider = jwtTokenProvider;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
  }

  @Override
  public String login(String email, String password) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
      return jwtTokenProvider.createToken(email, userRepository.findByEmail(email).getRoles());
    } catch (AuthenticationException e) {
      throw new StudentMngmtexception("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  @Override
  public User register(User user) {
    if (userRepository.checkEmailPresent(user.getEmail())) {
      throw new StudentMngmtexception("Email already exist", HttpStatus.ALREADY_REPORTED);
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.saveUser(user);
  }

  @Override
  public String refresh(String email) {
    return jwtTokenProvider.createToken(email, userRepository.findByEmail(email).getRoles());
  }

  @Override
  public User whoAmI(HttpServletRequest req) {
    return userRepository.findByEmail(jwtTokenProvider.getEmail(jwtTokenProvider.resolveToken(req)));
  }


}