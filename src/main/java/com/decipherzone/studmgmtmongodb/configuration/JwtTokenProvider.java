package com.decipherzone.studmgmtmongodb.configuration;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import com.decipherzone.studmgmtmongodb.exception.StudentMngmtexception;
import com.decipherzone.studmgmtmongodb.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

  @Value("${jwt.token.secret.key}")
  private String secretKey;

  @Value("${jwt.token.expireDate}")
  private long validityInMilliseconds;
  private MyUserDetailes myUserDetails;

  @Autowired
  public JwtTokenProvider(MyUserDetailes myUserDetails) {
    this.myUserDetails = myUserDetails;
  }

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  public String createToken(String email, List<Role> roles) {

    Claims claims = Jwts.claims().setSubject(email);
    claims.put("auth", roles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority())).filter(Objects::nonNull).collect(Collectors.toList()));
    Date now = new Date();
    Date validity = new Date(now.getTime() + validityInMilliseconds);

    return Jwts.builder()
      .setClaims(claims)
      .setIssuedAt(now)
      .setExpiration(validity)
      .signWith(SignatureAlgorithm.HS256, secretKey)
      .compact();
  }

  public Authentication getAuthentication(String token) {
    UserDetails userDetails = myUserDetails.loadUserByUsername(getEmail(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "token", userDetails.getAuthorities());
  }

  public String getEmail(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
  }

  public String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader("Authorization");
    if (StringUtils.isNotBlank(bearerToken)) {
      return bearerToken;
    }
    return null;
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      throw new StudentMngmtexception("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}