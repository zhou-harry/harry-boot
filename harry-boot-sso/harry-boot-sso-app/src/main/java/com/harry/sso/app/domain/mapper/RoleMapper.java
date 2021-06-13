package com.harry.sso.app.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.harry.sso.app.domain.entity.RoleEntity;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper extends BaseMapper<RoleEntity> {

  List<RoleEntity> selectByUserName(@Param("userName") String userName);

}
