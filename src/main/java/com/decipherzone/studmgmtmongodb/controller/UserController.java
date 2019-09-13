package com.decipherzone.studmgmtmongodb.controller;

import com.decipherzone.studmgmtmongodb.model.User;
import com.decipherzone.studmgmtmongodb.service.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

  private UserServiceImpl userServiceimpl;
  private ModelMapper modelMapper;

  @Autowired
  UserController(UserServiceImpl userServiceimpl, ModelMapper modelMapper) {
    this.userServiceimpl = userServiceimpl;
    this.modelMapper = modelMapper;
  }

  @PostMapping({"/login"})
  @ApiOperation(value = "Login To User")
  public String login(
    @ApiParam("Email") @RequestParam String email,
    @ApiParam("Password") @RequestParam String password) {
    return userServiceimpl.login(email, password);
  }

  @PostMapping("/register")
  @ApiOperation(value = "User Registration")
  public User register(@RequestBody User user) {
    return userServiceimpl.register(modelMapper.map(user, User.class));
  }

  @GetMapping(value = "/me")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
  @ApiOperation(value = "Who AM I", response = User.class)
  public User whoami(HttpServletRequest req) {
    return modelMapper.map(userServiceimpl.whoAmI(req), User.class);
  }

  @GetMapping("/refresh")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
  @ApiOperation(value = "Refresh User")
  public String refresh(HttpServletRequest req) {
    return userServiceimpl.refresh(req.getRemoteUser());
  }
}