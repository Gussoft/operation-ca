package com.gussoft.operations.core.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException implements CoreException {

  private static final String EXCEPTION_ID = "ERR404";
  private final String identifier;

  public ResourceNotFoundException(String identifier, String message) {
    super(message);
    this.identifier = identifier;
  }

  public ResourceNotFoundException(String identifier, String message, Throwable throwable) {
    super(message, throwable);
    this.identifier = identifier;
  }

  @Override
  public String getExceptionId() {
    return EXCEPTION_ID;
  }

}
