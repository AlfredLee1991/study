package com.framework.validate.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能描述：用户登录控制和用户对接口访问权限控制.<br/>
 * 
 * #date： 2016年10月9日 下午3:04:47<br/>
 * #author 8104485-李旭<br/>
 * #since 1.0.0<br/>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthorityFilter {

    /**
     * 方法描述：用户角色 <br/>
     *
     * #author 8104485-李旭<br/>
     * #date 2016年10月9日 下午3:10:15<br/>
     * #since 1.0.0<br/>
     * @return
     */
    UserRole[] roles() default {};
    
    /**
     * 方法描述：是否需要校验登录<br/>
     *
     * #author 8104485-李旭<br/>
     * #date 2016年10月9日 下午3:14:24<br/>
     * #since 1.0.0<br/>
     * @return
     */
    boolean loginCheck() default false;
}
