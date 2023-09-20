package com.gussoft.operations.core.repository;

import com.gussoft.operations.core.models.TypeOperation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TypeOperationsRepository extends ReactiveMongoRepository<TypeOperation, String> {

  Mono<TypeOperation> findByName(String name);

}
