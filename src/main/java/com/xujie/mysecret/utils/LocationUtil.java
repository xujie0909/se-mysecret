package com.xujie.mysecret.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xujie.mysecret.entity.mark.LocationDTO;

import java.util.HashMap;

import static com.xujie.mysecret.common.Constant.*;

/**
 * @author xujie17
 */
public class LocationUtil {

    public static void getLocationInfo(LocationDTO location) {

        String result = HttpUtil.doGet(ANALYSISLOCATIONIP, new HashMap<String, String>(3) {{
            put(LAT, location.getLatitude());
            put(LON, location.getLongitude());
            put(OUTPUT, JSONTYPE);
        }});

        JSONObject resultLocation = JSON.parseObject(result);
        Integer errcode = (Integer) resultLocation.get(ERRCODE);
        String address = (String) resultLocation.get(ADDRESS);
        if (ZERO == errcode) {
            location.setDesc(address);
        }
    }
}
