package com.decipherzone.studmgmtmongodb.configuration;

import com.decipherzone.studmgmtmongodb.model.User;
import com.decipherzone.studmgmtmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Default User Detail Service
 */
@Service
public class MyUserDetailes implements UserDetailsService {

  private UserRepository userRepository;

  @Autowired
  private MyUserDetailes(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    final User user = userRepository.findByEmail(email);

    if (Objects.isNull(user)) {
      throw new UsernameNotFoundException("User '" + email + "' not found");
    }

    return org.springframework.security.core.userdetails.User
      .withUsername(email)
      .password(user.getPassword())
      .authorities(user.getRoles())
      .accountExpired(false)
      .accountLocked(false)
      .credentialsExpired(false)
      .disabled(false)
      .build();
  }
}