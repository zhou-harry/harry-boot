package com.harry.sso.web.domain.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harry.sso.web.domain.entity.MenuEntity;
import com.harry.sso.web.domain.mapper.MenuMapper;
import com.harry.sso.web.domain.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DS("master")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements MenuService {

  @Override
  public List<MenuEntity> selectByUserName(String userName) {
    return baseMapper.selectByUserName(userName);
  }

  @Override
  public List<MenuEntity> selectByRoleId(Long roleId) {
    return baseMapper.selectByRoleId(roleId);
  }
}
