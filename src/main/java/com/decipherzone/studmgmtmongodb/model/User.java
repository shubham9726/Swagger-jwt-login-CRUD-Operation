package com.decipherzone.studmgmtmongodb.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.validation.constraints.NotNull;
import java.util.List;

@Document(collection = "user")
public class User {

  @Id
  private String id;
  @NotNull
  @ApiModelProperty
  private String email;
  @ApiModelProperty
  @NotNull
  private String password;
  @NotNull
  @ApiModelProperty
  private String name;
  @ElementCollection(fetch = FetchType.EAGER)
  List<Role> roles;

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}