package com.harry.base.common.base.result;

import com.harry.base.common.util.ApplicationUtil;

public enum ResultCode {
  SUCCESS(200,"操作成功"),
  EXCEPTION(500,"操作异常"),
  /**
   * 1001-1999 参数错误
   */
  PARM_IS_INVALID(1001,"参数无效"),
  PARM_IS_BLANK(1002,"参数为空"),
  PARM_TYPE_BIND_ERROR(1003,"参数类型错误"),
  PARM_NOT_COMPLETE(1004,"参数缺失"),

  /**
   * 2001-2999 用户错误
   */
  USER_NOT_LOGGIN(2001,"用户未登录"),
  USER_LOGGIN_ERROR(2002,"账号不存在或密码错误"),
  USER_ACCOUNT_FORBINDDEN(2003,"账号被禁用"),
  USER_NOT_EXIST(2004,"用户或密码不存在"),
  USER_HAS_EXISTED(2005,"用户已存在"),
  ;

  private Integer code;
  private String message;

  ResultCode(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return ApplicationUtil.getMSA().getMessage(this.code.toString(), this.message);
  }

  public String getMessage(Object... args) {
    return ApplicationUtil.getMSA().getMessage(this.code.toString(), args, this.message);
  }

  public String toJsonString() {
    return "{\"code\":" + code + ",\"message\":\"" + message + "\"}";
  }
}
