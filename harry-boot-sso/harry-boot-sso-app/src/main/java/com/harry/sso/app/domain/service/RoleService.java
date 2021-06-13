package com.harry.sso.app.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harry.sso.app.domain.entity.RoleEntity;
import java.util.List;

public interface RoleService extends IService<RoleEntity> {

  List<RoleEntity> selectByUserName(String userName);
}
