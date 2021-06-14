package com.harry.base.security.core.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.security.SocialUserDetailsService;

/**
 * @author harry
 * @version 1.0
 * @title: SecurityUserDetailService
 * @description: TODO
 * @date 2019/5/12 16:14
 */
public interface SecurityUserDetailService extends UserDetailsService, SocialUserDetailsService {

}
