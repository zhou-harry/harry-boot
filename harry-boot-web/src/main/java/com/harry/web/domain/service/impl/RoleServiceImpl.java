package com.harry.web.domain.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harry.web.domain.entity.RoleEntity;
import com.harry.web.domain.mapper.RoleMapper;
import com.harry.web.domain.service.RoleService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@DS("master")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

  @Override
  public List<RoleEntity> selectByUserName(String userName) {
    return baseMapper.selectByUserName(userName);
  }
}
