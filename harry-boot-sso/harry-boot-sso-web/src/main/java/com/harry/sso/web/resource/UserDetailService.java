package com.harry.sso.web.resource;

import com.harry.base.security.core.service.SecurityUserDetailService;
import com.harry.sso.web.domain.entity.RoleEntity;
import com.harry.sso.web.domain.entity.UserEntity;
import com.harry.sso.web.domain.service.RoleService;
import com.harry.sso.web.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserDetailService implements SecurityUserDetailService {

  @Autowired
  private UserService userService;
  @Autowired
  private RoleService roleService;
  @Autowired
  private PasswordEncoder passwordEncoder;

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
    log.debug("查询用户信息：userId="+userId);

    //查询数据库用户信息

    //将查询出的的用户信息组装并返回
    return this.buildUser(userId);
  }

  private SocialUserDetails buildUser(String userId){
    SocialUser user = new SocialUser(
        userId,
        passwordEncoder.encode("harry"),
        AuthorityUtils.createAuthorityList("admin")
    );
    return user;
  }
}
