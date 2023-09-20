package com.gussoft.operations.core.models;

import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "operations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Operations {
  @Id
  @NotEmpty
  private String id;

  private String account;

  private String number;

  private BigDecimal amount;

  private TypeOperation type;

  private Date createAt;

  private String detail;

}
