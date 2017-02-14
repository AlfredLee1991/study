package com.framework.validate.login;

/**
 * 功能描述：登录结果.<br/>
 * 
 * #date： 2016年10月10日 下午3:48:27<br/>
 * #author 李旭<br/>
 * #since 1.0.0<br/>
 */
public enum LoginEnum {

    /**
     * 已登录
     */
    islogin,

    /**
     * 未登录
     */
    unlogin,

    /**
     * 登录过期
     */
    login_expired,

    /**
     * 登录被锁定
     */
    login_lock;
}
