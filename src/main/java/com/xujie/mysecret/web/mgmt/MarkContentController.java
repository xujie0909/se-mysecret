package com.xujie.mysecret.web.mgmt;

import com.xujie.mysecret.entity.mark.MarkContent;
import com.xujie.mysecret.service.MarkContentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xujie17
 */
@RestController
@RequestMapping(value = "/mc/")
public class MarkContentController {

    private final MarkContentService markContentService;

    public MarkContentController(MarkContentService markContentService) {
        this.markContentService = markContentService;
    }

    @RequestMapping(value = "/save")
    public void save(){
        MarkContent markContent = new MarkContent();
        markContent.setId(1L);
        markContent.setMcName("wake");
        markContent.setMcDesc("中文描述");
        markContentService.saveOrUpdate(markContent);
    }
}
