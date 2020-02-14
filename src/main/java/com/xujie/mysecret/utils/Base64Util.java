package com.xujie.mysecret.utils;

import java.util.Base64;

public class Base64Util {

    //加密
    public static String encode(String str){
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(str.getBytes());
    }

    //解密
    public static String decode(String str){
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(str.getBytes());
        return new String(decode);
    }

}
