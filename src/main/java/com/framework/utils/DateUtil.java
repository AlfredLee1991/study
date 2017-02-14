package com.framework.utils;

import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能描述：时间处理工具类.<br/>
 * 
 * #author lixu<br/>
 */
public class DateUtil{

    /**
     * 日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    /**
     * yyyy-MM-dd格式日期正则式
     */
    public static final String DATE_PATTERN = "\\d{4}\\-\\d{1,2}\\-\\d{1,2}";

    /**
     * 年月日：yyyy-MM-dd
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * HH:mm:ss 格式时间正则式
     */
    public static final String TIME_PATTERN = "\\d{1,2}\\:\\d{1,2}\\:\\d{1,2}";

    /**
     * 时分秒： HH:mm:ss
     */
    public static final String TIME_FORMAT = "HH:mm:ss";

    /**
     * 短时分秒： HH:mm
     */
    public static final String SHORT_TIME_FORMAT = "HH:mm";

    /**
     * 时分秒： HH:mm:ss
     */
    public static final String HOUR_AND_MITUNE_FORMAT = "HH:mm";

    /**
     * 时分秒： hhmmss
     */
    public static final String TIME_NUM_PATTERN = "\\d{6}";

    /**
     * yyyy-MM-dd HH:mm:ss 格式日期+时间的正则式
     */
    public static final String TIMESTAMP_PATTERN = "\\d{4}\\-\\d{1,2}\\-\\d{1,2} \\d{1,2}\\:\\d{1,2}\\:\\d{1,2}";

    /**
     * 年月日时分秒： yyyy-MM-dd HH:mm:ss
     */
    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * yyyyMMdd 格式日期正则式
     */
    public static final String SHORT_DATE_PATTERN = "\\d{8}";

    /**
     * yyyy-MM-dd HH:mm:ss.S CST 格式日期+时间的完整正则式
     */
    public static final String TIMESTAMP_PATTERN_MM = "\\d{4}\\-\\d{1,2}\\-\\d{1,2}"
            + " \\d{1,2}\\:\\d{1,2}\\:\\d{1,2}.\\d{1} CST";

    /**
     * 年月日时分秒微秒： yyyy-MM-dd HH:mm:ss.S
     */
    public static final String TIMESTAMP_FORMAT_MM = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * EEE MMM dd HH:mm:ss zzz yyyy 格式的正则式
     */
    public static final String TIMESTAMP_PATTERN_EN = "\\w{3}\\ \\w{3}\\ \\d{1,2}"
            + " \\d{1,2}\\:\\d{1,2}\\:\\d{1,2} CST \\d{4}";

    /**
     * EEE MMM dd HH:mm:ss zzz yyyy
     */
    public static final String TIMESTAMP_FORMAT_EN = "EEE MMM dd HH:mm:ss zzz yyyy";

    /**
     * 年月日：yyyyMMdd
     */
    public static final String DATE_NUM_FORMAT = "yyyyMMdd";

    /**
     * 年月日：yyMMdd
     */
    public static final String DATE_NUM_FORMAT_SIMPLE = "yyMMdd";

    /**
     * HHmmss 格式时间正则式
     */
    private static final String TIME_NUM_FORMAT = "HHmmss";

    public static final String TIMESTAMP_FORMAT_HOUR_MIN = "yyyy-MM-dd HH:mm";

    /**
     * 私有构造函数，避免被通过new方法创建对象
     */
    private DateUtil() {
    }

    /**
     * 方法描述：根据默认格式yyyyMMdd，将字符串参数转为日期对象输出 <br/>
     * 
     * #author lixu<br/>
     * #date 2015年10月26日 下午2:04:59<br/>
     * #since 1.0.0<br/>
     * 
     * @param str
     * @return
     */
    public static Date parse(final String str) {
        final DateFormat format = new SimpleDateFormat(DATE_NUM_FORMAT);
        try {
            return format.parse(str);
        } catch (ParseException e) {
            LOGGER.error(str + " is not a valid Date String.", e);
        }
        return null;
    }

    /**
     * 方法描述：根据指定格式将字符串转为日期格式 <br/>
     * 
     * #author lixu<br/>
     * #date 2015年10月26日 下午2:05:39<br/>
     * #since 1.0.0<br/>
     * 
     * @param str
     * @param dateFormat
     * @return
     */
    public static Date parse(final String str, String dateFormat) {
        final DateFormat format = new SimpleDateFormat(dateFormat);
        try {
            return format.parse(str);
        } catch (ParseException e) {
            LOGGER.error(str + " is not a valid Date String.", e);
        }
        return null;
    }
    
    /**
     * 方法描述：不同日期字符串转换成日期对象 <br/>
     * 
     * #author lixu<br/>
     * #date 2015年10月26日 下午2:06:57<br/>
     * #since 1.0.0<br/>
     * 
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String dateStr) throws ParseException {
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        Matcher matcher = pattern.matcher(dateStr);
        SimpleDateFormat sdf = null;
        Date date = null;
        // 匹配yyyy-MM-dd格式日期
        if (matcher.matches()) {
            sdf = new SimpleDateFormat(DATE_FORMAT);
            date = sdf.parse(dateStr);
        } else {
            pattern = Pattern.compile(TIME_PATTERN);
            matcher = pattern.matcher(dateStr);
            // 匹配HH:mm:ss格式时间
            if (matcher.matches()) {
                sdf = new SimpleDateFormat(TIME_FORMAT);
                date = sdf.parse(dateStr);
            } else {
                pattern = Pattern.compile(TIMESTAMP_PATTERN);
                matcher = pattern.matcher(dateStr);
                // yyyy-MM-dd HH:mm:ss 格式日期+时间
                if (matcher.matches()) {
                    sdf = new SimpleDateFormat(TIMESTAMP_FORMAT);
                    date = sdf.parse(dateStr);
                } else {
                    pattern = Pattern.compile(SHORT_DATE_PATTERN);
                    matcher = pattern.matcher(dateStr);
                    // 匹配yyyyMMdd 格式日期
                    if (matcher.matches()) {
                        sdf = new SimpleDateFormat(DATE_NUM_FORMAT);
                        date = sdf.parse(dateStr);
                    }
                }
            }
        }

        // 如果还没匹配上，按西文格式时间匹配
        if (date == null) {
            pattern = Pattern.compile(TIMESTAMP_PATTERN_EN);
            matcher = pattern.matcher(dateStr);
            // 按EEE MMM dd HH:mm:ss zzz yyyy 格式匹配
            if (matcher.matches()) {
                sdf = new SimpleDateFormat(TIMESTAMP_FORMAT_EN, Locale.US);
                date = sdf.parse(dateStr);
            } else {
                pattern = Pattern.compile(TIMESTAMP_PATTERN_MM);
                matcher = pattern.matcher(dateStr);
                // 按yyyy-MM-dd HH:mm:ss.S CST 格式匹配
                if (matcher.matches()) {
                    sdf = new SimpleDateFormat(TIMESTAMP_FORMAT_MM);
                    date = sdf.parse(dateStr);
                }
            }
        }
        return date;
    }

    /**
     * 方法描述：根据默认格式HH:mm:ss解析字符串转为日期对象 <br/>
     * 
     * #author lixu<br/>
     * #date 2015年10月26日 下午2:05:59<br/>
     * #since 1.0.0<br/>
     * 
     * @param str
     * @return
     */
    public static Date parseTime(final String str) {
        final DateFormat format = new SimpleDateFormat(TIME_FORMAT);
        try {
            return format.parse(str);
        } catch (ParseException e) {
            LOGGER.error(str + " is not a valid Date String.", e);
        }
        return null;
    }
    
    /**
     * 方法描述：字符串转化为java.sql.time <br/>
     * 
     * #author lixu<br/>
     * #date 2015年10月26日 下午2:07:52<br/>
     * #since 1.0.0<br/>
     * 
     * @param timeStr
     * @return
     * @throws ParseException
     */
    public static Time parseTime4Sql(String timeStr) throws ParseException {
        Time time = null;
        Pattern pattern = Pattern.compile(TIME_PATTERN);
        Matcher matcher = pattern.matcher(timeStr);
        if (matcher.matches()) {
            time = Time.valueOf(timeStr);
        } else {
            pattern = Pattern.compile(TIME_NUM_PATTERN);
            matcher = pattern.matcher(timeStr);
            if (matcher.matches()) {
                final DateFormat format = new SimpleDateFormat(TIME_NUM_FORMAT);
                final Date date = format.parse(timeStr);
                time = new Time(date.getTime());
            }
        }
        return time;
    }


    /**
     * 方法描述： 默认将日期转为yyyyMMdd格式字符串 <br/>
     * 
     * #author lixu<br/>
     * #date 2015年10月26日 下午2:06:13<br/>
     * #since 1.0.0<br/>
     * 
     * @param date
     * @return
     */
    public static String format(final Date date) {
        final DateFormat format = new SimpleDateFormat(DATE_NUM_FORMAT);
        if (date != null) {
            return format.format(date);
        }
        return null;
    }

    /**
     * 方法描述： 默认将日期转为如yyyyMMdd格式的字符串 <br/>
     * 
     * #author 杲祥龙<br/>
     * #date 2016年4月7日 下午1:45:16<br/>
     * #since 1.0.0<br/>
     * 
     * @param date
     *            日期
     * @param dateFormat
     *            日期格式
     * @return
     */
    public static String format(final Date date, final String dateFormat) {
        final DateFormat format = new SimpleDateFormat(dateFormat);
        if (date != null) {
            return format.format(date);
        }
        return null;
    }

    /**
     * 方法描述： 默认将日期转为yyyy-MM-dd HH:mm:ss格式字符串 <br/>
     * 
     * #author lixu<br/>
     * #date 2015年10月26日 下午2:06:13<br/>
     * #since 1.0.0<br/>
     * 
     * @param date
     * @return
     */
    public static String formatTime(final Date date) {
        final DateFormat format = new SimpleDateFormat(TIMESTAMP_FORMAT_MM);
        if (date != null) {
            return format.format(date);
        }
        return null;
    }

    /**
     * 方法描述：默认将日期转为yyyy-MM-dd格式字符串<br/>
     * 
     * #author lixu<br/>
     * #date 2015年10月26日 下午2:06:27<br/>
     * #since 1.0.0<br/>
     * 
     * @param date
     * @return
     * @throws ParseException
     */
    public static String formatDate(String date) throws ParseException {
        final DateFormat format = new SimpleDateFormat(DATE_NUM_FORMAT);
        final Date d = format.parse(date);
        final DateFormat format1 = new SimpleDateFormat(DATE_FORMAT);
        return format1.format(d);
    }
    
    /**
     * 方法描述：将时间的毫秒数转成时间格式<br/>
     * 
     * #author lixu<br/>
     * #date 2015年12月3日 上午9:02:31<br/>
     * #since 1.0.0<br/>
     * 
     * @param time
     *            时间的毫米数
     * @param dateFormat
     *            时间格式
     * @return
     */
    public static String formatDate(long time, String dateFormat) {
        Date date = new Date();
        date.setTime(time);
        SimpleDateFormat dateFm = new SimpleDateFormat(dateFormat);
        return dateFm.format(date);
    }

    /**
     * 方法描述：根据指定格式转换日期到字符串<br/>
     * 
     * #author lixu<br/>
     * #date 2015年10月26日 下午2:06:42<br/>
     * #since 1.0.0<br/>
     * 
     * @param date
     * @param formatStr
     * @return
     */
    public static String formatDate(Date date, String formatStr) {
        final DateFormat format = new SimpleDateFormat(formatStr);
        if (date != null) {
            return format.format(date);
        }
        return null;
    }


    /**
     * 方法描述：获取当前时间戳 <br/>
     * 
     * #author lixu<br/>
     * #date 2015年10月26日 下午2:07:11<br/>
     * #since 1.0.0<br/>
     * 
     * @return
     */
    public static Timestamp getCurrentTimestamp() {
        final Timestamp ts = new Timestamp(System.currentTimeMillis());
        return ts;
    }

    /**
     * 方法描述：获取当前日期 <br/>
     * 
     * #author lixu<br/>
     * #date 2015年10月26日 下午2:07:23<br/>
     * #since 1.0.0<br/>
     * 
     * @return
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * 方法描述：获取当前小时数 <br/>
     * 
     * #author lixu<br/>
     * #date 2015年10月26日 下午2:07:34<br/>
     * #since 1.0.0<br/>
     * 
     * @return
     */
    public static int getCurrentHour() {

        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

   
    /**
     * 方法描述：获得指定日期date的指定偏移量years年数的日期 <br/>
     * 
     * #date 2016年4月21日 下午4:57:18<br/>
     * #author lixu<br/>
     * #since 1.0.0<br/>
     * 
     * @param date
     * @param years
     * @return
     */
    public static Date addYear(Date date, int years) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, years);
        return c.getTime();
    }

    /**
     * 方法描述：获得指定日期date的指定偏移量days天数的日期 <br/>
     * 
     * #author lixu<br/>
     * #date 2015年10月26日 下午2:08:26<br/>
     * #since 1.0.0<br/>
     * 
     * @param date
     * @param days
     * @return
     */
    public static Date addDay(Date date, int days) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, days);
        return c.getTime();
    }

    /**
     * 方法描述：获得指定日期date的指定偏移量hours小时的日期 <br/>
     * 
     * #author lixu<br/>
     * #date 2015年10月26日 下午2:08:38<br/>
     * #since 1.0.0<br/>
     * 
     * @param date
     * @param hours
     * @return
     */
    public static Date addHour(Date date, int hours) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, hours);
        return c.getTime();
    }

    /**
     * 方法描述：获得指定日期date的指定偏移量mins分钟的日期 <br/>
     * 
     * #author lixu<br/>
     * #date 2015年10月26日 下午2:08:50<br/>
     * #since 1.0.0<br/>
     * 
     * @param date
     * @param mins
     * @return
     */
    public static Date addMin(Date date, int mins) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, mins);
        return c.getTime();
    }

    /**
     * 方法描述：获得指定日期date的指定偏移量seconds秒的日期 <br/>
     * 
     * #author lixu<br/>
     * #date 2015年10月26日 下午2:09:02<br/>
     * #since 1.0.0<br/>
     * 
     * @param date
     * @param seconds
     * @return
     */
    public static Date addSecond(Date date, int seconds) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, seconds);
        return c.getTime();
    }

    /**
     * 方法描述：获取两个时间的天数上的差值 <br/>
     * 
     * #author lixu<br/>
     * #date 2015年10月26日 下午2:09:15<br/>
     * #since 1.0.0<br/>
     * 
     * @param preDate
     * @param date
     * @return
     */
    public static int daysDifference(Date preDate, Date date) {
        final long milliSeconds = date.getTime() - preDate.getTime();
        return new Long(milliSeconds / 1000 / 3600 / 24).intValue();
    }


    /**
     * 方法描述：获取两个时间的秒数上的差值 <br/>
     * 
     * #author lixu<br/>
     * #date 2015年10月26日 下午2:09:15<br/>
     * #since 1.0.0<br/>
     * 
     * @param preDate
     * @param date
     * @return
     */
    public static int secondsDifference(Date preDate, Date date) {
        final long milliSeconds = date.getTime() - preDate.getTime();
        return new Long(milliSeconds / 1000).intValue();
    }

    /**
     * 方法描述：根据传入时间对时间进行展示 <br/>
     * 
     * <pre>
     * 展示规则：以当前时间为基准
     * 当天时间展示 "HH:ss"
     * 昨天时间展示"昨天"
     * 如果属于近7天的，展示"周几"，否则展示"yyyy-MM-dd"
     * </pre>
     * 
     * #author lixu<br/>
     * #date 2015年10月26日 下午2:09:50<br/>
     * #since 1.0.0<br/>
     * 
     * @param date
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String displayDate(Date date) throws UnsupportedEncodingException {
        final Date currentDate = getCurrentDate();
        final String currentDateStr = format(currentDate);
        final String dateStr = format(date);
        final int dayDiff = Integer.parseInt(currentDateStr) - Integer.parseInt(dateStr);
        if (currentDateStr.equalsIgnoreCase(dateStr)) {
            return formatDate(date, "HH:mm");
        } else if (1 == dayDiff) {
            return new String("昨天".getBytes("UTF-8"), "UTF-8");
        } else if (6 < dayDiff) {
            return formatDate(date, DATE_FORMAT);
        } else {
            final SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
            return dateFm.format(date);
        }
    }

    /**
     * 方法描述：获取入参时间与当前时间的差值 <br/>
     * 
     * <pre>
     *  相差小于1分钟展示：XX秒前
     *  相差小于1小时展示：XX分钟前
     *  相差大于1小时，小于24小时，向下对小时数取整展示：XX小时前
     *  相差大于1，小于7天，向下对天数取整展示：XX天前
     *  相差大于7天展示：yyyy-MM-dd HH:mm:ss
     * </pre>
     * 
     * #author lixu<br/>
     * #date 2015年10月26日 下午2:11:00<br/>
     * #since 1.0.0<br/>
     * 
     * @param preDate
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String diffCurrentDate(Date preDate) throws UnsupportedEncodingException {
        final Date currentDate = getCurrentDate();
        final int milliSeconds = new Long(currentDate.getTime() - preDate.getTime()).intValue();
        final int seconds = milliSeconds / 1000;
        final int mins = milliSeconds / 1000 / 60;
        final int hours = milliSeconds / 1000 / 60 / 60;
        final int days = milliSeconds / 1000 / 60 / 60 / 24;
        if (seconds < 60) {
            return new StringBuilder().append(seconds).append(new String("秒前".getBytes("UTF-8"), "UTF-8")).toString();
        } else if (mins < 60) {
            return new StringBuilder().append(mins).append(new String("分钟前".getBytes("UTF-8"), "UTF-8")).toString();
        } else if (hours > 1 && hours < 24) {
            return new StringBuilder().append(hours).append(new String("小时前".getBytes("UTF-8"), "UTF-8")).toString();
        } else if (hours > 24 && days < 7) {
            return new StringBuilder().append(days).append(new String("天前".getBytes("UTF-8"), "UTF-8")).toString();
        } else {
            return formatDate(preDate, TIMESTAMP_FORMAT);
        }
    }

    /**
     * 方法描述：根据传入的时间获取当天的开始时间点喝结束时间点 <br/>
     * 
     * #author lixu<br/>
     * #date 2016年3月28日 下午4:48:25<br/>
     * #since 1.0.0<br/>
     * 
     * @param date
     * @return <"begin","2016-03-28 00:00:00">,<"end","2016-03-28 23:59:59">
     */
    public static Map<String, Date> getDayBeginAndEnd(Date date) {
        Map<String, Date> map = new HashMap<String, Date>();
        String dateStr = formatDate(date, DATE_FORMAT);
        StringBuilder beginBuilder = new StringBuilder();
        beginBuilder.append(dateStr).append(" ").append("00:00:00");
        map.put("begin", parse(beginBuilder.toString(), TIMESTAMP_FORMAT));
        StringBuilder endBuilder = new StringBuilder();
        endBuilder.append(dateStr).append(" ").append("23:59:59");
        map.put("end", parse(endBuilder.toString(), TIMESTAMP_FORMAT));
        return map;
    }

    /**
     * 方法描述：根据传入的时间获取一个周期内的开始时间点喝结束时间点 <br/>
     * 
     * #author lixu<br/>
     * #date 2016年6月6日 下午4:48:25<br/>
     * #since 1.0.0<br/>
     * 
     * @param date
     * @param period
     *            间隔周期 默认为0
     * @return <"begin","2016-03-28 00:00:00">,<"end","2016-03-28 23:59:59">
     * @throws ParseException
     */
    public static Map<String, Date> getDayBeginAndEnd(Date date, int period) throws ParseException {
        Map<String, Date> map = new HashMap<String, Date>();
        Date beginDate = addDay(date, period);
        String begin = formatDate(beginDate, DATE_FORMAT);
        StringBuilder beginBuilder = new StringBuilder();
        beginBuilder.append(begin).append(" ").append("00:00:00");
        map.put("begin", parse(beginBuilder.toString(), TIMESTAMP_FORMAT));
        String end = formatDate(date, DATE_FORMAT);
        StringBuilder endBuilder = new StringBuilder();
        endBuilder.append(end).append(" ").append("23:59:59");
        map.put("end", parse(endBuilder.toString(), TIMESTAMP_FORMAT));
        return map;
    }

    /***
     * 获取某月的第一天
     */
    public static Date getFirstMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /***
     * 获取某月的最后一天
     */
    public static Date getEndMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }
    
    /**
     * 方法描述：根据生日获取年龄 <br/>
     * 
     * #author lixu<br/>
     * #date 2016年2月18日 下午3:04:07<br/>
     * #since 1.0.0<br/>
     * 
     * @param birthday
     * @return
     */
    public static int getAge(Date birthday) {
        if (birthday == null) {
            return -1;
        }
        Calendar cal = Calendar.getInstance();

        Calendar bir = Calendar.getInstance();
        bir.setTime(new Date());

        if (cal.before(bir)) {
            return 0;
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthday);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                // monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                // monthNow<monthBirth
                age--;
            }
        }

        return age > 0 ? age : 0;
    }

}
