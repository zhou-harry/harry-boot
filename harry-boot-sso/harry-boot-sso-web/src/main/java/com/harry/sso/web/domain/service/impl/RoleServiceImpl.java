package com.harry.sso.web.domain.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harry.sso.web.domain.entity.RoleEntity;
import com.harry.sso.web.domain.mapper.RoleMapper;
import com.harry.sso.web.domain.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DS("master")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

  @Override
  public List<RoleEntity> selectByUserName(String userName) {
    return baseMapper.selectByUserName(userName);
  }
}
