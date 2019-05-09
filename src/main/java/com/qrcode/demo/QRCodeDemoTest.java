package com.qrcode.demo;

import com.qrcode.demo.zxing.QRCodeDemo;

public class QRCodeDemoTest {
    public static void main(String[] args) {

        QRCodeDemo demo = new QRCodeDemo();

        try {
            demo.createQRCode("www.github.com", 300, 300);

            // demo.readQrCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
