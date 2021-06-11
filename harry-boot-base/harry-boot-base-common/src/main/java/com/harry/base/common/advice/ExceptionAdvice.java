package com.harry.base.common.advice;

import com.harry.base.common.base.result.BaseResult;
import com.harry.base.common.exception.CommonException;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler(CommonException.class)
  public BaseResult<HttpStatus> handleException(HttpServletRequest request, CommonException e) {
    //输出日志
    this.showLog(e);

    return BaseResult.exception(e.getMessage(), getStatus(request));
  }

  /**
   * 输出日志
   *
   * @param e
   */
  private void showLog(Exception e) {
    Throwable throwable = null == e.getCause() ? e : e.getCause();
    log.error("[{}；{}；cause：{}]",
        e.getMessage(),
        throwable.getStackTrace()[0].toString(),
        throwable.toString());
  }

  private HttpStatus getStatus(HttpServletRequest request) {
    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    if (statusCode == null) {
      return HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return HttpStatus.valueOf(statusCode);
  }

}
