package com.mr.view;

/**
 * @Description:
 * @Author: �Ƽ���
 * @CreateDate�� 2022/6/9-17:44
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

// ��������
public class BackGroundImage {
    public BufferedImage image; // ����ͼƬ
    private BufferedImage image1, image2; // ����������ͼƬ
    private Graphics2D g; // ����ͼƬ�Ļ�ͼ����
    public int x1, x2; // ��������ͼƬ������
    public static final int SPEED = 4; // �����ٶ�

    public BackGroundImage() {
        try {
            image1 = ImageIO.read(new File("image/����.png"));
            image2 = ImageIO.read(new File("image/����.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ��ͼƬ���ÿ�800��300�Ĳ�ɫͼƬ
        image = new BufferedImage(800, 300, BufferedImage.TYPE_INT_RGB);
        g = image.createGraphics(); // ��ȡ��ͼƬ��ͼ����
        x1 = 0; // ��һ��ͼƬ��ʼ����Ϊ0
        x2 = 800; // �ڶ���ͼƬ��ʼ������Ϊ800
        g.drawImage(image1, x1, 0, null);
    }

    // ����
    public void roll() {
        x1 -= SPEED; // ��һ��ͼƬ����
        x2 -= SPEED; // �ڶ���ͼƬ����
        if (x1 <= -800) { // �����һ��ͼƬ�Ƴ���Ļ
            x1 = 800; // �ص���Ļ�Ҳ�
        }
        if (x2 <= -800) { // ����ڶ���ͼƬ�Ƴ���Ļ
            x2 = 800; // �ص���Ļ�Ҳ�
        }
        g.drawImage(image1, x1, 0, null); // ����ͼƬ�л�������ͼƬ
        g.drawImage(image2, x2, 0, null);
    }
}


