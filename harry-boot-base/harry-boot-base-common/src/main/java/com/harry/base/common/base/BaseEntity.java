package com.harry.base.common.base;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {

  @TableField("create_time")
  private Timestamp createTime;

  @TableField("create_by")
  private Long createBy;

  @TableField("update_time")
  private Timestamp updateTime;

  @TableField("update_by")
  private Long updateBy;

}
