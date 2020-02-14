package com.xujie.mysecret.web.system;

import com.xujie.mysecret.common.Constant;
import com.xujie.mysecret.entity.Dictionary;
import com.xujie.mysecret.entity.response.Response;
import com.xujie.mysecret.service.DictionaryService;
import com.xujie.mysecret.service.impl.DictionaryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/dictionary")
@Slf4j
public class DictionaryController {

    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryServiceImpl dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @CrossOrigin
    public Response saveDictionary(@RequestBody Dictionary dictionary) {
        log.info("需要更新的字典信息为:{}", dictionary);
        Dictionary editResult;
        if (dictionary.getId() == null) {
            //新增
            editResult = this.dictionaryService.save(dictionary);
        } else {
            //编辑
            editResult = this.dictionaryService.update(dictionary);
        }
        Response response = new Response();
        if (editResult == null) {
            response.setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setMessage("操作失败");
            return response;
        }
        response.setCode(Constant.SUCCESS);
        response.setMessage("操作成功");
        return response;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @CrossOrigin
    public Response list() {
        List<Dictionary> all = this.dictionaryService.findAll();
        Response response = new Response();
        response.setCode(Constant.SUCCESS);
        response.setMessage("查询成功");
        response.setData(all);
        return response;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @CrossOrigin
    public Response delete(@RequestBody Dictionary dictionary){
        log.info("需要删除的实体为:{}",dictionary);
        this.dictionaryService.delete(dictionary.getId());
        Response response = new Response();
        response.setCode(Constant.SUCCESS);
        response.setMessage("删除成功");
        return response;
    }

    @RequestMapping(value = "/dicTypes",method = RequestMethod.GET)
    @CrossOrigin
    public Response getDicTypes(){
        List<String> dicTypes = this.dictionaryService.getDicTypes();
        Response response = new Response();
        response.setCode(Constant.SUCCESS);
        response.setData(dicTypes);
        response.setMessage("查询成功！");
        return response;
    }
}
