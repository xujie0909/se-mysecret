package com.xujie.mysecret.web.mgmt;

import com.sun.deploy.net.HttpResponse;
import com.xujie.mysecret.common.Constant;
import com.xujie.mysecret.entity.response.Response;
import com.xujie.mysecret.enums.BillTypeEnum;
import com.xujie.mysecret.excel.AliPayBllAnalyze;
import com.xujie.mysecret.service.impl.BillServiceImpl;
import com.xujie.mysecret.utils.CsvReader;
import com.xujie.mysecret.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("/mgmt")
@Slf4j
public class uploadController {

    private final BillServiceImpl billService;

    public uploadController(BillServiceImpl billService) {
        this.billService = billService;
    }

    // 处理文件上传
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @CrossOrigin
    public Response upload(MultipartFile file, int billType) throws IOException {
        Response response = new Response();
        String fileName = file.getOriginalFilename();
        if (StringUtils.isBlank(fileName)) {
            log.warn("文件名称为:{}", fileName);
            response.setCode(HttpServletResponse.SC_BAD_REQUEST);
            response.setMessage("入参不合法，文件名为空！");
            return response;
        }
        String fileType = fileName.split("\\.")[1];
        if (!Constant.CSV.equals(fileType)) {
            response.setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setMessage("不支持的文件格式，请上传csv文件");
            return response;
        }

        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            log.error("获取文件流错误!", e);
        }

        if(BillTypeEnum.ALIPAY.getType() == billType){
            billService.dealAlipayBill(inputStream);
        }
        if(BillTypeEnum.WEICHAT.getType() == billType){
            billService.dealWechatBill(inputStream);
        }
        response.setCode(Constant.SUCCESS);
        response.setMessage("数据录入成功!");
        return response;
    }
}