package com.xujie.mysecret.web.wechat;

import com.alibaba.fastjson.JSON;
import com.xujie.mysecret.entity.response.Response;
import com.xujie.mysecret.service.impl.WeChatServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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
    public Response mark(HttpServletRequest request) {

        log.info("get location msg is:{}", JSON.toJSONString(request.getParameterMap()));

        Response response = weChatService.saveMarkInfo(request);

        return response;
    }
}
