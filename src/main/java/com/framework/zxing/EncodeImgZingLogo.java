package com.framework.zxing;



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

/**
 * 通过google的zxing实现二维码(加入logo图片)
 * @author tskk
 * @version 2015-6-28 13:30:20
 * */
public final class EncodeImgZingLogo {
    /**
     * 二维码绘制logo
     * @param twodimensioncodeImg 二维码图片文件
     * @param logoImg logo图片文件
     * */
    public static BufferedImage encodeImgLogo(File twodimensioncodeImg,File logoImg){
        BufferedImage twodimensioncode = null;
        try{
            if(!twodimensioncodeImg.isFile() || !logoImg.isFile()){
                System.out.println("输入非图片");
                return null;
            }
            //读取二维码图片
            twodimensioncode = ImageIO.read(twodimensioncodeImg);
            //获取画笔
            Graphics2D g = twodimensioncode.createGraphics();
            //读取logo图片
            BufferedImage logo = ImageIO.read(logoImg);
            
            //设置二维码大小，太大，会覆盖二维码，此处20%
            int width = logo.getWidth(null) > twodimensioncode.getWidth()*2 /10 ? (twodimensioncode.getWidth()*2 /10) : logo.getWidth(null);
            int height = logo.getHeight(null) > twodimensioncode.getHeight()*2 /10 ? (twodimensioncode.getHeight()*2 /10) : logo.getHeight(null);
            //设置logo图片放置位置
            //中心
            int x = (twodimensioncode.getWidth() - width) / 2;
            int y = (twodimensioncode.getHeight() - height) / 2;
            //右下角，15为调整值
//          int x = twodimensioncode.getWidth()  - logoWidth-15;
//          int y = twodimensioncode.getHeight() - logoHeight-15;
            //开始合并绘制图片
            g.drawImage(logo, x, y, width, height, null);
            g.drawRoundRect(x, y, width, height, 15 ,15);
            //logo边框大小
            g.setStroke(new BasicStroke(2));
            //logo边框颜色
            g.setColor(Color.WHITE);
            g.drawRect(x, y, width, height);
            g.dispose();
            logo.flush();
            twodimensioncode.flush();
        }catch(Exception e){
            System.out.println("二维码绘制logo失败");
        }
        return twodimensioncode;
    }
    
    /**
     * 二维码输出到文件
     * @param twodimensioncodeImg 二维码图片文件
     * @param logoImg logo图片文件
     * @param format 图片格式
     * @param file 输出文件
     * */
    public static void writeToFile(File twodimensioncodeImg,File logoImg,String format,File file){
        BufferedImage image = encodeImgLogo(twodimensioncodeImg, logoImg);
        try {
            ImageIO.write(image, format, file);
        } catch (IOException e) {
            System.out.println("二维码写入文件失败"+e.getMessage());
        }
    }
    /**
     * 二维码流式输出
     * @param twodimensioncodeImg 二维码图片文件
     * @param logoImg logo图片文件
     * @param format 图片格式
     * @param stream 输出流
     * */
    public static void writeToStream(File twodimensioncodeImg,File logoImg,String format,OutputStream stream){
        BufferedImage image = encodeImgLogo(twodimensioncodeImg, logoImg);
        try {
            ImageIO.write(image, format, stream);
        } catch (IOException e) {
            System.out.println("二维码写入流失败"+e.getMessage());
        }
    }
}
