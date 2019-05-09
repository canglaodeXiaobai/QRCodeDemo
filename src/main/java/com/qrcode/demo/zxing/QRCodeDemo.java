package com.qrcode.demo.zxing;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;


public class QRCodeDemo {

    /**
     * 生成简单二维码
     * @param contents 二维码内容
     * @param width 宽
     * @param height 高
     * @throws Exception
     */
    public void createQRCode(String contents, int width, int height) throws Exception  {
        // 定义宽
        // int width = 300;
        // 高
        // int height = 300;
        // 文件类型
        String format = "png";
        // 内容
        // String contents = "我的二维码制作demo";

        // 定义二维码参数
        Map<EncodeHintType, Object> hints = new HashMap<>();

        // 设置字符集
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        // 设置容错等级，纠错能力越高，可存储的越少，一般使用M
        // L级：约可纠错7%的数据码字
        // M级：~15%
        // Q级：~25%
        // H级：~30%
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);

        // 设置边距，默认是5
        hints.put(EncodeHintType.MARGIN, 2);

        // 二维码编码 MultiFormatWriter().encode("内容", 格式, 宽度, 高度)
        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height);

        Path file = new File(new File("").getCanonicalPath() + "/code/img.png").toPath();

        // 绘制二维码
        MatrixToImageWriter.writeToPath(bitMatrix, format, file);

        System.out.println("成功！！！");
    }

    /**
     * 读取二维码数据
     * @throws Exception
     */
    public void readQrCode() throws Exception {
        MultiFormatReader formatReader = new MultiFormatReader();
        Map hints = new HashMap();
        // hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

        File file = new File(new File("").getCanonicalPath() + "/code/img.png");
        BufferedImage read = ImageIO.read(file);

        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(read)));

        Result result = formatReader.decode(binaryBitmap, hints);

        System.out.println("解析结果：" + result.toString());
        System.out.println("二维码格式：" + result.getBarcodeFormat());
        System.out.println("二维码文本内容：" + new String(result.getText().getBytes(), "utf-8"));
        System.out.println("二维码文本内容：" + result.getText());
    }
}
