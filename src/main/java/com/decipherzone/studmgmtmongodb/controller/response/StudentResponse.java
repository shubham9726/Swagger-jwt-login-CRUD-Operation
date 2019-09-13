package com.decipherzone.studmgmtmongodb.controller.response;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

public class StudentResponse {

  @Id
  private int Id;
  @NotNull
  private String name;
  @NotNull
  private String address;
  @NotNull
  private String mobile;
  @NotNull
  private float fee;


  public float getFee() {
    return fee;
  }

  public void setFee(float fee) {
    this.fee = fee;
  }

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
}
