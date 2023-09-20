package com.gussoft.operations.core.business.impl;

import com.gussoft.operations.core.business.AccountService;
import com.gussoft.operations.core.models.dto.Account;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {

  @Autowired
  @Qualifier("registerWebAccount")
  private WebClient.Builder client;

  @Override
  public Mono<Account> findById(String id) {
    return client.build().get()
      .uri("/{id}", Collections.singletonMap("id", id))
      .accept(MediaType.APPLICATION_JSON)
      .exchangeToMono(response -> response.bodyToMono(Account.class));
  }

  @Override
  public Mono findByCustomerAccount(String id) {
    return client.build().get()
      .uri("/customer/{id}/home", Collections.singletonMap("id", id))
      .accept(MediaType.APPLICATION_JSON)
      .exchangeToMono(response -> response.bodyToMono(HashMap.class));
  }

  @Override
  public Mono<Account> updateAmount(String id, BigDecimal amount, String operation) {
    return client.build().put()
      .uri(uriBuilder -> uriBuilder.path("/customer/{id}")
        .queryParam("amount", amount)
        .queryParam("operation", operation).build(id))
      .accept(MediaType.APPLICATION_JSON)
      .exchangeToMono(response -> response.bodyToMono(Account.class));
  }

}
