package icu.hilin.core.entity.vo;

import lombok.Data;

@Data
public class UserVO extends BaseVO {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户类型
     */
    private String userType;
    /**
     * 用户类型名称
     */
    private String userTypeName;
    /**
     * 用户手机号
     */
    private String userMobile;


}
