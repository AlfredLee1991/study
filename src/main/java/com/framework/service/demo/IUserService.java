package com.framework.service.demo;

import java.util.Map;

import com.framework.dao.model.demo.User;

/**
 * 功能描述：用户接口定义.<br/>
 * 
 * #author lixu<br/>
 */
public interface IUserService{

    Map<String, Object> listUser(int pageNum, int pageSize, String sort, String order);

    int addUser(User user);

    int modifyUser(User user);
    
    int removeUser(int id);
}
