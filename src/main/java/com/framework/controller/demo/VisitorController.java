package com.framework.controller.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.framework.dao.model.demo.Visitor;
import com.framework.service.demo.IVisitorService;

/**
 * 功能描述：访客接口.<br/>
 * 
 * #author lixu<br/>
 */
@Controller
public class VisitorController{
    
    private static final Logger logger = LoggerFactory.getLogger(VisitorController.class);

    @Autowired
    private IVisitorService visitorService;
    
    @RequestMapping(method=RequestMethod.GET,value="listVisitorByState")
    public ModelAndView listVisitorByState(HttpServletRequest request,HttpServletResponse response,@RequestParam int state){
        List<Visitor> list = visitorService.getVisitorByState(state);
        ModelAndView model = new ModelAndView();
        model.addObject("visitorList", list);
        model.setViewName("listVisitor");
        return model;
    }
    
    @RequestMapping(method=RequestMethod.GET,value="listAllVisitor")
    public ModelAndView listAllVisitor(HttpServletRequest request,HttpServletResponse response){
        List<Visitor> list = visitorService.getAllVisitor();
        ModelAndView model = new ModelAndView();
        model.addObject("visitorList", list);
        model.setViewName("listVisitor");
        return model;
    }
    
    @RequestMapping(method=RequestMethod.GET,value="addVisitor")
    public void addVisitor(HttpServletRequest request,HttpServletResponse response){
        logger.debug("ready add visitor");
        visitorService.addVisitor();
        try {
            PrintWriter writer = response.getWriter();
            writer.print("<html><script>alert(\"add visitor success!\")</script></html>");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            logger.error("add visitor fail",e);
        }
    }
}
