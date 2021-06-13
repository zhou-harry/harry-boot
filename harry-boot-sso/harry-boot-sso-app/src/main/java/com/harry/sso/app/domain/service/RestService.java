package com.harry.sso.app.domain.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.harry.sso.app.domain.entity.RestEntity;
import java.util.List;

public interface RestService extends IService<RestEntity> {

  List<RestEntity> selectByUserName(String userName);

  List<RestEntity> selectByRoleId(Long roleId);
}
