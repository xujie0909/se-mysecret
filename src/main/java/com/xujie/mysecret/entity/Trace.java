package com.xujie.mysecret.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "t_trace")
@ToString
public class Trace implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date time;

    private String latitude;

    private String longitude;

    private String locationDesc;

    private String speed;

    private String action;

    private String description;




}
