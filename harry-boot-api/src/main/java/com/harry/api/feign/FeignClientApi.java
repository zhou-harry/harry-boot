package com.harry.api.feign;

import com.harry.api.feign.falback.ClientFallbackFactory;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "${com.harry.api.providerServiceName}",
        url = "${com.harry.api.providerServiceName}",
        fallbackFactory = ClientFallbackFactory.class
)
public interface FeignClientApi {

  @GetMapping("/server")
  String getServer();

  @GetMapping("/service")
  List<String> getServiceList();

  @GetMapping("/instance")
  Object getInstance(@RequestParam("name") String name);

  @GetMapping("/instance2/{name}")
  Object getInstanceByName(@PathVariable("name") String name);

}
