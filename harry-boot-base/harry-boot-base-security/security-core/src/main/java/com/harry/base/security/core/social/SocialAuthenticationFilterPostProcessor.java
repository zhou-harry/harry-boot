package com.harry.base.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 *
 * @see SocialConfig.socialSecurityConfig()
 */
public interface SocialAuthenticationFilterPostProcessor {

	void process(SocialAuthenticationFilter socialAuthenticationFilter);

}
