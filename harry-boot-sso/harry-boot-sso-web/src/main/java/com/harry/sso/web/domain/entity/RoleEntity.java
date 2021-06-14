package com.harry.sso.web.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.harry.base.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role")
public class RoleEntity extends BaseEntity {

  @Builder
  public RoleEntity(Timestamp createTime, Long createBy, Timestamp updateTime,
      Long updateBy, Long roleId, String name, Integer level, String description) {
    super(createTime, createBy, updateTime, updateBy);
    this.roleId = roleId;
    this.name = name;
    this.level = level;
    this.description = description;
  }

  @TableId(value = "role_id", type = IdType.ASSIGN_ID)
  private Long roleId;
  @TableField("name")
  private String name;
  @TableField("level")
  private Integer level;
  @TableField("description")
  private String description;

}
