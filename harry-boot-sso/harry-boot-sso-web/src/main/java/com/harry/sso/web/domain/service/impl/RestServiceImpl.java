package com.harry.sso.web.domain.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harry.sso.web.domain.entity.RestEntity;
import com.harry.sso.web.domain.mapper.RestMapper;
import com.harry.sso.web.domain.service.RestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DS("master")
public class RestServiceImpl extends ServiceImpl<RestMapper, RestEntity> implements RestService {

  @Override
  public List<RestEntity> selectByUserName(String userName) {
    return baseMapper.selectByUserName(userName);
  }

  @Override
  public List<RestEntity> selectByRoleId(Long roleId) {
    return baseMapper.selectByRoleId(roleId);
  }
}
