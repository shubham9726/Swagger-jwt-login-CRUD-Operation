package com.decipherzone.studmgmtmongodb.exception;

import org.springframework.http.HttpStatus;

/**
 * Custom exception class
 */
public class StudentMngmtexception extends RuntimeException {

  private final String message;
  private final HttpStatus httpStatus;

  public StudentMngmtexception(String message, HttpStatus httpStatus) {
    this.message = message;
    this.httpStatus = httpStatus;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

}
