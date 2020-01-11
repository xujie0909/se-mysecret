package com.xujie.mysecret.web;

import com.xujie.mysecret.common.Constant;
import com.xujie.mysecret.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

import static com.xujie.mysecret.common.Constant.*;

/**
 * @author xujie17
 */
@Controller
@Slf4j
public class PageController {

    private static final String TOKENRESULT = "tokenResult";

    private static final String INDEX = "index";


    @RequestMapping("/page/{pageName}")
    public ModelAndView testPage(@PathVariable String pageName) {

        ModelAndView mv = new ModelAndView();



        if (INDEX.equals(pageName)) {
            mv.setViewName(INDEX);
        }





        mv.addObject(TOKENRESULT, tokenResult);
        mv.setViewName("index");
        return mv;
    }
}
