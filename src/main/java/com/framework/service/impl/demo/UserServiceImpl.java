package com.framework.service.impl.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.dao.demo.IUserDao;
import com.framework.dao.model.demo.User;
import com.framework.service.demo.IUserService;

/**
 * 功能描述：用户接口实现.<br/>
 * 
 * #author lixu<br/>
 */
@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public Map<String, Object> listUser(int pageNum, int pageSize, String sort, String order) {
        Map<String, Object> result = new HashMap<String, Object>();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao dao = sqlSession.getMapper(IUserDao.class);
        List<Map<String, Object>> resultMap = dao.queryUserList((pageNum - 1) * pageSize, pageSize, sort, order);
        List<User> userList = new ArrayList<>();
        for (Map<String, Object> map : resultMap) {
            User user = new User();
            user.setId(Integer.valueOf(map.get("id")+""));
            user.setFirstname(map.get("firstname") + "");
            user.setLastname(map.get("lastname") + "");
            user.setPhone(map.get("phone") + "");
            user.setEmail(map.get("email") + "");
            userList.add(user);
        }
        if (null != resultMap) {
            result.put("total", resultMap.get(0).get("total"));
            result.put("rows", userList);
        }

        return result;
    }

    @Override
    public int addUser(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao dao = sqlSession.getMapper(IUserDao.class);
        return dao.insertUser(user);
    }

    @Override
    public int modifyUser(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao dao = sqlSession.getMapper(IUserDao.class);
        return dao.updateUser(user);
    }

    @Override
    public int removeUser(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao dao = sqlSession.getMapper(IUserDao.class);
        return dao.deleteUser(id);
    }

}
