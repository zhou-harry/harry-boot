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
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class UserEntity extends BaseEntity {

  @Builder
  public UserEntity(Timestamp createTime, Long createBy, Timestamp updateTime,
      Long updateBy, Long id, String username, String nickName, boolean gender,
      String phone, String email, String avatar, String password, String salt,
      Date birthday, boolean status, String thirdId, String thirdType) {
    super(createTime, createBy, updateTime, updateBy);
    this.id = id;
    this.username = username;
    this.nickName = nickName;
    this.gender = gender;
    this.phone = phone;
    this.email = email;
    this.avatar = avatar;
    this.password = password;
    this.salt = salt;
    this.birthday = birthday;
    this.status = status;
    this.thirdId = thirdId;
    this.thirdType = thirdType;
  }

  @TableId(value = "user_id",type = IdType.ASSIGN_ID)
  private Long id;

  @TableField("username")
  private String username;
  @TableField("nick_name")
  private String nickName;
  @TableField("gender")
  private boolean gender;
  @TableField("phone")
  private String phone;
  @TableField("email")
  private String email;
  @TableField("avatar")
  private String avatar;
  @TableField("password")
  private String password;
  @TableField("salt")
  private String salt;
  @TableField("birthday")
  private Date birthday;
  @TableField("status")
  private boolean status;
  @TableField("third_id")
  private String thirdId;
  @TableField("third_type")
  private String thirdType;

}
