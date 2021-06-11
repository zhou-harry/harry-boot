package com.harry.base.common.base.result;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel("统一返回格式说明")
public class BaseResult<T> implements Serializable {

  private Integer code;
  private String message;
  private T data;

  /**
   * 返回成功
   *
   * @param data
   * @param <T>
   * @return
   */
  public static <T> BaseResult success(T data) {
    return BaseResult.builder()
        .code(ResultCode.SUCCESS.getCode())
        .message(ResultCode.SUCCESS.getMessage())
        .data(data)
        .build();
  }

  /**
   * 返回成功
   *
   * @param <T>
   * @return
   */
  public static <T> BaseResult success() {
    return BaseResult.builder()
        .code(ResultCode.SUCCESS.getCode())
        .message(ResultCode.SUCCESS.getMessage())
        .build();
  }

  /**
   * 返回失敗
   *
   * @param resultCode
   * @param data
   * @param <T>
   * @return
   */
  public static <T> BaseResult fail(ResultCode resultCode, T data) {
    return BaseResult.builder()
        .code(resultCode.getCode())
        .message(resultCode.getMessage())
        .data(data)
        .build();
  }

  /**
   * 返回异常
   * @param message
   * @param data
   * @param <T>
   * @return
   */
  public static <T> BaseResult exception(String message,T data) {
    return BaseResult.builder()
        .code(ResultCode.EXCEPTION.getCode())
        .message(message)
        .data(data)
        .build();
  }

  /**
   * 返回失败
   *
   * @param resultCode
   * @param args
   * @return
   */
  public static BaseResult fail(ResultCode resultCode, String... args) {
    return BaseResult.builder()
        .code(resultCode.getCode())
        .message(resultCode.getMessage(args))
        .build();
  }
}
