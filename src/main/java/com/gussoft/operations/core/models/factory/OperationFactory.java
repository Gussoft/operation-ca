package com.gussoft.operations.core.models.factory;

public interface OperationFactory {

  Double checkBalance();

  Double withdrawals(Double amount);

  boolean depositMoney(Double amount);

  boolean transferMoney(Double amount, String numberAccount);

  void accountStatus(String idCustomer);

}
