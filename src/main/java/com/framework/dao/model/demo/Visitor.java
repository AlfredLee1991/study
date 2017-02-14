package com.framework.dao.model.demo;

import java.util.Date;

/**
 * 功能描述：持久化实体.<br/>
 * 
 * #author lixu<br/>
 */
public class Visitor{

    private int id;
    
    private String name;
    
    private String email;
    
    private int status;
    
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
