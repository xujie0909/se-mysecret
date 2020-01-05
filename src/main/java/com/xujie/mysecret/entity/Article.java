package com.xujie.mysecret.entity;

import lombok.Data;

@Data
public class Article {
    //图文消息名称
    private String Title;
    //图文消息描述
    private String Description;
    //图片链接，支持JPG、PNG格式,较好的效果为大图640像素*320像素,小图80像素*80像素
    private String PicUrl;
    //点击图文消息跳转链接
    private String Url;
}
