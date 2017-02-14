package com.framework.dao.demo;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.framework.dao.model.demo.Visitor;

/**
 * 功能描述：访客持久化接口.<br/>
 * 
 * #author lixu<br/>
 */
public interface IVisitorDao{

    List<Visitor> queryVisitorByState(int state);
    
    @Select("select * from Visitor")
    List<Visitor> queryAllVisitor();
    
    void insertVisitor(Visitor entity);
}
