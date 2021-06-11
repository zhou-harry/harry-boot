package com.harry.base.common.advice;

import com.harry.base.common.annotation.ResponseResult;
import com.harry.base.common.base.result.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice {

  @Override
  public boolean supports(MethodParameter methodParameter, Class aClass) {
    if (methodParameter.getMethodAnnotation(ResponseResult.class) != null){
      return true;
    }
    if (methodParameter.getContainingClass().getAnnotation(ResponseResult.class)!=null){
      return true;
    }
    return false;
  }

  @Override
  public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType,
      Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
    return BaseResult.success(body);
  }
}
