package com.framework.java8.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamToMapTest{

    public static void main(String[] args) {
        List<UserBo> list = new ArrayList<>();
        list.add(new UserBo(100, "Mohan"));
        list.add(new UserBo(100, "Sohan"));
        list.add(new UserBo(300, "Mahesh"));
        //Map<Integer, Object> map = list.stream().collect(Collectors.toMap(UserBo::getUserId, v -> v, (v1, v2) -> v1));
        //Map<Integer, Object> map = list.stream().collect(Collectors.toMap(UserBo::getUserId, v -> v.getUserName(), (v1, v2) -> v1));
        Map<Integer, Object> map = list.stream().collect(Collectors.toMap(UserBo::getUserId, UserBo::getUserName, (v1, v2) -> v1));
        map.forEach((v1, v2) -> System.out.println("Key: " + v1 + ", value: " + v2));
    }

}

class UserBo{
    private int UserId;
    private String UserName;

    public UserBo(int userId, String userName) {
        super();
        UserId = userId;
        UserName = userName;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    @Override
    public String toString() {
        return "UserBo [UserId=" + UserId + ", UserName=" + UserName + "]";
    }

}
