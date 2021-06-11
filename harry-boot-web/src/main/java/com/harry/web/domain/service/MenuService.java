package com.harry.web.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harry.web.domain.entity.MenuEntity;
import java.util.List;

public interface MenuService extends IService<MenuEntity> {

  List<MenuEntity> selectByUserName(String userName);

  List<MenuEntity> selectByRoleId(Long roleId);
}
