package com.gussoft.operations.integration.transfer.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CustomResponse<E> {

  private String message;
  private int statusCode;

  @JsonInclude(value = JsonInclude.Include.NON_NULL)
  private E data;

  public CustomResponse(String message, HttpStatus status, E data) {
    this.message = message;
    this.statusCode = status.value();
    this.data = data;
  }

  public CustomResponse(String message, HttpStatus statusCode) {
    this.message = message;
    this.statusCode = statusCode.value();
  }

  public CustomResponse(HttpStatus statusCode) {
    this.statusCode = statusCode.value();
    this.message = statusCode.getReasonPhrase();
  }

}
