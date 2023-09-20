package com.gussoft.operations.core.models.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Account {

  private String id;

  private String customer;

  private String numberAccount;

  private String cci;

  private TypeAccount type;

  private Date createAt;

  private BigDecimal balance;

  private BigDecimal commission;

}
