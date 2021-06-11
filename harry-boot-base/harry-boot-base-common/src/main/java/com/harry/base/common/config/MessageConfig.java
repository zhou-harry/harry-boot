package com.harry.base.common.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;

/**
 * @author zhouhong
 * @date 2019/5/31 17:35
 */
@Configuration("messageConfig")
public class MessageConfig {

    private final MessageSource messageSource;

    public MessageConfig(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Bean
    public MessageSourceAccessor messages() {
        return new MessageSourceAccessor(messageSource);
    }
}
