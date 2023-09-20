package com.gussoft.operations.core.business.impl;

import com.gussoft.operations.core.business.AccountService;
import com.gussoft.operations.core.business.OperationService;
import com.gussoft.operations.core.exception.ResourceNotFoundException;
import com.gussoft.operations.core.models.dto.Account;
import com.gussoft.operations.core.repository.OperationsRepository;
import com.gussoft.operations.core.utils.Constrains;
import com.gussoft.operations.integration.mappers.OperationsMapper;
import com.gussoft.operations.integration.transfer.request.OperationsRequest;
import com.gussoft.operations.integration.transfer.response.OperationsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@Slf4j
public class OperationServiceImpl implements OperationService {

  @Autowired
  private OperationsRepository repo;

  @Autowired
  private AccountService accountService;

  @Override
  public Flux<OperationsResponse> findByAll() {
    return repo.findAll().map(OperationsMapper::toOperationsResponse);
  }

  @Override
  public Mono<OperationsResponse> findById(String id) {
    return repo.findById(id).map(OperationsMapper::toOperationsResponse);
  }

  @Override
  public Flux<OperationsResponse> findByAccount(String account) {
    return repo.findByAccount(account).map(OperationsMapper::toOperationsResponse);
  }

  @Override
  @Transactional
  public Mono<OperationsResponse> save(Mono<OperationsRequest> request) {
    return request.map(OperationsMapper::toOperationsRequest)
      .flatMap(operation -> {
        accountService.findById(operation.getAccount()).flatMap(account -> {
          if(operation.getType().getName().equalsIgnoreCase(Constrains.DEPOSITO)) {
            accountService.updateAmount(operation.getAccount(), operation.getAmount(), Constrains.DEPOSITO).subscribe();
            log.info("Deposito Realizado con exito de ".concat(operation.getAmount().toString()));
            return repo.save(operation);
          }
          if(operation.getType().getName().equalsIgnoreCase(Constrains.RETIRO)) {
            if (account.getBalance().compareTo(operation.getAmount()) >= 0) {
              accountService.updateAmount(operation.getAccount(), operation.getAmount(), Constrains.RETIRO).subscribe();
              log.info("Retiro Realizado con exito de ".concat(operation.getAmount().toString()));
              return repo.save(operation);
            } else {
              return Mono.error(new ResourceNotFoundException("sa","Saldo insuficiente"));
            }
          }
          return Mono.just(account);
        }).subscribe();

        return Mono.just(operation);
      })
      //.flatMap(repo::save)
      .map(OperationsMapper::toOperationsResponse);
  }

  @Override
  public Mono<OperationsResponse> update(Mono<OperationsRequest> request, String id) {
    return repo.findById(id)
      .flatMap(c -> request.map(OperationsMapper::toOperationsRequest)
        .doOnNext(e -> {
          e.setId(id);
          log.info(e.toString());
        }))
      .flatMap(repo::save)
      .map(OperationsMapper::toOperationsResponse);
  }

  @Override
  public Mono<Void> delete(String id) {
    return repo.deleteById(id);
  }
}
