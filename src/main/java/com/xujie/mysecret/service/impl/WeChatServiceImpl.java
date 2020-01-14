package com.xujie.mysecret.service.impl;

import com.sun.deploy.net.HttpResponse;
import com.xujie.mysecret.cache.CacheContent;
import com.xujie.mysecret.dao.TraceDao;
import com.xujie.mysecret.entity.LocationDTO;
import com.xujie.mysecret.entity.Trace;
import com.xujie.mysecret.entity.WeixinMessageInfo;
import com.xujie.mysecret.entity.WxResponse;
import com.xujie.mysecret.entity.message.TextMessage;
import com.xujie.mysecret.service.WeChatService;
import com.xujie.mysecret.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.xujie.mysecret.common.Constant.*;

/**
 * @author xujie17
 */
@Slf4j
@Service
public class WeChatServiceImpl implements WeChatService {

    private static final String PREFIX = "weichat_";

    private final CacheContent cacheContent;

    private final TraceDao traceDao;

    public WeChatServiceImpl(CacheContent cacheContent, TraceDao traceDao) {
        this.cacheContent = cacheContent;
        this.traceDao = traceDao;
    }


    @Override
    public String weChatHandle(HttpServletRequest request, HttpServletResponse response) {

        WeixinMessageInfo weixinMessageInfo = new WeixinMessageInfo();

        log.info("------------微信消息开始处理-------------");
        // 返回给微信服务器的消息,默认为null

        String respMessage = null;

        try {

            // 默认返回的文本消息内容
            String respContent = null;
            // xml分析
            // 调用消息工具类MessageUtil解析微信发来的xml格式的消息，解析的结果放在HashMap里；
            Map<String, String> map = WeChatMessageUtil.parseXml(request);
            // 发送方账号
            String fromUserName = map.get("FromUserName");
            weixinMessageInfo.setFromUserName(fromUserName);
            System.out.println("fromUserName--->" + fromUserName);
            // 接受方账号（公众号）
            String toUserName = map.get("ToUserName");
            weixinMessageInfo.setToUserName(toUserName);
            System.out.println("toUserName----->" + toUserName);
            // 消息类型
            String msgType = map.get("MsgType");
            weixinMessageInfo.setMessageType(msgType);
            log.info("fromUserName is:" + fromUserName + " toUserName is:" + toUserName + " msgType is:" + msgType);

            // 默认回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(System.currentTimeMillis());
            textMessage.setMsgType(WeChatMessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);

            // 分析用户发送的消息类型，并作出相应的处理

            // 文本消息
            if (msgType.equals(WeChatMessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                respContent = "亲，这是文本消息！" + TESTIP + "/page/index";
                textMessage.setContent(respContent);
                respMessage = WeChatMessageUtil.textMessageToXml(textMessage);
            }

            // 图片消息
            else if (msgType.equals(WeChatMessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "您发送的是图片消息！";
                textMessage.setContent(respContent);
                respMessage = WeChatMessageUtil.textMessageToXml(textMessage);
            }

            // 语音消息
            else if (msgType.equals(WeChatMessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是语音消息！";
                textMessage.setContent(respContent);
                respMessage = WeChatMessageUtil.textMessageToXml(textMessage);
            }

            // 视频消息
            else if (msgType.equals(WeChatMessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
                respContent = "您发送的是视频消息！";
                textMessage.setContent(respContent);
                respMessage = WeChatMessageUtil.textMessageToXml(textMessage);
            }

            // 地理位置消息
            else if (msgType.equals(WeChatMessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
                textMessage.setContent(respContent);
                respMessage = WeChatMessageUtil.textMessageToXml(textMessage);
            }

            // 链接消息
            else if (msgType.equals(WeChatMessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
                textMessage.setContent(respContent);
                respMessage = WeChatMessageUtil.textMessageToXml(textMessage);
            }

            // 事件推送(当用户主动点击菜单，或者扫面二维码等事件)
            else if (msgType.equals(WeChatMessageUtil.REQ_MESSAGE_TYPE_EVENT)) {

                // 事件类型
                String eventType = map.get("Event");
                System.out.println("eventType------>" + eventType);
                // 关注
                if (eventType.equals(WeChatMessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respMessage = WeixinMessageModelUtil.followResponseMessageModel(weixinMessageInfo);
                }
                // 取消关注
                else if (eventType.equals(WeChatMessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    WeixinMessageModelUtil.cancelAttention(fromUserName);
                }
                // 扫描带参数二维码
                else if (eventType.equals(WeChatMessageUtil.EVENT_TYPE_SCAN)) {
                    System.out.println("扫描带参数二维码");
                }
                // 上报地理位置
                else if (eventType.equals(WeChatMessageUtil.EVENT_TYPE_LOCATION)) {
                    System.out.println("上报地理位置");
                }
                // 自定义菜单（点击菜单拉取消息）
                else if (eventType.equals(WeChatMessageUtil.EVENT_TYPE_CLICK)) {

                    // 事件KEY值，与创建自定义菜单时指定的KEY值对应
                    String eventKey = map.get("EventKey");
                    System.out.println("eventKey------->" + eventKey);

                }
                // 自定义菜单（(自定义菜单URl视图)）
                else if (eventType.equals(WeChatMessageUtil.EVENT_TYPE_VIEW)) {
                    System.out.println("处理自定义菜单URI视图");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("系统出错");
            System.err.println("系统出错");
            respMessage = null;
        } finally {
            if (null == respMessage) {
                respMessage = WeixinMessageModelUtil.systemErrorResponseMessageModel(weixinMessageInfo);
            }
        }

        return respMessage;

    }

    @Override
    public HashMap<String, String> getSignature(String url) {

        HashMap<String, String> resultMap = new HashMap<>();
        resultMap.put(APPIDNAME, APPIDVALUE);

        String ticket = null;
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = formater.format(new Date());

        try {
            ticket = cacheContent.get(PREFIX + TICKET);
        } catch (Exception e) {
            log.error("get accessToken from cache error!", e);
        }

        log.info("获取的ticket值为:{}", ticket);

        //随机字符串
        String randomString = StringUtil.getRandomString(8);
        resultMap.put(NONCESTR, randomString);

        //时间戳
        long timestamp = System.currentTimeMillis();
        resultMap.put(TIMESTAMP, String.valueOf(timestamp));

        String str = JSAPITICKET + "=" + ticket + "&" + NONCESTR + "=" + randomString + "&"
                + TIMESTAMP + "=" + timestamp + "&" + URL + "=" + TESTIP + "/page/index";

        resultMap.put(SIGNATURE, SHA1.encode(str));

        return resultMap;
    }

    public LocationDTO getLocationDes(LocationDTO locationDTO) {
        //重试两次
        int count = 0;
        while (count < LOCATIONMAXTIME) {
            LocationUtil.getLocationInfo(locationDTO);
            if (StringUtils.isNotBlank(locationDTO.getDesc())) {
                break;
            }
            count++;
        }
        return locationDTO;
    }

    @Override
    public WxResponse saveMarkInfo(HttpServletRequest request) {

        WxResponse wxResponse = new WxResponse();

        /*
         * 微信返回的位置报文
         *{
         * 	"indoor_building_id": [""],
         * 	"latitude": ["40.073566"],
         * 	"accuracy": ["30.0"],
         * 	"indoor_building_floor": ["1000"],
         * 	"indoor_building_type": ["-1"],
         * 	"speed": ["0.0"],
         * 	"longitude": ["116.35183"],
         * 	"errMsg": ["getLocation:ok"]
         * }
         */

        //经度
        String longitude = request.getParameter(LONGITUDE);
        //维度
        String latitude = request.getParameter(LATITUDE);
        //速度
        String speed = request.getParameter(SPEED);
        //标记类型
        String actionType = request.getParameter(ACTIONTYPE);

        if (StringUtils.isBlank(longitude) || StringUtils.isBlank(latitude)) {
            wxResponse.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
            wxResponse.setStatusDes("未获取到经度和维度！");
            return wxResponse;
        }

        LocationDTO locationDTO = getLocationDes(new LocationDTO(latitude, latitude, speed));

        Trace trace = new Trace();
        trace.setLatitude(locationDTO.getLatitude());
        trace.setLongitude(locationDTO.getLongitude());
        trace.setAction(actionType);
        trace.setSpeed(speed);
        log.info("需要存储的东西:{}",trace);
        Trace saveTrace = traceDao.save(trace);
        log.info("存储成功!{}",saveTrace);

        //WxResponse wxResponse = new WxResponse();
        wxResponse.setStatusCode(HttpServletResponse.SC_OK);
        wxResponse.setStatusDes("存储成功!");
        wxResponse.setResResult(locationDTO.getDesc());

        return wxResponse;
    }
}
