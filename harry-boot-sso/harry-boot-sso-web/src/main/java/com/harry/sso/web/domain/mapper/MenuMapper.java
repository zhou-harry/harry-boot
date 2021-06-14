package com.harry.sso.web.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.harry.sso.web.domain.entity.MenuEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper extends BaseMapper<MenuEntity> {

  List<MenuEntity> selectByUserName(@Param("userName") String userName);

  List<MenuEntity> selectByRoleId(@Param("roleId") Long roleId);

}
