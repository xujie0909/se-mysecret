package com.xujie.mysecret.web.bill;

import com.xujie.mysecret.common.Constant;
import com.xujie.mysecret.entity.bill.Tag;
import com.xujie.mysecret.entity.response.Response;
import com.xujie.mysecret.service.bill.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @CrossOrigin
    public Response saveBillTag(@RequestBody Tag tag) {
        this.tagService.saveTag(tag);
        Response response = new Response();
        response.setMessage("标签保存成功");
        response.setCode(Constant.SUCCESS);
        return response;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @CrossOrigin
    public Response list(Tag tag) {
        List<Tag> result = this.tagService.list(tag);
        Response response = new Response();
        response.setCode(Constant.SUCCESS);
        response.setMessage("查询成功");
        response.setData(result);
        return response;
    }

}
