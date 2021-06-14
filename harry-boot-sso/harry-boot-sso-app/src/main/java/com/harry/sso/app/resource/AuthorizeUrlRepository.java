package com.harry.sso.app.resource;

import com.google.common.collect.Sets;
import com.harry.base.security.core.authorize.repository.SecurityAuthorizeUrlRepository;
import com.harry.sso.app.domain.entity.MenuEntity;
import com.harry.sso.app.domain.entity.RestEntity;
import com.harry.sso.app.domain.service.MenuService;
import com.harry.sso.app.domain.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorizeUrlRepository implements SecurityAuthorizeUrlRepository {

  @Autowired
  private RestService restService;
  @Autowired
  private MenuService menuService;

  @Override
  public Set<String> loadUrlByUsername(String username) {
    // TODO: 2019/5/25  从数据库查询用户所拥有权限的所有后台服务url
    List<RestEntity> rests = restService.selectByUserName(username);
    // TODO: 2021/5/30  从数据库查询用户所拥有权限的所有菜单url
    List<MenuEntity> menus = menuService.selectByUserName(username);

    Set<String> urls = Sets.newHashSet();
    urls.addAll(this.formatRests(rests));
    urls.addAll(this.formatMenus(menus));
    return urls;
  }

  @Override
  public Set<String> loadUrlByAuthority(Collection<? extends GrantedAuthority> authorities) {
    Set<String> urls = Sets.newHashSet();
    for (GrantedAuthority authority : authorities) {
      Long roleId = Long.valueOf(null == authority.getAuthority() ? "0" : authority.getAuthority());
      // TODO: 2019/5/25  从数据库查询用户所拥有权限的所有后台服务url
      List<RestEntity> rests = restService.selectByRoleId(roleId);
      // TODO: 2021/5/30  从数据库查询用户所拥有权限的所有菜单url
      List<MenuEntity> menus = menuService.selectByRoleId(roleId);

      urls.addAll(this.formatRests(rests));
      urls.addAll(this.formatMenus(menus));
    }
    return urls;
  }

  private Set<String> formatMenus(List<MenuEntity> menus) {
    if (ObjectUtils.isEmpty(menus)) {
      return Sets.newHashSet();
    }
    return menus.stream()
        .map(MenuEntity::getPath)
        .collect(Collectors.toSet());
  }

  private Set<String> formatRests(List<RestEntity> rests) {
    if (ObjectUtils.isEmpty(rests)) {
      return Sets.newHashSet();
    }
    return rests.stream()
        .map(entity -> String.join("/", entity.getParentUrl(), entity.getRequestUrl())).collect(
            Collectors.toSet());
  }
}
