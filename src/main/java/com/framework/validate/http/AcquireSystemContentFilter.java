package com.framework.validate.http;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述：添加功能描述.<br/>
 * 
 * #date： 2016年10月20日 下午4:41:28<br/>
 * #author 李旭<br/>
 * #since 1.0.0<br/>
 */
public class AcquireSystemContentFilter implements Filter{
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {
        SystemContent.setRequest((HttpServletRequest) arg0);
        SystemContent.setResponse((HttpServletResponse) arg1);
        arg2.doFilter(arg0, arg1);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
    }
}