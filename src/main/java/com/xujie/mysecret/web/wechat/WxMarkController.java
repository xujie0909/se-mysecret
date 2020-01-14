package com.xujie.mysecret.web.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xujie.mysecret.entity.WxResponse;
import com.xujie.mysecret.service.impl.WeChatServiceImpl;
import com.xujie.mysecret.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * @author xujie17
 */
@Controller
@RequestMapping(value = "/wx")
@Slf4j
public class WxMarkController {

    private final WeChatServiceImpl weChatService;

    public WxMarkController(WeChatServiceImpl weChatService) {
        this.weChatService = weChatService;
    }

    @RequestMapping(value = "/mark", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public WxResponse mark(HttpServletRequest request) {

        log.info("get location msg is:{}", JSON.toJSONString(request.getParameterMap()));

        WxResponse wxResponse = weChatService.saveMarkInfo(request);

        return wxResponse;
    }
}
