package com.poke.miaosha.domain;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName MiaoshaoUser
 * @Description //TODO
 * @Author poke
 * @Date 2020/3/1 1:13 上午
 */
@Data
public class MiaoshaUser {
    private Long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;
}
