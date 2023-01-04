package com.mr.modle;

/**
 * @Description:
 * @Author: �Ƽ���
 * @CreateDate�� 2022/6/9-17:40
 * @UpdataUser:
 * @UpdataDate: 2022/6/9-17:40
 * @UpdataRemark:
 * @Version: 1.0
 */
import com.mr.view.BackGroundImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

// �ϰ���
public class Obstacle {
    public int x, y; // ��������
    public BufferedImage image;
    private BufferedImage stone; // ʯͷͼƬ---(32,26)
    private BufferedImage cacti; // ������ͼƬ---(32,59)
    private int speed; // �ƶ��ٶ�--ͼƬ���ű�����

    public Obstacle() {
        try {
            stone = ImageIO.read(new File("image/ʯͷ.png")); // ʯͷͼƬ
            cacti = ImageIO.read(new File("image/������.png")); // ������ͼƬ
        } catch (IOException e) {
            e.printStackTrace();
        }
        Random r = new Random(); // �����������
        if (r.nextInt(2) == 0) { // ��0��1��ȡһֵ����Ϊ0
            image = cacti; // ����������ͼƬ
        } else {
            image = stone; // ����ʯͷͼƬ
        }
        x = 800; // ��ʼ������
        y = 200 - image.getHeight(); // ������--ʹͼƬ���ڵ�ƽ����
        speed = BackGroundImage.SPEED; // �ƶ��ٶ��뱳��ͬ��--BackgroundImage=����
    }

    // �ƶ�
    public void move() {
        x -= speed; // ������ݼ�--�ϰ�����ٶ��뱳�����ٶ�һ��
    }

    public boolean isLive() {
        // ����Ƴ�����Ϸ����
        if (x <= -image.getWidth()) {
            return false; // ����
        }
        return true; // ���
    }

    // ͨ��getBounds()�������ر߽����
    public Rectangle getBounds() {
        if (image == cacti) { // ���ʹ��������ͼƬ
            // ���������Ƶı߽�
            return new Rectangle(x + 7, y, 15, image.getHeight());
        }
        // ����ʯͷ�ı߽�
        return new Rectangle(x + 5, y + 4, 23, 21);
    }

}


