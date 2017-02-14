package com.framework.service.impl.demo;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.dao.demo.IVisitorDao;
import com.framework.dao.model.demo.Visitor;
import com.framework.service.demo.IVisitorService;

/**
 * 功能描述：访客业务接口实现.<br/>
 * 
 * #author lixu<br/>
 */
@Service
public class VisitorServiceImpl implements IVisitorService{

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public List<Visitor> getVisitorByState(int state) {
        SqlSession session = sqlSessionFactory.openSession();
        IVisitorDao dao = session.getMapper(IVisitorDao.class);
        List<Visitor> list = dao.queryVisitorByState(state);
        return list;
    }

    @Override
    public List<Visitor> getAllVisitor() {
        SqlSession session = sqlSessionFactory.openSession();
        IVisitorDao dao = session.getMapper(IVisitorDao.class);
        return dao.queryAllVisitor();
    }

    @Override
    public void addVisitor() {
        SqlSession session = sqlSessionFactory.openSession();
        IVisitorDao dao = session.getMapper(IVisitorDao.class);
        Visitor entity = new Visitor();
        entity.setName("add");
        entity.setEmail("add@gmail.com");
        entity.setStatus(2);
        entity.setCreateTime(new Date());
        dao.insertVisitor(entity);
        System.out.println(1/0);
    }
}
