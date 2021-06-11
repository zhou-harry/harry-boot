package com.harry.base.security.core.validatecode;

import com.harry.base.security.core.constant.ValidateCodeTypeEnum;
import com.harry.base.security.core.exception.ValidateCodeException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author harry
 * @version 1.0
 * @title: ValidateCodeProcessorHolder
 * @description: 适配器模式（找出验证码的处理器）
 * @date 2019/5/14 16:35
 */
@Component
public class ValidateCodeProcessorHolder {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeTypeEnum type) {

        ValidateCodeProcessor processor = validateCodeProcessors.get(type.getProcessor());
        if (processor == null) {
            throw new ValidateCodeException("验证码处理器" + type.getProcessor() + "不存在");
        }
        return processor;
    }

}
