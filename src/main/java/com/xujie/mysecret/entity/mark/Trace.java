package com.xujie.mysecret.entity.mark;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@ToString
public class Trace implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Date time;

    private String latitude;

    private String longitude;

    private String locationDesc;

    private String speed;

    private String action;

    private String description;




}
