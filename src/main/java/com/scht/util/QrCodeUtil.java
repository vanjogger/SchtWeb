package com.scht.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/12.
 */
public class QrCodeUtil {

    private static int defaultWidth = 300;
    private static int defaultHeight = 300;
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    public QrCodeUtil(int width,int height){
        this.defaultWidth = width;
        this.defaultHeight = height;
    }

    public static void encodeQrCode(String content,HttpServletResponse response) throws Exception {
        if(!StringUtil.isNotNull(content))
            return ;
        MultiFormatWriter writer = new MultiFormatWriter();
        Map hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        BitMatrix matrix = null;

        matrix = writer.encode(content, BarcodeFormat.QR_CODE,defaultWidth,defaultHeight,hints);
        BufferedImage img = toBufferedImage(matrix);
        ImageIO.write(img,"png",response.getOutputStream());
    }

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

}
