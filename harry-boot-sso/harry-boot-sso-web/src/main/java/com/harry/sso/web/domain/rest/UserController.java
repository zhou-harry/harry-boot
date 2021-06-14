package com.harry.sso.web.domain.rest;

import com.harry.base.common.annotation.ResponseResult;
import com.harry.base.common.base.result.ResultCode;
import com.harry.base.common.exception.CommonException;
import com.harry.sso.api.dto.UserDTO;
import com.harry.sso.web.domain.entity.UserEntity;
import com.harry.sso.web.domain.mapping.UserStruct;
import com.harry.sso.web.domain.service.UserService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@AllArgsConstructor
@ResponseResult
@Api("SSO用户信息")
@RequestMapping("/user")
public class UserController {

  private static final Random RANDOM = new Random();
  private final UserService userService;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @GetMapping("/master")
  public List<UserDTO> masterUsers() {
    List<UserEntity> entities = userService.selectMasterUsers();
    return UserStruct.INSTANCE.map2DTOs(entities);
  }

  @GetMapping("/slave")
  public List<UserDTO> slaveUsers() {
    List<UserEntity> entities = userService.selectSlaveUsers();
    return UserStruct.INSTANCE.map2DTOs(entities);
  }

  @GetMapping("/lambdaMaster")
  public List<UserDTO> lambdaMasterUsers() {
    List<UserEntity> entities = userService.selectLambdaMasterUsers();
    List<UserDTO> list = UserStruct.INSTANCE.map2DTOs(entities);
    return list;
  }

  @GetMapping("/lambdaSlave")//fixme 似乎不生效
  public List<UserDTO> lambdaSlaveUsers() {
    throw new CommonException(ResultCode.EXCEPTION);
//    List<UserEntity> entities = userService.selectLambdaSlaveUsers();
//    return UserStruct.INSTANCE.map2DTOs(entities);
  }

  @GetMapping("/slaveAnnotation")
  public List<UserDTO> slaveAnnotationUsers() {
    List<UserEntity> entities = userService.selectSlaveAnnotationUsers();
    return UserStruct.INSTANCE.map2DTOs(entities);
  }

  @PostMapping("/add")
  public UserDTO addUser(UserDTO user) {
    userService.addUser(UserStruct.INSTANCE.map2Entity(user));
    return user;
  }

  @DeleteMapping("/id/{id}")
  public String deleteUser(@PathVariable Long id) {
    userService.deleteUserById(id);
    return "成功删除用户" + id;
  }
}
