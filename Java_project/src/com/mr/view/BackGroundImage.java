package com.mr.view;

/**
 * @Description:
 * @Author: 黄嘉宇
 * @CreateDate： 2022/6/9-17:44
 * @UpdataUser:
 * @UpdataDate: 2022/6/9-17:44
 * @UpdataRemark:
 * @Version: 1.0
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// 滚动背景
public class BackGroundImage {
    public BufferedImage image; // 背景图片
    private BufferedImage image1, image2; // 滚动的两个图片
    private Graphics2D g; // 背景图片的绘图对象
    public int x1, x2; // 两个滚动图片的坐标
    public static final int SPEED = 4; // 滚动速度

    public BackGroundImage() {
        try {
            image1 = ImageIO.read(new File("image/背景.png"));
            image2 = ImageIO.read(new File("image/背景.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 主图片采用宽800高300的彩色图片
        image = new BufferedImage(800, 300, BufferedImage.TYPE_INT_RGB);
        g = image.createGraphics(); // 获取主图片绘图对象
        x1 = 0; // 第一幅图片初始坐标为0
        x2 = 800; // 第二幅图片初始横坐标为800
        g.drawImage(image1, x1, 0, null);
    }

    // 滚动
    public void roll() {
        x1 -= SPEED; // 第一幅图片左移
        x2 -= SPEED; // 第二幅图片左移
        if (x1 <= -800) { // 如果第一幅图片移出屏幕
            x1 = 800; // 回到屏幕右侧
        }
        if (x2 <= -800) { // 如果第二幅图片移出屏幕
            x2 = 800; // 回到屏幕右侧
        }
        g.drawImage(image1, x1, 0, null); // 在主图片中绘制两幅图片
        g.drawImage(image2, x2, 0, null);
    }
}


