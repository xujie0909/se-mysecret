package com.xujie.mysecret.web.mgmt;

import com.xujie.mysecret.common.Constant;
import com.xujie.mysecret.entity.Dictionary;
import com.xujie.mysecret.entity.response.Response;
import com.xujie.mysecret.service.impl.DictionaryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/dictionary")
@Slf4j
public class DictionaryController {

    private final DictionaryImpl dictionaryImpl;

    public DictionaryController(DictionaryImpl dictionary) {
        this.dictionaryImpl = dictionary;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @CrossOrigin
    public Response saveDictionary(@RequestBody Dictionary dictionary){
        log.info("需要保存的字典信息为:{}",dictionary);
        this.dictionaryImpl.save(dictionary);
        Response response = new Response();
        response.setCode(Constant.SUCCESS);
        response.setMessage("保存成功");
        return response;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @CrossOrigin
    public Response list(){
        List<Dictionary> all = this.dictionaryImpl.findAll();
        Response response = new Response();
        response.setCode(Constant.SUCCESS);
        response.setMessage("查询成功");
        response.setData(all);
        return response;
    }
}
