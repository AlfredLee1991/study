package com.framework.dao.model.demo;

/**
 * 功能描述：用户持久化实体.<br/>
 * 
 * #author lixu<br/>
 */
public class User{

    private int id;
    
    private String firstname;
    
    private String lastname;
    
    private String phone;
    
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", phone=" + phone
                + ", email=" + email + "]";
    }
}
