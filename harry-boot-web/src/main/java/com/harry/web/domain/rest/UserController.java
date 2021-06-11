package com.harry.web.domain.rest;

import com.harry.base.common.annotation.ResponseResult;
import com.harry.base.common.base.result.ResultCode;
import com.harry.base.common.exception.CommonException;
import com.harry.web.domain.entity.UserEntity;
import com.harry.web.domain.service.UserService;
import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
  private final UserService userService;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @GetMapping("master")
  public List<UserEntity> masterUsers() {
    return userService.selectMasterUsers();
  }

  @GetMapping("slave")
  public List<UserEntity> slaveUsers() {
    return userService.selectSlaveUsers();
  }

  @GetMapping("/lambdaMaster")
  public List<UserEntity> lambdaMasterUsers() {
    return userService.selectLambdaMasterUsers();
  }

  @GetMapping("/lambdaSlave")//fixme 似乎不生效
  public List<UserEntity> lambdaSlaveUsers() {
    throw new CommonException(ResultCode.EXCEPTION);
//    return userService.selectLambdaSlaveUsers();
  }

  @GetMapping("slaveAnnotation")
  public List<UserEntity> slaveAnnotationUsers() {
    return userService.selectSlaveAnnotationUsers();
  }

  @PostMapping("add")
  public UserEntity addUser() {
    int nextInt = RANDOM.nextInt();
    UserEntity user = UserEntity.builder()
        .username("user_" + nextInt)
        .nickName("用户_" + nextInt)
        .password(passwordEncoder.encode("harry"))
        .gender(true)
        .avatar("url")
        .salt("harry")
        .status(true)
        .createBy(10010l)
        .updateBy(10010l)
        .build();

    userService.addUser(user);
    return user;
  }

  @DeleteMapping("/id/{id}")
  public String deleteUser(@PathVariable Long id) {
    userService.deleteUserById(id);
    return "成功删除用户" + id;
  }
}
