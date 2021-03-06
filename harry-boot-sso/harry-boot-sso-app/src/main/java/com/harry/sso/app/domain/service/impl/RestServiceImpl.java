package com.harry.sso.app.domain.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harry.sso.app.domain.entity.RestEntity;
import com.harry.sso.app.domain.mapper.RestMapper;
import com.harry.sso.app.domain.service.RestService;
import java.util.List;
import org.springframework.stereotype.Service;

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
