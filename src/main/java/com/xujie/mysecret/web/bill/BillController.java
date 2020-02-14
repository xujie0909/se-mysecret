package com.xujie.mysecret.web.bill;

import com.xujie.mysecret.common.Constant;
import com.xujie.mysecret.entity.bill.Bill;
import com.xujie.mysecret.entity.bill.Tag;
import com.xujie.mysecret.entity.response.Response;
import com.xujie.mysecret.service.bill.BillService;
import com.xujie.mysecret.service.bill.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/bill")
@Slf4j
public class BillController {

    private final BillService billService;

    private final TagService tagService;

    public BillController(BillService billService, TagService tagService) {
        this.billService = billService;
        this.tagService = tagService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @CrossOrigin
    public Response list(Bill bill) {
        List<Bill> result = billService.list(bill);
        Response response = new Response();
        response.setCode(Constant.SUCCESS);
        response.setMessage("查询成功");
        response.setData(result);
        return response;
    }

    @RequestMapping(value = "/chart", method = RequestMethod.GET)
    @CrossOrigin
    public Response chart(String charType) {
        HashMap<String, Object> resultMap = this.billService.sourceProportion();
        Response response = new Response();
        response.setCode(Constant.SUCCESS);
        response.setMessage("统计成功");
        response.setData(resultMap);
        return response;
    }
}

