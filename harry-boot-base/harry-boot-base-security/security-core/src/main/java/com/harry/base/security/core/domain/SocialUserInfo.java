package com.harry.base.security.core.domain;

import lombok.Data;

/**
 * @author harry
 * @version 1.0
 * @title: SocialUserInfo
 * @description: TODO
 * @date 2019/5/12 21:19
 */
@Data
public class SocialUserInfo {

    private String providerId;

    private String providerUserId;

    private String nickname;

    private String headimg;

}
