package com.decipherzone.studmgmtmongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "student")
public class Student extends DeleteModel{

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

  @Override
  public String toString() {
    return "Student{" +
      "Id=" + Id +
      ", name='" + name + '\'' +
      ", address='" + address + '\'' +
      ", mobile='" + mobile + '\'' +
      ", fee='" + fee + '\'' +
      '}';
  }
}
