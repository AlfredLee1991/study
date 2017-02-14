package com.framework.validate.login;

import org.aspectj.lang.JoinPoint;

import com.framework.validate.result.ValidateResult;

/**
 * 功能描述：登录校验.<br/>
 * 
 * #date： 2016年10月10日 下午3:11:45<br/>
 * #author 8104485-李旭<br/>
 * #since 1.0.0<br/>
 */
public interface LoginValidateBeanFactory{

    /**
     * 方法描述：登录校验 <br/>
     *
     * #author 8104485-李旭<br/>
     * #date 2016年10月10日 下午3:23:11<br/>
     * #since 1.0.0<br/>
     * @param point
     * @return
     */
    ValidateResult loginValidate(JoinPoint point);
}
