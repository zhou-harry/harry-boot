package com.harry.web.domain.service;

import com.harry.base.security.core.service.SecurityUserDetailService;
import com.harry.web.domain.entity.RoleEntity;
import com.harry.web.domain.entity.UserEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class WebUserDetailService extends SecurityUserDetailService {

  @Autowired
  private UserService userService;
  @Autowired
  private RoleService roleService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // TODO: 2021/5/29 获取用户信息
    UserEntity userEntity = userService.selectLambdaMasterUser(username);
    // TODO: 2021/5/29 获取用户权限
    List<RoleEntity> role = roleService.selectByUserName(username);
    List<String> roids = role.stream().map(entity -> entity.getRoleId().toString())
        .collect(Collectors.toList());
    return new SocialUser(
        userEntity.getUsername(),
        userEntity.getPassword(),
        AuthorityUtils.createAuthorityList(roids.toArray(new String[roids.size()]))
    );
  }

  @Override
  public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
    return super.loadUserByUserId(userId);
  }
}
