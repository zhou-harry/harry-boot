package com.harry.sso.app.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.harry.base.common.base.BaseEntity;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_menu")
public class MenuEntity extends BaseEntity {

  @TableId(value = "menu_id", type = IdType.ASSIGN_ID)
  private Long restId;
  @TableField("parent_id")
  private Long parentId;
  @TableField("menu_type")
  private Integer menuType;
  @TableField("title")
  private String title;
  @TableField("component")
  private String component;
  @TableField("menu_index")
  private Integer menuIndex;
  @TableField("icon")
  private String icon;
  @TableField("path")
  private String path;
  @TableField("hidden")
  private Integer hidden;
  @TableField("permission")
  private String permission;

  @Builder
  public MenuEntity(Timestamp createTime, Long createBy, Timestamp updateTime,
      Long updateBy, Long restId, Long parentId, Integer menuType, String title,
      String component, Integer menuIndex, String icon, String path, Integer hidden,
      String permission) {
    super(createTime, createBy, updateTime, updateBy);
    this.restId = restId;
    this.parentId = parentId;
    this.menuType = menuType;
    this.title = title;
    this.component = component;
    this.menuIndex = menuIndex;
    this.icon = icon;
    this.path = path;
    this.hidden = hidden;
    this.permission = permission;
  }
}
