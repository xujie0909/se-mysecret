package com.xujie.mysecret.common;

/**
 * @author xujie17
 */
public class Constant {

    /**
     * 地图服务最大重试次数
     */
    public static final Integer LOCATIONMAXTIME = 2;

    public static final String APPID = "appid";
    public static final String SECRET = "secret";
    public static final String GRANTTYPE = "grant_type";

    /**
     * 微信h5SDK获取token地址
     */
    public static final String TOKENURL = "https://api.weixin.qq.com/cgi-bin/token";

    /**
     * 经纬度地址解析地址
     * 两个均有次数限制
     *   http://www.cellocation.com/api/
     *   https://www.free-api.com/doc/120
     */

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
    public static final String LONGITUDE = "longitude";
    public static final String LATITUDE = "latitude";
    public static final String SPEED = "speed";
    public static final String LON = "lon";
    public static final String OUTPUT = "output";
    public static final String ACTIONTYPE = "actiontype";

    /**
     * 配置文件路径
     */
    public static final String PROPPATH = "/app.properties";

    public static final int ZERO = 0;
    public static final String JSONTYPE = "json";
    public static final String PREFIX = "weichat_";



}
