package com.harry.sso.web.domain.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.harry.sso.web.domain.entity.UserEntity;

import java.util.List;

public interface UserService extends IService<UserEntity> {

  List<UserEntity> selectMasterUsers();

  List<UserEntity> selectSlaveUsers();

  List<UserEntity> selectLambdaMasterUsers();

  List<UserEntity> selectLambdaSlaveUsers();

  List<UserEntity> selectSlaveAnnotationUsers();

  UserEntity selectLambdaMasterUser(String userName);

  void addUser(UserEntity user);

  void deleteUserById(Long id);
}