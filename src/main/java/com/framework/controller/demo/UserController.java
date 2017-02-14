package com.framework.controller.demo;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.dao.model.demo.User;
import com.framework.service.demo.IUserService;
import com.google.gson.Gson;

/**
 * 功能描述：用户接口.<br/>
 * 
 * #author lixu<br/>
 */
@Controller
public class UserController{

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "listUser")
    @ResponseBody
    public String listUser(HttpServletRequest request, HttpServletResponse response, @RequestParam int page,
            @RequestParam int rows) {
        logger.debug("当前页：" + page);
        logger.debug("页大小：" + rows);
        String sort = request.getParameter("sort");
        String order = request.getParameter("order");
        Map<String, Object> map = userService.listUser(page, rows, sort, order);
        Gson json = new Gson();
        return json.toJson(map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "addUser")
    @ResponseBody
    public String addUser(HttpServletRequest request, HttpServletResponse response, User user) {
        logger.debug("入参User：" + user.toString());
        int result = userService.addUser(user);
        logger.debug("新增用户结果：" + ((result == 1) ? true : false));
        Gson json = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", (result == 1) ? true : false);
        map.put("msg", (result == 1) ? "add user success" : "add user fail");
        return json.toJson(map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "updateUser")
    @ResponseBody
    public String updateUser(HttpServletRequest request, HttpServletResponse response, User user) {
        logger.debug("入参User：" + user.toString());
        int result = userService.modifyUser(user);
        logger.debug("修改用户结果：" + ((result == 1) ? true : false));
        Gson json = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", (result == 1) ? true : false);
        map.put("msg", (result == 1) ? "modify user success" : "modify user fail");
        return json.toJson(map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "removeUser")
    @ResponseBody
    public String removeUser(HttpServletRequest request, HttpServletResponse response, int id) {
        logger.debug("入参id：" + id);
        int result = userService.removeUser(id);
        logger.debug("删除用户结果：" + ((result == 1) ? true : false));
        Gson json = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", (result == 1) ? true : false);
        map.put("msg", (result == 1) ? "delete user success" : "delete user fail");
        return json.toJson(map);
    }
}
