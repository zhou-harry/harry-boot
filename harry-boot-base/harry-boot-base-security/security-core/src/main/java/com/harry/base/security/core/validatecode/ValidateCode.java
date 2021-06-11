package com.harry.base.security.core.validatecode;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author harry
 * @version 1.0
 * @title: ValidateCode
 * @description: 验证码
 * @date 2019/5/11 14:05
 */
@Data
public class ValidateCode implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String code;

    protected LocalDateTime expireTime;//过期时间点

    /**
     * @param code
     * @param expireTn 多少秒过期
     */
    public ValidateCode(String code, int expireTn) {
        this.code = code;
        //过期时间=当前时间+过期秒数
        this.expireTime = LocalDateTime.now().plusSeconds(expireTn);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    /**
     * 验证码是否过期
     *
     * @param @return true 过期，false 没过期
     * @return boolean   true 过期，false 没过期
     * @throws
     * @Description: 验证码是否过期
     * @author lihaoyang
     * @date 2018年3月2日
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }

}
