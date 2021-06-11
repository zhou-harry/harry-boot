package com.harry.base.security.app.config;

import com.harry.base.security.core.constant.SecurityConstants;
import com.harry.base.security.core.properties.SecurityProperties;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author harry
 * @version 1.0
 * @title: AuthServerConfig
 * @description: Oauth2认证服务配置
 * @date 2019/5/20 21:56
 */
@Configuration
@EnableAuthorizationServer
public class AppAuthServerConfig extends AuthorizationServerConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final DataSource dataSource;
  private final TokenStore tokenStore;
  private final UserDetailsService userDetailsService;
  private final SecurityProperties securityProperties;
  private final JwtAccessTokenConverter jwtAccessTokenConverter;
  private final TokenEnhancer jwtTokenEnhancer;

  public AppAuthServerConfig(
      PasswordEncoder passwordEncoder,
      AuthenticationManager authenticationManager,
      DataSource dataSource,
      TokenStore tokenStore,
      UserDetailsService userDetailsService,
      SecurityProperties securityProperties,
      @Autowired(required = false)
          JwtAccessTokenConverter jwtAccessTokenConverter,
      @Autowired(required = false)
          TokenEnhancer jwtTokenEnhancer) {
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
    this.dataSource = dataSource;
    this.tokenStore = tokenStore;
    this.userDetailsService = userDetailsService;
    this.securityProperties = securityProperties;
    this.jwtAccessTokenConverter = jwtAccessTokenConverter;
    this.jwtTokenEnhancer = jwtTokenEnhancer;
  }

  @Override
  public void configure(
      AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
    oauthServer
        .tokenKeyAccess("permitAll()")
        .checkTokenAccess("isAuthenticated()")
        .allowFormAuthenticationForClients();
  }

  /**
   * client信息从数据库获取
   *
   * @param clients
   * @throws Exception
   */
  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.jdbc(dataSource)
        .passwordEncoder(passwordEncoder);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints
        .authenticationManager(authenticationManager)
        .tokenStore(tokenStore)
        .userDetailsService(userDetailsService);

    if (SecurityConstants.DEFAULT_OAUTH2_STORETYPE
        .equals(securityProperties.getOauth2().getStoreType())) {
      TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
      List<TokenEnhancer> enhancers = new ArrayList<>();
      enhancers.add(jwtTokenEnhancer);
      enhancers.add(jwtAccessTokenConverter);
      enhancerChain.setTokenEnhancers(enhancers);

      endpoints
          .tokenEnhancer(enhancerChain)
          .accessTokenConverter(jwtAccessTokenConverter);
    }
  }
}