package com.xujie.mysecret.web.wechat;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

@Controller
@RequestMapping(value = "/wc")
@Slf4j
public class WcController {

    @RequestMapping(value = "/location",method = RequestMethod.GET)
    @ResponseBody
    public void getLocation(HttpServletRequest request) throws UnsupportedEncodingException {
        //{"indoor_building_id":[""],
        // "latitude":["40.073566"],
        // "accuracy":["30.0"],
        // "indoor_building_floor":["1000"],
        // "indoor_building_type":["-1"],
        // "speed":["0.0"],
        // "longitude":["116.35183"],
        // "errMsg":["getLocation:ok"]}
        log.info("get location msg is:{}",JSON.toJSONString(request.getParameterMap()));
        //经度
        String longitude = request.getParameter("longitude");
        //维度
        String latitude = request.getParameter("latitude");


    }
}
