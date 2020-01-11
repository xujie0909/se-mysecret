package com.xujie.mysecret.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author xujie17
 */
public interface WeChatService {

    /**
     * 微信各种监听事件
     * @param request 请求
     * @param response 响应
     * @return 返回
     */
    String weChatHandle(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取ticket
     * @return ticket
     */
    String getJsapiTicket();


}
