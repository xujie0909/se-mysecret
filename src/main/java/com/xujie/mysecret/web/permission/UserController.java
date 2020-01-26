package com.xujie.mysecret.web.permission;

import com.alibaba.fastjson.JSONObject;
import com.xujie.mysecret.common.Constant;
import com.xujie.mysecret.entity.permission.User;
import com.xujie.mysecret.entity.response.Response;
import com.xujie.mysecret.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/permission")
@Slf4j
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Response save(User user) {
        User saveResultUser = userService.save(user);
        Response response = new Response();
        response.setCode(HttpServletResponse.SC_OK);
        response.setMessage("存储成功！");
        response.setData(saveResultUser);
        return response;
    }

    @RequestMapping(value = "userToken", method = RequestMethod.POST)
    @CrossOrigin
    public Response userToken(@RequestBody  User user) {
        log.info("获取请求为:{}",user);
        String token = userService.userToken(user);
        Response res = new Response();

        if (token != null) {
            res.setData(token);
            res.setCode(Constant.SUCCESS);
            res.setMessage("登陆成功！");
            return res;
        }

        res.setCode(HttpServletResponse.SC_FORBIDDEN);
        res.setMessage("登录失败");
        return res;
    }

    @RequestMapping(value = "userInfo",method = RequestMethod.GET)
    @CrossOrigin
    public Response userInfo(String token){
        Response response = new Response();
        response.setCode(Constant.SUCCESS);
        String userInfo = userService.userInfo(token);
        response.setCode(Constant.SUCCESS);
        response.setMessage("请求用户信息成功！");
        response.setData(JSONObject.parseObject(userInfo));
        return response;
    }
}
