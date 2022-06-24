package com.houcheng.emplate.controller;

import com.houcheng.emplate.common.Result;
import com.houcheng.emplate.utils.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * author:tutu
 * version:1.0
 */
@RestController
@RequestMapping("/code")
public class CodeController {
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 用户登录/注册校验码生成
     * 生成验证码后，将本次生成验证码操作存入redis中，有效期为3分钟
     * 键值规则为  USER_VERIFYCODE_SESSION + UUID : 4位数字验证码
     * @param
     * @param
     * @return
     */
    @GetMapping(path = "/getVerifyCodePic")
    public Result getVerifyCodePic() throws IOException {
        Map<String, String> result = new HashMap<>();
        VerifyCodeUtils code = new VerifyCodeUtils();
        // 生成验证码图片
        BufferedImage image = code.getImage();
        // 获取验证码四位数字
        String text = code.getText();
        // 验证码-键值对存入分别存入redis
        String verifyCode_key = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(verifyCode_key,text,60*3, TimeUnit.SECONDS);
        //进行base64编码
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try{
            ImageIO.write(image, "png", bos);
            String string = Base64Utils.encodeToString(bos.toByteArray());
            result.put("key", verifyCode_key);
            result.put("image", string);
            return Result.success(result);
        }catch (IOException e){
            System.out.println(e);
        }finally {
            bos.close();
        }
        return Result.error("-1","生成验证码失败");
    }
}
