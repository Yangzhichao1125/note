package com.yang.springboot.code.controler;

import com.alibaba.fastjson.JSONObject;
import com.yang.springboot.code.util.WeChatUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This is Description
 *
 * @author yang
 * @date 2019/09/17
 */

@Controller
@Log4j2
public class WXLogin {

    // 微信小程序ID
    @Value("${wx.appid}")
    String appid ;//= "wxf465ec59a088a2b1";
    // 微信小程序秘钥
    @Value("${wx.appsecret}")
    String secret ;//= "220f16d2f88e47ada57eb38f2b81867f";

    @ResponseBody
    @RequestMapping("/wxLogin")
    public void login(String username,String password,String code){

        // 根据小程序穿过来的code想这个url发送请求
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
        // 发送请求，返回Json字符串
        String str = WeChatUtil.httpRequest(url, "GET", null);
        // 转成Json对象 获取openid
        JSONObject jsonObject = JSONObject.parseObject(str);
        // 我们需要的openid，在一个小程序中，openid是唯一的
        String openid = jsonObject.get("openid").toString();

    }
}
