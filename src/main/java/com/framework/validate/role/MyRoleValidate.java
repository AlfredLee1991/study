package com.framework.validate.role;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Repository;

import com.framework.validate.annotation.UserRole;
import com.framework.validate.result.ValidateResult;

/**
 * 功能描述：添加功能描述.<br/>
 * 
 * #date： 2016年10月11日 上午8:49:50<br/>
 * #author 李旭<br/>
 * #since 1.0.0<br/>
 */
@Repository
public class MyRoleValidate implements RoleValidateBeanFactory{

    @Override
    public ValidateResult roleValidate(JoinPoint point, UserRole[] roles) {
        return new ValidateResult("roleValidate", "success", "login success", null);
    }

}
