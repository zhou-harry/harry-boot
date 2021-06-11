package com.harry.api.feign.falback;

import com.google.common.collect.Lists;
import com.harry.api.feign.FeignClientApi;
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * 调用端熔断/降级
 */
@Data
@Builder
public class ClientFallback implements FeignClientApi {

  private String errorMessage;

  @Override
  public String getServer() {
    return "服务请求失败了=" + errorMessage;
  }

  @Override
  public List<String> getServiceList() {
    return Lists.newArrayList();
  }

  @Override
  public Object getInstance(String name) {
    return null;
  }

  @Override
  public Object getInstanceByName(String name) {
    return null;
  }

}
