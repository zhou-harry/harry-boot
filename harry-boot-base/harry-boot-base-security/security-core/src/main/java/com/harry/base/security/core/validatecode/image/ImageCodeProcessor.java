package com.harry.base.security.core.validatecode.image;

import com.harry.base.security.core.constant.SecurityConstants;
import com.harry.base.security.core.validatecode.AbstractValidateCodeProcessor;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author harry
 * @version 1.0
 * @title: ImageCodeProcessor
 * @description: 图片验证码处理器
 * @date 2019/5/11 23:08
 */
@Component(SecurityConstants.DEFAULT_IMAGE_CODE_PROCESSOR)
public class ImageCodeProcessor extends
    AbstractValidateCodeProcessor<ImageCode, ImageCodeGenerator> {

  private static final String formatName = "JPEG";
  @Autowired
  private ImageCodeGenerator imageCodeGenerator;

  @Override
  protected ImageCodeGenerator getValidateCodeGenerator() {
    return imageCodeGenerator;
  }

  @Override
  protected void send(ServletWebRequest request, ImageCode validateCode) throws Exception {
    request.getResponse().addHeader("Content-Type", "image/jpeg");
    ImageIO.write(
        validateCode.getImage(),
        formatName,
        request.getResponse().getOutputStream()
    );
  }
}
