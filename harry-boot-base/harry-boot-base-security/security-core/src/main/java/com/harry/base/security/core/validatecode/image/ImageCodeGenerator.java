package com.harry.base.security.core.validatecode.image;

import static com.harry.base.security.core.constant.SecurityConstants.SERVLET_REQUEST_PARAM_HEIGHT;
import static com.harry.base.security.core.constant.SecurityConstants.SERVLET_REQUEST_PARAM_WIDTH;

import com.harry.base.security.core.constant.ValidateCodeTypeEnum;
import com.harry.base.security.core.properties.SecurityProperties;
import com.harry.base.security.core.util.VerifyCodeUtil;
import com.harry.base.security.core.validatecode.ValidateCode;
import com.harry.base.security.core.validatecode.ValidateCodeGenerator;
import java.awt.image.BufferedImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author harry
 * @version 1.0
 * @title: ImageCodeGenerator
 * @description: 图片验证码生成类
 * @date 2019/5/11 14:09
 */
@Component("imageCodeGenerator")
public class ImageCodeGenerator implements ValidateCodeGenerator {

  @Autowired
  private SecurityProperties securityProperties;

  @Override
  public ValidateCodeTypeEnum getValidateCodeType() {
    return ValidateCodeTypeEnum.IMAGE;
  }

  @Override
  public ValidateCode generator(ServletWebRequest request) {

    //先从request里读取有没有长、宽、字符个数参数，有的话就用，没有用默认的
    int width = ServletRequestUtils.getIntParameter(request.getRequest(), SERVLET_REQUEST_PARAM_WIDTH,
        securityProperties.getValidateCode().getImage().getWidth());

    int height = ServletRequestUtils.getIntParameter(request.getRequest(), SERVLET_REQUEST_PARAM_HEIGHT,
        securityProperties.getValidateCode().getImage().getHeight());

    int verifySize = this.securityProperties.getValidateCode().getImage().getLength();

    VerifyCodeUtil codeUtil = new VerifyCodeUtil(width, height, verifySize);

    BufferedImage image = codeUtil.getImage();

    return new ImageCode(
        image,
        codeUtil.getVerifyCode(),
        this.securityProperties.getValidateCode().getImage().getExpireIn()
    );
  }

}
