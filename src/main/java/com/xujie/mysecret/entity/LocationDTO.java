package com.xujie.mysecret.entity;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xujie17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {

    /**
     *维度
     */
    private String latitude;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 位置描述
     */
    private String desc;

    /**
     * 速度
     */
    private String speed;

    public LocationDTO(String latitude, String longitude, String speed) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
    }
}
