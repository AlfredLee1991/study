package com.framework.zxing;

import java.io.File;

/**
 * 功能描述：添加功能描述.<br/>
 * 
 * #date： 2017年8月2日 下午8:15:55<br/>
 * #author 李旭<br/>
 * #since 1.0.0<br/>
 */
public class EncodeImgZingLogoTest{

    public static void main(String[] args) {
        String contents = "http://blog.csdn.net/typa01_kk";  
        String format = "png"; //***此处如果格式为"gif"，则logo图片为黑色，其他格式ok  
        //生成二维码  
        File logoImg = new File("D:"+File.separator+"logo.png");  
        File img = new File("D:"+File.separator+"csdn.png");  
        EncodeImgZxing.writeToFile(contents, format, img);  
        //添加logo图片  
        File img1 = new File("D:"+File.separator+"qrcodelogo.png");  
        EncodeImgZingLogo.writeToFile(img, logoImg, format, img1);  
          
        //解析二维码  
        String content = DecodeImgZxing.decodeImg(img);  
        System.out.println("1:"+content);  
        //带logo  
        String content1 = DecodeImgZxing.decodeImg(img1);  
        System.out.println("2:"+content1);  
    }
}
