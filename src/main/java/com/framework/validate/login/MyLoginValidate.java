package com.framework.validate.login;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Repository;

import com.framework.validate.result.ValidateResult;

/**
 * 功能描述：添加功能描述.<br/>
 * 
 * #date： 2016年10月11日 上午8:44:14<br/>
 * #author 李旭<br/>
 * #since 1.0.0<br/>
 */
@Repository
public class MyLoginValidate implements LoginValidateBeanFactory{

    @Override
    public ValidateResult loginValidate(JoinPoint point) {
        return new ValidateResult("loginValidate", "success", "login success", null);
    }

}
