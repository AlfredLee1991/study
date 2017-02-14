package com.framework.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.UUID;

/**
 * 字符串处理工具类.
 * 
 * @author lixu
 */
public class StringUtil{

    /** 初始值 */
    private static int serial = 0;

    /**
     * 最大序列值：999
     */
    private static final int MAX_SERIAL = 999;

    /**
     * 长度：12
     */
    private static final int SEQUENCE_LENTH = 12;

    /**
     * 同步方式uuid生成策略<br>
     * 核心处理与非同步方式一致，存在并发插入同一个表场景时，使用此方法
     */
    public static synchronized String makeUUID() {
        return aSyncMakeUUID();
    }

    /**
     * 非同步方式uuid生成策略
     */
    public static String aSyncMakeUUID() {
        StringBuilder ret = new StringBuilder();
        SimpleDateFormat dfDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        dfDate.setLenient(false);
        ret.append(dfDate.format((new GregorianCalendar()).getTime()));

        DecimalFormat dfNum = new DecimalFormat("000");
        ret.append(dfNum.format(serial++));

        if (serial > MAX_SERIAL) {
            serial = 0;
        }

        UUID uuid = UUID.randomUUID();
        ret.append(uuid.toString().replace("-", "").subSequence(0, SEQUENCE_LENTH));
        return ret.toString();
    }

    /**
     * 方法描述：生成的指定长度的数字字母组合的字符串<br/>
     * 
     * #author lixu<br/>
     * 
     * @param length
     * @return
     */
    public static String generateRandomPassword(int length) {
        String result = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 取得大写字母还是小写字母,65是 'A',97 是 'a'
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                result += (char) (choice + random.nextInt(26));
            } else {
                // 数字
                result += String.valueOf(random.nextInt(10));
            }
        }
        return result;
    }

    /**
     * 方法描述：生成指定长度的数字字符串 <br/>
     * 
     * #author lixu<br/>
     * 
     * @param length
     * @return
     */
    public static String generateVerifyCode(int length) {
        String result = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            result += String.valueOf(random.nextInt(10));
        }
        return result;
    }

}
