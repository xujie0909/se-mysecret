package com.xujie.mysecret.entity.message;

import com.xujie.mysecret.entity.Image;
import lombok.Data;

@Data
public class ImageMessage extends BaseMessage {
    private Image image;
}
