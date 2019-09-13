package com.decipherzone.studmgmtmongodb.service;

import com.decipherzone.studmgmtmongodb.model.User;

import javax.servlet.http.HttpServletRequest;

public interface UserServiceImpl {

  User register(User user);

  String login(String email, String password);

  String refresh(String remoteUser);

  Object whoAmI(HttpServletRequest req);
}