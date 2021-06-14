package com.harry.sso.web.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.harry.sso.web.domain.entity.RestEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RestMapper extends BaseMapper<RestEntity> {

  List<RestEntity> selectByUserName(@Param("userName") String userName);

  List<RestEntity> selectByRoleId(@Param("roleId") Long roleId);

}
