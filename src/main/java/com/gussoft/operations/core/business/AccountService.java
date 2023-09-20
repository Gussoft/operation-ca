package com.gussoft.operations.core.business;

import com.gussoft.operations.core.models.dto.Account;
import java.math.BigDecimal;
import java.util.Map;
import reactor.core.publisher.Mono;

public interface AccountService {

  Mono<Account> findById(String id);

  Mono<Map<String,Object>> findByCustomerAccount(String id);

  Mono<Account> updateAmount(String id, BigDecimal amount, String operation);

}
