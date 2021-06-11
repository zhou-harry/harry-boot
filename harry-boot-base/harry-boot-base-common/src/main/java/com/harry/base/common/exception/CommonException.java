package com.harry.base.common.exception;

import com.harry.base.common.base.result.ResultCode;

public class CommonException extends RuntimeException{

  public CommonException(ResultCode resultCode) {
    super(resultCode.getMessage());
  }

  public CommonException(ResultCode resultCode, Object... args) {
    super(resultCode.getMessage(args));
  }

  public CommonException(ResultCode resultCode, Throwable cause) {
    super(resultCode.getMessage(), cause);
  }
}
