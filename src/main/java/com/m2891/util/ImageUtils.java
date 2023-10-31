package com.m2891.util;

import com.m2891.util.id.ThreadLocalRandomIdGenerator;
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class ImageUtils {

    private static final Random RANDOM = ThreadLocalRandom.current();

    public static String buildImage(String code) throws IOException {
        int width = 130;
        int height = 40;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 36));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = RANDOM.nextInt(width);
            int y = RANDOM.nextInt(height);
            int xl = RANDOM.nextInt(12);
            int yl = RANDOM.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        for (int i = 0; i < code.toCharArray().length; i++) {
            g.setColor(new Color(20 + RANDOM.nextInt(110), 20 + RANDOM.nextInt(110), 20 + RANDOM.nextInt(110)));
            g.drawString(String.valueOf(code.charAt(i)), 18 * i + 12, 30);
        }
        g.dispose();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", out);
        return "data:image/jpg;base64,".concat(new Base64().encodeToString(out.toByteArray()));
    }

    private static Color getRandColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + RANDOM.nextInt(bc - fc);
        int g = fc + RANDOM.nextInt(bc - fc);
        int b = fc + RANDOM.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}