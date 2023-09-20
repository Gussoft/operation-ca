package com.gussoft.operations.integration.mappers;

import com.gussoft.operations.core.models.Operations;
import com.gussoft.operations.core.models.TypeOperation;
import com.gussoft.operations.integration.transfer.request.OperationsRequest;
import com.gussoft.operations.integration.transfer.response.OperationsResponse;
import com.gussoft.operations.integration.transfer.response.TypeOperationResponse;
import org.springframework.beans.BeanUtils;

public class OperationsMapper {

  public OperationsMapper() {
  }

  public static Operations toOperationsRequest(OperationsRequest request) {
    Operations response = new Operations();
    BeanUtils.copyProperties(request, response);
    if (request.getType() != null) {
      response.setType(new TypeOperation(request.getType().getId(), request.getType().getName()));
    }
    return response;
  }

  public static OperationsResponse toOperationsResponse(Operations entity) {
    OperationsResponse response = new OperationsResponse();
    BeanUtils.copyProperties(entity, response);
    if (entity.getType() != null) {
      response.setType(new TypeOperationResponse(entity.getType().getId(), entity.getType().getName()));
    }
    return response;
  }

}
