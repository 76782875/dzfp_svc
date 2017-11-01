package com.rjxx.taxeasy.bizcomm.utils;

import com.rjxx.utils.TemplateUtils;
import com.swetake.util.Qrcode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by xlm on 2017/11/1.
 */
public class QRCodeUtil {
    //二维码颜色
    private static final int BLACK = 0xFF000000;
    //二维码颜色
    private static final int WHITE = 0xFFFFFFFF;

    /**
     * <span style="font-size:18px;font-weight:blod;">QRCode 方式生成二维码</span>
     * @param content    二维码内容
     * @param imgPath    二维码生成路径
     * @param version    二维码版本
     * @param isFlag    是否生成Logo图片    为NULL不生成
     */
    public static void QRCodeCreate(String content, String imgPath, int version, String logoPath){
        try {
            Qrcode qrcodeHandler = new Qrcode();
            //设置二维码排错率，可选L(7%) M(15%) Q(25%) H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
            qrcodeHandler.setQrcodeErrorCorrect('M');
            //N代表数字,A代表字符a-Z,B代表其他字符
            qrcodeHandler.setQrcodeEncodeMode('B');
            //版本1为21*21矩阵，版本每增1，二维码的两个边长都增4；所以版本7为45*45的矩阵；最高版本为是40，是177*177的矩阵
            qrcodeHandler.setQrcodeVersion(version);
            //根据版本计算尺寸
            int imgSize = 67 + 12 * (version - 1) ;
            byte[] contentBytes = content.getBytes("UTF-8");
            BufferedImage bufImg = new BufferedImage(imgSize , imgSize ,BufferedImage.TYPE_INT_RGB);
            Graphics2D gs = bufImg.createGraphics();
            gs.setBackground(Color.WHITE);
            gs.clearRect(0, 0, imgSize , imgSize);
            // 设定图像颜色 > BLACK
            gs.setColor(Color.BLACK);
            // 设置偏移量 不设置可能导致解析出错
            int pixoff = 2;
            // 输出内容 > 二维码
            if (contentBytes.length > 0 && contentBytes.length < 130) {
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
                for (int i = 0; i < codeOut.length; i++) {
                    for (int j = 0; j < codeOut.length; j++) {
                        if (codeOut[j][i]) {
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                        }
                    }
                }
            } else {
                System.err.println("QRCode content bytes length = " + contentBytes.length + " not in [ 0,130 ]. ");
            }
           /* 判断是否需要添加logo图片 */
            if(logoPath != null){
                File icon = new File(logoPath);
                if(icon.exists()){
                    int width_4 = imgSize / 4;
                    int width_8 = width_4 / 2;
                    int height_4 = imgSize / 4;
                    int height_8 = height_4 / 2;
                    Image img = ImageIO.read(icon);
                    gs.drawImage(img, width_4 + width_8, height_4 + height_8,width_4,height_4, null);
                    gs.dispose();
                    bufImg.flush();
                }else{
                    System.out.println("Error: login图片不存在！");
                }

            }


            gs.dispose();
            bufImg.flush();
            //创建二维码文件
            File imgFile = new File(imgPath);
            if(!imgFile.exists())
                imgFile.createNewFile();
            //根据生成图片获取图片
            String imgType = imgPath.substring(imgPath.lastIndexOf(".") + 1, imgPath.length());
            // 生成二维码QRCode图片
            ImageIO.write(bufImg, imgType, imgFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
