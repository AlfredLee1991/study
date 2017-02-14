package com.framework.service.demo;

import java.util.List;

import com.framework.dao.model.demo.Visitor;

/**
 * 功能描述：访客业务接口.<br/>
 * 
 * #author lixu<br/>
 */
public interface IVisitorService{

    List<Visitor> getVisitorByState(int state);
    
    List<Visitor> getAllVisitor();
    
    void addVisitor();
}
