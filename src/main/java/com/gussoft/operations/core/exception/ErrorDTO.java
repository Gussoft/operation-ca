package com.gussoft.operations.core.exception;


import lombok.Builder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
public class ErrorDTO {

  private int code;
  private String createAt;
  private String message;

}
