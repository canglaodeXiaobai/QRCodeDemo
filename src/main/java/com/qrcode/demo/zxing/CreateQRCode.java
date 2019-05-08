package com.qrcode.demo.zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成二维码
 */
public class CreateQRCode {
    public static void main(String[] args) {

        int width = 300;
        int height = 300;
        String format = "png";
        String contents = "www.baidu.com";

        // 定义二维码参数
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
        hints.put(EncodeHintType.MARGIN, 2);

        // 生成二维码
        try {

            // new MultiFormatWriter().encode("内容", 格式, 宽度, 高度)
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height);

            Path file = new File("E:/jl_whw/private/git_repository/QRCodeDemo/code/img.png").toPath();

            // 生成
            // MatriToImageWriter
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
