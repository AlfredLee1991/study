package com.framework.dao.demo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import com.framework.dao.model.demo.User;

/**
 * 功能描述：用户持久化接口.<br/>
 * 
 * #author lixu<br/>
 */
public interface IUserDao{

    List<Map<String, Object>> queryUserList(int pageNum, int pageSize, String sort, String desc);

    @Insert("insert into users(firstname,lastname,phone,email) values(#{firstname},#{lastname},#{phone},#{email})")
    int insertUser(User user);

    @Update("update users set firstname=#{firstname},lastname=#{lastname},phone=#{phone},email=#{email} where id=#{id}")
    int updateUser(User user);
    
    @Delete("delete from users where id=#{id}")
    int deleteUser(int id);
}
