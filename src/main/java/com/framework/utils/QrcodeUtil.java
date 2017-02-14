package com.framework.utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 功能描述：二维码工具类.<br/>
 * 
 * #date： 2015年12月14日 上午10:13:57<br/>
 * #author lixu<br/>
 * #since 1.0.0<br/>
 */
public class QrcodeUtil{

    /**
     * 
     * 方法描述：添加方法描述.<br/>
     *
     * #author lixu<br/>
     * #date 2015年12月15日 上午11:08:49<br/>
     * #since 1.0.0<br/>
     * 
     * @param content
     * @param width
     * @param height
     * @param filePath
     * @return
     */
    public static String Dencode(String content, int width, int height, String filePath) {
        try {
            File file = new File(filePath);

            Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF8");

            QRCodeWriter writer = new QRCodeWriter();

            // 生成二维码↓
            BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height, hints);

            // 重设白边大小
            matrix = updateBit(matrix, 5);

            MatrixToImageWriter.writeToFile(matrix, "png", file);
            return file.getPath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 
     * 方法描述：生成二维码流.<br/>
     *
     * #author lixu<br/>
     * #date 2015年12月15日 上午11:09:03<br/>
     * #since 1.0.0<br/>
     * 
     * @param content
     * @param width
     * @param height
     * @param os
     * @return
     */
    public static OutputStream Dencode(String content, int width, int height,int margin, OutputStream os) {
        try {
            Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF8");

            QRCodeWriter writer = new QRCodeWriter();

            // 生成二维码↓
            BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height, hints);

            // 重设白边大小
            matrix = updateBit(matrix, margin);

            MatrixToImageWriter.writeToStream(matrix, "png", os);
            return os;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 
     * 方法描述：重设白边框宽度.<br/>
     *
     * #author lixu<br/>
     * #date 2015年12月15日 上午11:08:57<br/>
     * #since 1.0.0<br/>
     * 
     * @param matrix
     * @param margin
     * @return
     */
    private static BitMatrix updateBit(BitMatrix matrix, int margin) {
        int tempM = margin * 2;
        int[] rec = matrix.getEnclosingRectangle(); // 获取二维码图案的属性
        int resWidth = rec[2] + tempM;
        int resHeight = rec[3] + tempM;
        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight); // 按照自定义边框生成新的BitMatrix
        resMatrix.clear();
        for (int i = margin; i < resWidth - margin; i++) { // 循环，将二维码图案绘制到新的bitMatrix中
            for (int j = margin; j < resHeight - margin; j++) {
                if (matrix.get(i - margin + rec[0], j - margin + rec[1])) {
                    resMatrix.set(i, j);
                }
            }
        }
        return resMatrix;
    }

    /**
     * 
     * 方法描述：图片放大缩小.<br/>
     *
     * #author lixu<br/>
     * #date 2015年12月14日 下午1:39:18<br/>
     * #since 1.0.0<br/>
     * 
     * @param originalImage
     * @param width
     * @param height
     * @return
     */
    public static BufferedImage zoomInImage(BufferedImage originalImage, int width, int height) {
        BufferedImage newImage = new BufferedImage(width, height, originalImage.getType());
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return newImage;
    }
}
