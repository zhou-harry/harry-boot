package com.harry.web.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.harry.web.domain.entity.MenuEntity;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuMapper extends BaseMapper<MenuEntity> {

  List<MenuEntity> selectByUserName(@Param("userName") String userName);

  List<MenuEntity> selectByRoleId(@Param("roleId") Long roleId);

}
