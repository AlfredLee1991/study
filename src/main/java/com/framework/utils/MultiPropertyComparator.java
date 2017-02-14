package com.framework.utils;

import java.util.Comparator;

import org.apache.commons.beanutils.PropertyUtils;

/***
 * 
 * 功能描述：排序.<br/>
 * 
 * #date： 2015年12月18日 上午8:40:58<br/>
 * #author 张程<br/>
 * #since 1.0.0<br/>
 */
public class MultiPropertyComparator implements Comparator<Object>{
	
 	private String properties[];
    private boolean asc[];
    private int length;

    /***
     * 
     * @param properties bean对应属性
     * @param asc 【true 升序 false 降序】
     */
    public MultiPropertyComparator(String properties[], boolean asc[]){
        this.properties = null;
        length = 0;
        this.properties = properties;
        length = Math.min(properties.length, asc.length);
        this.asc = asc;
    }
    
    @Override
    public int compare(Object o1, Object o2){
        int result = 0;
        if(o1 != null && o2 == null)
            result = 1;
        else
        if(o1 == null && o2 != null)
            result = -1;
        else
        if(o1 != null && o2 != null)
        {
            for(int i = 0; i < length; i++)
                try
                {
                    Comparable p1 = (Comparable)PropertyUtils.getProperty(o1, properties[i]);
                    Comparable p2 = (Comparable)PropertyUtils.getProperty(o2, properties[i]);
                    if(p1 == null && p2 != null)
                        result = -1;
                    else
                    if(p2 == null && p1 != null)
                        result = 1;
                    else
                    if(p1 != null && p2 != null)
                        result = p1.compareTo(p2);
                    if(result != 0)
                        return asc[i] ? result : -result;
                }
                catch(Exception e) { }

        }
        return 0;
    }
}
