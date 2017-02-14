package com.framework.validate.annotation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.framework.validate.http.SystemContent;
import com.framework.validate.login.LoginValidateBeanFactory;
import com.framework.validate.result.ValidateResult;
import com.framework.validate.role.RoleValidateBeanFactory;
import com.google.gson.Gson;

/**
 * 功能描述：接口角色校验和登录校验切面定义.<br/>
 * 
 * #date： 2016年10月9日 下午3:51:28<br/>
 * #author 8104485-李旭<br/>
 * #since 1.0.0<br/>
 */
@Aspect
@Component
public class AuthorityAspect{

    private static final Logger logger = LoggerFactory.getLogger(AuthorityAspect.class);

    @Autowired
    private LoginValidateBeanFactory loginValidate;

    @Autowired
    private RoleValidateBeanFactory roleValidate;

    @Before(value = "@annotation(af)")
    public HttpServletResponse doBefore(JoinPoint point, AuthorityFilter af) {
        HttpServletRequest request = SystemContent.getRequest();
        HttpServletResponse response=SystemContent.getResponse();
        // 请求的IP
        String ip = request.getRemoteAddr();
        logger.info("当前用户ip：{}", ip);

        List<ValidateResult> result = new ArrayList<ValidateResult>();
        // 是否需要校验登录
        boolean loginCheck = af.loginCheck();
        // 如果需要校验登录，则执行以下校验逻辑
        if (loginCheck) {
            ValidateResult loginValidateResult = loginValidate.loginValidate(point);
            result.add(loginValidateResult);
        }

        // 当前接口允许访问者的角色
        UserRole[] roles = af.roles();
        ValidateResult roleValidateResult = roleValidate.roleValidate(point, roles);
        result.add(roleValidateResult);
        Gson gson = new Gson();
        try {
            response.getWriter().write(gson.toJson(result));
        } catch (IOException e) {
            logger.error("request error", e);
        }
        return response;
    }
}
