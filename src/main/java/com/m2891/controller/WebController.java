package com.m2891.controller;

import com.google.code.kaptcha.Producer;
import com.m2891.pojo.R;
import com.m2891.pojo.dto.CaptchaDto;
import com.m2891.service.SysService;
import com.m2891.util.CacheUtil;
import com.m2891.util.id.IDUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("web")
public class WebController
{
    @Autowired
    private Producer captchaProducer;

    @Autowired
    private Producer captchaProducerMath;
    @Autowired
    private SysService sysService;

    @GetMapping("captcha")
    public R<CaptchaDto> captcha() throws IOException
    {
        String text = captchaProducer.createText();
        BufferedImage image = captchaProducer.createImage(text);
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        ImageIO.write(image, "jpg", os);
        byte[] bytes = os.toByteArray();
        String captchaBase64 = new String(Base64.encodeBase64(bytes));
        String uuid = IDUtils.getUUID();
        CacheUtil.setCaptcha(uuid, text);
        return R.success(new CaptchaDto().setCaptchaId(uuid).setCaptchaBase64(captchaBase64));
    }
    
    @GetMapping("config")
    public Object config()
    {
        return  R.success(sysService.getSysConfig());
//        return "{\"errorCode\":0,\"message\":\"\",\"data\":{\"siteTitle\":\"bbs-go演示站1\",\"siteDescription\":\"bbs-go，基于Go语言的开源社区系统\",\"siteKeywords\":null,\"siteNavs\":[{\"title\":\"首页\",\"url\":\"/\"},{\"title\":\"话题\",\"url\":\"/topics\"},{\"title\":\"文章\",\"url\":\"/articles\"}],\"siteNotification\":\"你好\",\"recommendTags\":null,\"urlRedirect\":false,\"scoreConfig\":{\"postTopicScore\":1,\"postCommentScore\":1,\"checkInScore\":1},\"defaultNodeId\":2,\"articlePending\":false,\"topicCaptcha\":false,\"userObserveSeconds\":0,\"tokenExpireDays\":365,\"loginMethod\":{\"password\":true,\"qq\":false,\"github\":false,\"osc\":false},\"createTopicEmailVerified\":false,\"createArticleEmailVerified\":true,\"createCommentEmailVerified\":false,\"enableHideContent\":true,\"modules\":[{\"module\":\"tweet\",\"enabled\":true},{\"module\":\"topic\",\"enabled\":true},{\"module\":\"article\",\"enabled\":true}],\"emailWhitelist\":null},\"success\":true}";
    }

        @GetMapping("topicNodes")
    public Object topicNodes(String tag)
    {
        return R.success(sysService.getTopiNodes(tag));
    }

    /**
     * 友链
     */
    @GetMapping("backlink")
    public Object backlink()
    {
        return R.success();
    }
}
