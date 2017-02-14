package com.framework.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.common.MediaTypes;
import com.framework.common.req.ReqParam;
import com.framework.common.req.ReqQueryTemplate;
import com.framework.validate.annotation.AuthorityFilter;
import com.framework.validate.annotation.UserRole;

/**
 * 功能描述：测试.<br/>
 * 
 * #date： 2016年10月10日 上午10:20:31<br/>
 * #author 8104485-李旭<br/>
 * #since 1.0.0<br/>
 */
@Controller
public class AspectController{

    @RequestMapping(value = "test", method = RequestMethod.POST, consumes = MediaTypes.JSON)
    @AuthorityFilter(loginCheck = true, roles = UserRole.Patient)
    @ResponseBody
    public void test(HttpServletRequest request, HttpServletResponse response,
            @RequestBody ReqParam<ReqQueryTemplate> res) {
        System.out.println("coming");
    }

}
