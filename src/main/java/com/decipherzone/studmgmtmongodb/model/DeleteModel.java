package com.decipherzone.studmgmtmongodb.model;

public class DeleteModel {

  private boolean deleted = Boolean.FALSE;

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }
}
