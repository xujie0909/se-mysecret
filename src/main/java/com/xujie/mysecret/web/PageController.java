package com.xujie.mysecret.web;

import com.xujie.mysecret.service.impl.WeChatServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import static com.xujie.mysecret.common.Constant.INDEX;

/**
 * @author xujie17
 */
@Controller
@Slf4j
public class PageController {

    private final WeChatServiceImpl weChatService;

    public PageController(WeChatServiceImpl weChatService) {
        this.weChatService = weChatService;
    }

    @RequestMapping("/page/{pageName}")
    public ModelAndView testPage(@PathVariable String pageName, HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();

        log.info("请求IP来源:{}",getRequestIP());

        //获取微信鉴权信息
        HashMap<String, String> weChatConfig = weChatService.getSignature(request.getRequestURI());
        log.info("get weChatConfig is:{}", weChatConfig);

        if (INDEX.equals(pageName)) {
            mv.addAllObjects(weChatConfig);
            mv.setViewName(INDEX);
        }

        return mv;
    }

    public static String getRequestIP(){
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=requestAttributes.getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    @RequestMapping("test")
    @ResponseBody
    public String test(){
        log.info("请求成功...");
        return "success";
    }
}
