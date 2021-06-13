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
@TableName("sys_rest")
public class RestEntity extends BaseEntity {

  @TableId(value = "rest_id", type = IdType.ASSIGN_ID)
  private Long restId;
  @TableField("parent_url")
  private String parentUrl;
  @TableField("request_url")
  private String requestUrl;

  @Builder
  public RestEntity(Timestamp createTime, Long createBy, Timestamp updateTime,
      Long updateBy, Long restId, String parentUrl, String requestUrl) {
    super(createTime, createBy, updateTime, updateBy);
    this.restId = restId;
    this.parentUrl = parentUrl;
    this.requestUrl = requestUrl;
  }
}
