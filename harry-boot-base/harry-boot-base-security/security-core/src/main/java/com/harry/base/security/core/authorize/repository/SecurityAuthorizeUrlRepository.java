package com.harry.base.security.core.authorize.repository;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

/**
 * @author harry
 * @version 1.0
 * @title: AuthorizeUrlRepository
 * @description: 加载用户所拥有权限的所有url
 * @date 2019/5/25 19:30
 */
public interface SecurityAuthorizeUrlRepository {

    Set<String> loadUrlByUsername(String username);

    Set<String> loadUrlByAuthority(Collection<? extends GrantedAuthority> authorities);

}
