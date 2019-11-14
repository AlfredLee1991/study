package com.framework.zxing;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
/**
 * 通过google的zxing解析二维码
 * @author tskk
 * @version 2015-6-26 13:30:20
 * 注：此代码，不能解析：L纠错级别带logo和H级别的解析
 * */
public final class DecodeImgZxing {
    //二维码格式参数
    private static final EnumMap<DecodeHintType, Object> hints = new EnumMap<DecodeHintType, Object>(DecodeHintType.class);
    static{
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
    }
    /**
     * 解析二维码，使用google的zxing
     * @param imgPath 二维码路径
     * @return content 二维码内容
     * */
    public static String decodeImg(File imgFile){
        String content = null;
        if(!imgFile.isFile()){
            System.out.println("输入非文件");
            return null;
        }
        try {
            BufferedImage image = ImageIO.read(imgFile);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            //优化精度
            hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
            //复杂模式，开启PURE_BARCODE模式
            hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
            MultiFormatReader formatReader = new MultiFormatReader();
            Result result = formatReader.decode(binaryBitmap, hints);
            //设置返回值
            content = result.getText();
            System.out.println("result 为：" + result.toString());
            System.out.println("resultFormat 为：" + result.getBarcodeFormat());
            System.out.println("resultText 为：" + result.getText());
        } catch (NotFoundException e) {
            System.out.println("二维码解析NotFoundException");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("二维码解析IOException");
            e.printStackTrace();
        }
        return content;
    }
}