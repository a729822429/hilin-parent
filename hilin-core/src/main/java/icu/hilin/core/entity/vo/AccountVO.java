package icu.hilin.core.entity.vo;

import lombok.Data;

import java.util.Map;

@Data
public class AccountVO extends BaseVO {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 如果是账号密码登录: 这就是账号
     * 如果是短信验证码登录：这就是手机号
     * 如果是微信小程序登录：这个是openid
     */
    private String account;

    /**
     * 账号类型
     */
    private String accountType;

    /**
     * 账号类型描述
     */
    private String accountTypeName;
    /**
     * 账号参数
     * 如果是账号密码登录: 包含salt、password字段
     * 如果是短信验证码登录：包含code(验证码)
     * 如果是微信小程序登录：包含code
     */
    private Map<String, String> params;

}
