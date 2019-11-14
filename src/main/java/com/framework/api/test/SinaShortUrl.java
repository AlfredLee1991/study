package com.framework.api.test;

import java.util.Base64;

import com.framework.utils.HttpUtil;

/**
 * 功能描述：添加功能描述.<br/>
 * 
 * #date： 2019年11月13日 下午2:32:27<br/>
 * #author 8104485-李旭<br/>
 * #since 1.0.0<br/>
 */
public class SinaShortUrl{

    public static void main(String[] args) throws Exception {
        String prefix = "http://dwz.wailian.work/api.php?from=w&url=";
        String originUrl = "https://ainewqas.cttq.com/cvue/New-Login/login";
        String encodeUrl = new String(Base64.getEncoder().encode(originUrl.getBytes()));
        System.out.println(encodeUrl);
        // http://dwz.wailian.work/api.php?from=w&url=aHR0cHM6Ly9haW5ld3Fhcy5jdHRxLmNvbS9jdnVlL05ldy1Mb2dpbi9sb2dpbg==&site=tinyurl
//        String url = prefix + encodeUrl + "&site=tinyurl";
        String url = "http://dwz.wailian.work/api.php?from=w&url=aHR0cHM6Ly9haW5ld3Fhcy5jdHRxLmNvbS9jdnVlL05ldy1Mb2dpbi9sb2dpbg==&site=tinyurl";
        System.out.println(url);
        String result = HttpUtil.doGet(url);
        System.out.println(result);
    }
}
