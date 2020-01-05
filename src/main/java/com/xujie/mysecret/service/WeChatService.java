package com.xujie.mysecret.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface WeChatService {

    String weChatHandle(HttpServletRequest request, HttpServletResponse response);
}
