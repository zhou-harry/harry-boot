package com.harry.base.security.core.authorize.repository;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Set;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author harry
 * @version 1.0
 * @title: DefaultAuthorizeUrlRepository
 * @description: TODO
 * @date 2019/5/25 19:49
 */
@Data
public class SecurityAuthorizeUrlRepository implements AuthorizeUrlRepository {

  @Override
  public Set<String> loadUrlByUsername(String username) {
    // TODO: 2019/5/25  从数据库查询用户所拥有权限的所有url
    return Sets.newHashSet();
  }

  @Override
  public Set<String> loadUrlByAuthority(Collection<? extends GrantedAuthority> authorities) {
    // TODO: 2019/5/25  从数据库查询用户所拥有权限的所有url
    return Sets.newHashSet();
  }
}
