package com.qrcode.demo;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 读取二维码
 */
public class ReadQRCode {

    public static void main(String[] args) {

        MultiFormatReader formatReader = new MultiFormatReader();

        /*// 定义二维码参数
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();

        // HashMap<EncodeHintType, Object> hints = new HashMap();
        // 设置字符集编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

        // 设置容错等级，纠错能力越高，可存储的越少，一般使用M
        // L级：约可纠错7%的数据码字
        // M级：~15%
        // Q级：~25%
        // H级：~30%
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        // 设置边距，默认是5
        hints.put(EncodeHintType.MARGIN, 2);*/
        Map hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {

            // 获取图片文件
            File file = new File("E:/jl_whw/private/git_repository/QRCodeDemo/code/img.png");

            // 将文件转换成image
            BufferedImage image = ImageIO.read(file);

            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            Result result = formatReader.decode(bitmap, hints);

            System.out.println("解析结果：" + result.toString());
            System.out.println("二维码格式：" + result.getBarcodeFormat());
            System.out.println("二维码文本内容：" + result.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
