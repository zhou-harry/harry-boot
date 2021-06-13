package com.harry.sso.app.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.harry.sso.app.domain.entity.RestEntity;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RestMapper extends BaseMapper<RestEntity> {

  List<RestEntity> selectByUserName(@Param("userName") String userName);

  List<RestEntity> selectByRoleId(@Param("roleId") Long roleId);

}
