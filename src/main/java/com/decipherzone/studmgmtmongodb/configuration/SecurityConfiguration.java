package com.decipherzone.studmgmtmongodb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security Config
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  public SecurityConfiguration(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf().disable()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and().authorizeRequests()
      .antMatchers("/login").permitAll()
      .antMatchers("/register").permitAll()
      .anyRequest().authenticated()
      .and()
      .exceptionHandling().accessDeniedPage("/login")
      .and()
      .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  public void configure(WebSecurity web) {
    web
      .ignoring().antMatchers("/v2/api-docs")
      .antMatchers("/swagger-resources/**")
      .antMatchers("/swagger-ui.html")
      .antMatchers("/configuration/**")
      .antMatchers("/webjars/**")
      .antMatchers("/public")
      .and()
      .ignoring()
      .antMatchers("/h2-console/**/**");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager() {
    return new AuthenticationManager() {
      @Override
      public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
      }
    };
  }
}