package com.gussoft.operations.core.business;

import com.gussoft.operations.core.models.dto.Customer;
import reactor.core.publisher.Mono;

public interface CustomerService {

  Mono<Customer> findById(String id);

}
