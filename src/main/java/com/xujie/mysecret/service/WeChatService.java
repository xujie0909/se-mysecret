package com.xujie.mysecret.service;

import com.xujie.mysecret.entity.WxResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;


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
    HashMap<String, String> getSignature(String url);


    /**
     * 微信响应
     * @param request 请求
     * @return 响应
     */
    WxResponse saveMarkInfo(HttpServletRequest request);




}
