package com.framework.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：添加功能描述.<br/>
 * 
 * #date： 2017年9月5日 下午2:16:53<br/>
 * #author 李旭<br/>
 * #since 1.0.0<br/>
 */
public class HeapOOM{
    static class oomObject{
    }

    public static void main(String[] args) {
        List<oomObject> list = new ArrayList<oomObject>();
        while (true) {
            list.add(new oomObject());
        }
    }
}
