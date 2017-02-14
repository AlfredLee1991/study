package com.framework.validate.role;

import org.aspectj.lang.JoinPoint;

import com.framework.validate.annotation.UserRole;
import com.framework.validate.result.ValidateResult;

/**
 * 功能描述：接口角色访问权限校验.<br/>
 * 
 * #date： 2016年10月10日 下午7:04:45<br/>
 * #author 李旭<br/>
 * #since 1.0.0<br/>
 */
public interface RoleValidateBeanFactory{

    /**
     * 方法描述：接口角色访问权限校验 <br/>
     *
     * #author 李旭<br/>
     * #date 2016年10月10日 下午7:08:55<br/>
     * #since 1.0.0<br/>
     * @param point
     * @param roles
     * @return
     */
    ValidateResult roleValidate(JoinPoint point,UserRole[] roles);
}
