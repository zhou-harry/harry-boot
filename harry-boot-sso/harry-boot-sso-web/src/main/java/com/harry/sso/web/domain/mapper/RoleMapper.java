package com.harry.sso.web.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.harry.sso.web.domain.entity.RoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper<RoleEntity> {

  List<RoleEntity> selectByUserName(@Param("userName") String userName);

}
