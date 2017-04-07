package com.framework.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 功能描述：添加功能描述.<br/>
 * 
 * #date： 2017年4月7日 上午10:13:02<br/>
 * #author 李旭<br/>
 * #since 1.0.0<br/>
 */
public class WeekendHolidayTest{

    private static List<Calendar> holidayList = new ArrayList<Calendar>(); // 节假日列表
    private static String[] holidays = new String[] { "2017-05-01" }; // 节假日列表
    private static List<Calendar> holidayWorkList = new ArrayList<Calendar>(); // 是周末但正常上班列表
    private static String[] holidayWorks = new String[] {}; // 是周末但正常上班列表

    static {
        for (String holiday : holidays) {
            String[] day = holiday.split("-");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.valueOf(day[0]));
            calendar.set(Calendar.MONTH, Integer.valueOf(day[1]) - 1);// 月份比正常小1,0代表一月
            calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(day[2]));
            holidayList.add(calendar);
        }
        for (String holiday : holidayWorks) {
            String[] day = holiday.split("-");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.valueOf(day[0]));
            calendar.set(Calendar.MONTH, Integer.valueOf(day[1]) - 1);// 月份比正常小1,0代表一月
            calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(day[2]));
            holidayWorkList.add(calendar);
        }
    }

    public static void main(String[] args) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            Date d = df.parse("2017-04-09");
            calendar.setTime(d);// 设置当前时间
            boolean result = checkHoliday(calendar);
            System.out.println("是否是节假日：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean checkHoliday(Calendar calendar) throws Exception {
        // 判断日期是否是节假日
        for (Calendar ca : holidayList) {
            if (ca.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)
                    && ca.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)
                    && ca.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
                return true;
            }
        }
        // 判断日期是否是周六周日
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
                || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            // 判断日期是否周末但正常上班
            for (Calendar ca : holidayWorkList) {
                if (ca.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)
                        && ca.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)
                        && ca.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
