package com.xujie.mysecret.common;

/**
 * @author xujie17
 */
public class Constant {

    public static final String TESTIP = "http://pbkabr.natappfree.cc";
    /**
     * 地图服务最大重试次数
     */
    public static final Integer LOCATIONMAXTIME = 2;


    public static final String APPIDVALUE = "wx1ac3362f8a49e7cc";
    public static final String APPIDNAME = "appid";
    public static final String APPSECRET = "373e5e95e6faaebd824949855b96703a";
    public static final String GRANTTYPE = "client_credential";

    /**
     * 微信h5SDK获取token地址
     */
    public static final String TOKENURL = "https://api.weixin.qq.com/cgi-bin/token";
    /**
     * 微信h5SDK获取ticket地址
     */
    public static final String TICKETURL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";

    /**
     * 经纬度地址解析地址
     * 两个均有次数限制
     *   http://www.cellocation.com/api/
     *   https://www.free-api.com/doc/120
     */
    public static final String ANALYSISLOCATIONIP = "http://api.cellocation.com:81/regeo/";

    public static final String ACCESSTOKEN = "access_token";
    public static final String TICKET = "ticket";
    public static final String JSAPITICKET = "jsapi_ticket";
    public static final String NONCESTR = "noncestr";
    public static final String TIMESTAMP = "timestamp";
    public static final String URL = "url";
    public static final String SIGNATURE = "signature";

    public static final String INDEX = "index";

    /**
     * 报文属性
     */
    public static final String ADDRESS = "address";
    public static final String ERRCODE = "errcode";
    public static final String LAT = "lat";
    public static final String LON = "lon";
    public static final String OUTPUT = "output";

    /**
     * 其他常量
     */
    public static final int ZERO = 0;
    public static final String JSONTYPE = "json";


}
