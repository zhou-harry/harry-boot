package com.harry.sso.api.feign;

import com.harry.base.common.base.result.BaseResult;
import com.harry.sso.api.dto.UserDTO;
import com.harry.sso.api.feign.falback.ClientFallbackFactory;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "${harry.sso.api.providerServiceName:}",
        url = "${harry.sso.api.providerServiceUrl:}",
        fallbackFactory = ClientFallbackFactory.class
)
public interface RemoteSSOApi {

  @GetMapping("/user/master")
  BaseResult<List<UserDTO>> masterUsers();

  @GetMapping("/user/slave")
  BaseResult<List<UserDTO>> slaveUsers();

  @GetMapping("/user/lambdaMaster")
  BaseResult<List<UserDTO>> lambdaMasterUsers();

  @GetMapping("/user/lambdaSlave")
  BaseResult<List<UserDTO>> lambdaSlaveUsers();

  @GetMapping("/user/slaveAnnotation")
  BaseResult<List<UserDTO>> slaveAnnotationUsers() ;

  @PostMapping("/user/add")
  BaseResult<UserDTO> addUser(UserDTO user);

  @DeleteMapping("/user/id/{id}")
  BaseResult<String> deleteUser(@PathVariable Long id) ;

}
