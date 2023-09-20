package com.gussoft.operations.integration.mappers;

import com.gussoft.operations.core.models.TypeOperation;
import com.gussoft.operations.integration.transfer.request.TypeOperationRequest;
import com.gussoft.operations.integration.transfer.response.TypeOperationResponse;
import org.springframework.beans.BeanUtils;

public class TypeOperationMapper {

  public TypeOperationMapper() {
  }

  public static TypeOperation toTypeOperationRequest(TypeOperationRequest request) {
    TypeOperation entity = new TypeOperation();
    BeanUtils.copyProperties(request, entity);
    return entity;
  }

  public static TypeOperationResponse toTypeOperationResponse(TypeOperation request) {
    TypeOperationResponse response = new TypeOperationResponse();
    BeanUtils.copyProperties(request, response);
    return response;
  }

}
