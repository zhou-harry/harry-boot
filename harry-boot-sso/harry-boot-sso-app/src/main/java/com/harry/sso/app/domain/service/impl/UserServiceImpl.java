package com.harry.sso.app.domain.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.annotation.Slave;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harry.sso.app.domain.entity.UserEntity;
import com.harry.sso.app.domain.mapper.UserMapper;
import com.harry.sso.app.domain.service.UserService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@DS("master")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

  @Override
  public List<UserEntity> selectMasterUsers() {
    return baseMapper.selectList(null);
  }

  @Override
  @DS("slave")
  public List<UserEntity> selectSlaveUsers() {
    return baseMapper.selectList(null);
  }

  @Override
  public List<UserEntity> selectLambdaMasterUsers() {
    return this.lambdaQuery().list();
  }

  public UserEntity selectLambdaMasterUser(String userName) {
    LambdaQueryWrapper<UserEntity> queryChainWrapper = Wrappers.<UserEntity>lambdaQuery()
        .eq(UserEntity::getUsername, userName);
    return baseMapper.selectOne(queryChainWrapper);
  }

  @Override
  @DS("slave")
  public List<UserEntity> selectLambdaSlaveUsers() {
    return this.lambdaQuery().list();
  }

  @Override
  @Slave
  public List<UserEntity> selectSlaveAnnotationUsers() {
    return this.lambdaQuery().list();
  }

  @Override
  public void addUser(UserEntity user) {
    baseMapper.insert(user);
  }

  @Override
  public void deleteUserById(Long id) {
    baseMapper.deleteById(id);
  }
}
