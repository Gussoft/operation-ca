package com.gussoft.operations.core.repository;

import com.gussoft.operations.core.models.Operations;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface OperationsRepository extends ReactiveMongoRepository<Operations, String> {

  Flux<Operations> findByAccount(String id);

}
