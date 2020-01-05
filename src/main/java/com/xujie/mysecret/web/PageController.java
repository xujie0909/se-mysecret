package com.xujie.mysecret.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/page/index")
    public String testPage(){
        return "index";
    }
}
