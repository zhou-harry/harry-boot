package com.harry.base.security.core.properties;

import com.harry.base.security.core.constant.SecurityConstants;
import lombok.Data;

/**
 * @author harry
 * @version 1.0
 * @title: SocialProperties
 * @description: 社交认证配置属性
 * @date 2019/5/12 16:37
 */
@Data
public class SocialProperties {

  private String filterProcessUrl = SecurityConstants.DEFAULT_FILTER_PROCESSES_URL;

  private boolean open = false;//是否启用社交认证

  private QQProperties qq = new QQProperties();

  private WeixinProperties weixin = new WeixinProperties();


  @Data
  public class QQProperties {

    /**
     * Application id.
     */
    private String appId;

    /**
     * Application secret.
     */
    private String appSecret;

    private String providerId = "qq";

  }

  @Data
  public class WeixinProperties {

    /**
     * Application id.
     */
    private String appId;

    /**
     * Application secret.
     */
    private String appSecret;

    private String providerId = "weixin";

  }
}
