package com.gussoft.operations.core.exception;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ExceptionAdviceController {

  @ExceptionHandler(value = ResourceNotFoundException.class)
  public Mono<ResponseEntity<ErrorDTO>> notFoundExceptionHandler(ResourceNotFoundException e) {
    ErrorDTO error = ErrorDTO.builder()
      .code(HttpStatus.NOT_FOUND.value())
      .createAt(new Date().toString())
      .message(e.getMessage()).build();
    return Mono.just(new ResponseEntity<>(error, HttpStatus.NOT_FOUND));
  }

}
