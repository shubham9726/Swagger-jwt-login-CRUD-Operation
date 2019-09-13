package com.decipherzone.studmgmtmongodb.controller.request;

import javax.validation.constraints.NotNull;

public class StudentRequest {

  @NotNull
  private float fee;

  public float getFee() {
    return fee;
  }

  public void setFee(float fee) {
    this.fee = fee;
  }
}