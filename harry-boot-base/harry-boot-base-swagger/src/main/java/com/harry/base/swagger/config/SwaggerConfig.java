package com.harry.base.swagger.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.harry.base.swagger.properties.SwaggerProperties;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationCodeGrant;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerConfig {

  private static final String DEFAULT_EXCLUDE_PATH = "/error";
  private static final String BASE_PATH = "/**";
  public static final String BASE_OAUTH2_TOKEN = "/oauth/token";
  public static final String BASE_OAUTH2_AUTHORIZE = "/oauth/authorize";

  private final SwaggerProperties properties;

  public SwaggerConfig(SwaggerProperties properties) {
    this.properties = properties;
  }

  @Bean
  public Docket api() {
    // base-path处理
    if (ObjectUtils.isEmpty(properties.getBasePath())) {
      properties.getBasePath().add(BASE_PATH);
    }
    //noinspection unchecked
    List<Predicate<String>> basePath = properties.getBasePath().stream()
        .map(path -> PathSelectors.ant(path))
        .collect(Collectors.toList());

    // exclude-path处理
    if (ObjectUtils.isEmpty(properties.getExcludePath())) {
      properties.getExcludePath().add(DEFAULT_EXCLUDE_PATH);
    }
    List<Predicate<String>> excludePath = properties.getExcludePath().stream()
        .map(path -> PathSelectors.ant(path))
        .collect(Collectors.toList());
    //noinspection Guava
    return new Docket(DocumentationType.SWAGGER_2)
        .host(properties.getHost())
        .apiInfo(apiInfo()).select()
        .apis(RequestHandlerSelectors.basePackage(properties.getBasePackage()))
        .paths(Predicates.and(Predicates.not(Predicates.or(excludePath)), Predicates.or(basePath)))
        .build()
        .securitySchemes(Collections.singletonList(securitySchema()))
        .securityContexts(Collections.singletonList(securityContext()))
        .pathMapping("/");
  }

  private OAuth securitySchema() {

    List<AuthorizationScope> authorizationScopeList = properties.getAuthorization()
        .getAuthorizationScopeList().stream()
        .map(authorizationScope -> new AuthorizationScope(
                authorizationScope.getScope(),
                authorizationScope.getDescription()
            )
        ).collect(Collectors.toList());

    List<GrantType> grantTypes = properties.getAuthorization().getAuthServers().stream().map(
        authServer -> {
          //授权码模式(authorization_code)
          TokenRequestEndpoint tokenRequestEndpoint = new TokenRequestEndpoint(
              authServer + BASE_OAUTH2_AUTHORIZE, "SWAGGER-CLIENT", "SWAGGER-SECRET");
          TokenEndpoint tokenEndpoint = new TokenEndpoint(authServer + BASE_OAUTH2_TOKEN,
              "access_token");
          return new AuthorizationCodeGrant(tokenRequestEndpoint, tokenEndpoint);
          //密码模式
//          return new ResourceOwnerPasswordCredentialsGrant(authServer+BASE_OAUTH2_TOKEN);
          //客户端模式（client credentials）
//          return new ClientCredentialsGrant(authServer+BASE_OAUTH2_TOKEN);
        }
    ).collect(Collectors.toList());

    return new OAuth(properties.getAuthorization().getName(), authorizationScopeList, grantTypes);
  }

  /**
   * 配置默认的全局鉴权策略的开关，通过正则表达式进行匹配；默认匹配所有URL
   *
   * @return
   */
  private SecurityContext securityContext() {
    return SecurityContext.builder()
        .securityReferences(defaultAuth())
        .forPaths(PathSelectors.regex(properties.getAuthorization().getAuthRegex()))
        .build();
  }

  /**
   * 默认的全局鉴权策略
   *
   * @return
   */
  private List<SecurityReference> defaultAuth() {
    List<AuthorizationScope> authorizationScopeList = properties.getAuthorization()
        .getAuthorizationScopeList()
        .stream().map(
            authorizationScope -> new AuthorizationScope(
                authorizationScope.getScope(),
                authorizationScope.getDescription()
            )
        ).collect(Collectors.toList());

    AuthorizationScope[] authorizationScopes = new AuthorizationScope[authorizationScopeList
        .size()];
    return Collections.singletonList(SecurityReference.builder()
        .reference(properties.getAuthorization().getName())
        .scopes(authorizationScopeList.toArray(authorizationScopes))
        .build());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title(properties.getTitle())
        .description(properties.getDescription())
        .license(properties.getLicense())
        .licenseUrl(properties.getLicenseUrl())
        .termsOfServiceUrl(properties.getTermsOfServiceUrl())
        .contact(new Contact(properties.getContact().getName(),
            properties.getContact().getUrl(), properties.getContact().getEmail()))
        .version(properties.getVersion())
        .build();
  }

}
