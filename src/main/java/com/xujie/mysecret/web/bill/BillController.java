package com.xujie.mysecret.web.bill;

import com.sun.deploy.net.HttpResponse;
import com.xujie.mysecret.cache.DictionaryCache;
import com.xujie.mysecret.common.Constant;
import com.xujie.mysecret.entity.Dictionary;
import com.xujie.mysecret.entity.bill.Bill;
import com.xujie.mysecret.entity.bill.Tag;
import com.xujie.mysecret.entity.response.Response;
import com.xujie.mysecret.enums.BillTypeEnum;
import com.xujie.mysecret.service.bill.BillService;
import com.xujie.mysecret.service.bill.TagService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.xujie.mysecret.common.Constant.STARTROWNUM;
import static com.xujie.mysecret.common.Constant.TEMPLATE;

@RestController
@RequestMapping("/bill")
@Slf4j
public class BillController {

    private final BillService billService;

    private final DictionaryCache dictionaryCache;

    private final TagService tagService;

    public BillController(BillService billService, TagService tagService, DictionaryCache dictionaryCache) {
        this.billService = billService;
        this.tagService = tagService;
        this.dictionaryCache = dictionaryCache;
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

    @RequestMapping(value = "/patch", method = RequestMethod.GET)
    @CrossOrigin
    public Response patch() {


        Response response = new Response();
        return response;
    }

    public Response upload1(MultipartFile file, int billType) {

        Response response = new Response();
        String fileName = file.getOriginalFilename();
        boolean fileNameOk = fileNameCheck(fileName);
        if (!fileNameOk) {
            response.setCode(HttpServletResponse.SC_BAD_REQUEST);
            response.setMessage("文件名称校验错误！请检查文件！");
            return response;
        }

        HashMap<String, Object> serviceParam = new HashMap<>();

        String startRowKeyOfDict = "";
        String templateKeyOfDict = "";

        if (BillTypeEnum.ALIPAY.getType() == billType) {
            startRowKeyOfDict = "alipayBillStartRow";
            templateKeyOfDict = "";
        }
        if (BillTypeEnum.WEICHAT.getType() == billType) {
            startRowKeyOfDict = "weChatBillStartRow";
            templateKeyOfDict = "wechatBillTemplate";
        }

        Dictionary startRowDic = dictionaryCache.getDictByTypeAndName("systemConfig", startRowKeyOfDict);
        serviceParam.put(STARTROWNUM, Integer.parseInt(startRowDic.getDicValue()));

        List<Dictionary> templateDicts = dictionaryCache.getDictsByType(templateKeyOfDict);
        Map<String, String> fieldsMapping = templateDicts.stream().collect(Collectors.toMap(Dictionary::getDicName, Dictionary::getDicValue));
        serviceParam.put(TEMPLATE,fieldsMapping);



        return null;
    }

    // 处理文件上传
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @CrossOrigin
    public Response upload(MultipartFile file, int billType) {
        Response response = new Response();
        String fileName = file.getOriginalFilename();
        boolean fileNameOk = fileNameCheck(fileName);
        if (!fileNameOk) {
            response.setCode(HttpServletResponse.SC_BAD_REQUEST);
            response.setMessage("文件名称校验错误！请检查文件！");
            return response;
        }

        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            log.error("获取文件流错误!", e);
        }

        HashMap<String, Object> serviceParam = new HashMap<>();
        serviceParam.put("stream", inputStream);
        serviceParam.put("billType", billType);


        if (BillTypeEnum.ALIPAY.getType() == billType) {
            billService.dealAlipayBill(inputStream);
        }
        if (BillTypeEnum.WEICHAT.getType() == billType) {
            billService.dealWechatBill(inputStream);
        }
        response.setCode(Constant.SUCCESS);
        response.setMessage("数据录入成功!");
        return response;
    }

    public boolean fileNameCheck(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            log.error("文件名为空！fileName:{}", fileName);
            return false;
        }
        String fileType = fileName.split("\\.")[1];
        if (!Constant.CSV.equals(fileType)) {
            log.error("不支持的文件格式！fileName:{}", fileName);
            return false;
        }
        return true;
    }
}

