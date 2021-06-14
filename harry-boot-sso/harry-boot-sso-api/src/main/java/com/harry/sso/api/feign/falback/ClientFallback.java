package com.harry.sso.api.feign.falback;

import com.google.common.collect.Lists;
import com.harry.base.common.base.result.BaseResult;
import com.harry.sso.api.dto.UserDTO;
import com.harry.sso.api.feign.RemoteSSOApi;
import lombok.Builder;
import lombok.Data;

import java.util.List;

import static com.harry.base.common.base.result.ResultCode.EXCEPTION;

/**
 * 调用端熔断/降级
 */
@Data
@Builder
public class ClientFallback implements RemoteSSOApi {

  private String errorMessage;


  @Override
  public BaseResult<List<UserDTO>> masterUsers() {
    return BaseResult.fail(EXCEPTION, Lists.newArrayList());
  }

  @Override
  public BaseResult<List<UserDTO>> slaveUsers() {
    return BaseResult.fail(EXCEPTION, Lists.newArrayList());
  }

  @Override
  public BaseResult<List<UserDTO>> lambdaMasterUsers() {
    return BaseResult.fail(EXCEPTION, Lists.newArrayList());
  }

  @Override
  public BaseResult<List<UserDTO>> lambdaSlaveUsers() {
    return BaseResult.fail(EXCEPTION, Lists.newArrayList());
  }

  @Override
  public BaseResult<List<UserDTO>> slaveAnnotationUsers() {
    return BaseResult.fail(EXCEPTION, Lists.newArrayList());
  }

  @Override
  public BaseResult<UserDTO> addUser(UserDTO user) {
    return BaseResult.fail(EXCEPTION, UserDTO.builder().build());
  }

  @Override
  public BaseResult<String> deleteUser(Long id) {
    return BaseResult.fail(EXCEPTION, "");
  }
}
