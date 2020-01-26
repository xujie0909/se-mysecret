package com.xujie.mysecret.entity.permission;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;

@Data
@Entity
@ToString
@Table(uniqueConstraints = @UniqueConstraint(name = "index_username",columnNames = {"username"}))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String name;

    /**
     * 登录状态
     */
    @Transient
    private int loginStatus = 1;

    /**
     * 登录token
     */
    @Transient
    private String token;

    /**
     * 用户介绍
     */
    @Transient
    private String introduction;

    @Transient
    private ArrayList<String> roles;

    public enum loginStatus{
        SUCCESS(0,"登陆成功"),
        FAILURE(1,"登陆失败")
        ;
        private int lsCode;
        private String lsDesc;

        loginStatus(int lsCode, String lsDesc) {
            this.lsCode = lsCode;
            this.lsDesc = lsDesc;
        }

        public int getLsCode() {
            return lsCode;
        }

        public String getLsDesc() {
            return lsDesc;
        }
    }

}
