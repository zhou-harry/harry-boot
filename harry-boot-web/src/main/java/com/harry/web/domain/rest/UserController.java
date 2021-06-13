package com.harry.web.domain.rest;

import com.harry.base.common.annotation.ResponseResult;
import com.harry.base.common.base.result.BaseResult;
import com.harry.base.common.base.result.ResultCode;
import com.harry.base.common.exception.CommonException;
import com.harry.sso.api.dto.UserDTO;
import com.harry.sso.api.feign.RemoteSSOApi;
import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@ResponseResult
@RequestMapping("/user")
public class UserController {

  private static final Random RANDOM = new Random();

  @Autowired
  private RemoteSSOApi ssoApi;

  @GetMapping("master")
  public List<UserDTO> masterUsers() {
    BaseResult<List<UserDTO>> result = ssoApi.masterUsers();
    return result.getData();
  }

  @GetMapping("slave")
  public List<UserDTO> slaveUsers() {
    BaseResult<List<UserDTO>> result = ssoApi.slaveUsers();
    return result.getData();
  }

  @GetMapping("/lambdaMaster")
  public List<UserDTO> lambdaMasterUsers() {
    BaseResult<List<UserDTO>> result = ssoApi.lambdaMasterUsers();
    return result.getData();
  }

  @GetMapping("/lambdaSlave")
  public List<UserDTO> lambdaSlaveUsers() {
    throw new CommonException(ResultCode.EXCEPTION);
//    BaseResult<List<UserDTO>> result = ssoApi.lambdaSlaveUsers();
//    return result.getData();
  }

  @GetMapping("slaveAnnotation")
  public List<UserDTO> slaveAnnotationUsers() {
    BaseResult<List<UserDTO>> result = ssoApi.slaveAnnotationUsers();
    return result.getData();
  }

  @PostMapping("add")
  public UserDTO addUser() {
    int nextInt = RANDOM.nextInt();
    UserDTO user = UserDTO.builder()
        .username("user_" + nextInt)
        .nickName("用户_" + nextInt)
        .password("harry")
        .gender(true)
        .avatar("url")
        .salt("harry")
        .status(true)
        .build();

    BaseResult<UserDTO> result = ssoApi.addUser(user);
    return result.getData();
  }

  @DeleteMapping("/id/{id}")
  public String deleteUser(@PathVariable Long id) {
    BaseResult<String> result = ssoApi.deleteUser(id);
    return result.getData();
  }
}
