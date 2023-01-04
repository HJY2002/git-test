package com.mr.modle;

/**
 * @Description:
 * @Author: �Ƽ���
 * @CreateDate�� 2022/6/9-17:39
 * @UpdataUser:
 * @UpdataDate: 2022/6/9-17:39
 * @UpdataRemark:
 * @Version: 1.0
 */
import com.mr.service.FreshThread;
import com.mr.service.Sound;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// ������
public class Dinosaur {
    public BufferedImage image; // ��ͼƬ
    private BufferedImage image1, image2, image3; // �ܲ�ͼƬ
    public int x, y; // ����
    private int jumpValue = 0; // ��Ծ��������
    private boolean jumpState = false; // ��Ծ״̬
    private int stepTimer = 0; // ̤����ʱ��
    private final int JUMP_HIGHT = 100; // �������߶�
    private final int LOWEST_Y = 120; // ����������
    private final int FREASH = FreshThread.FREASH; // ˢ��ʱ��--ˢ��֡�߳�

    public Dinosaur() {
        x = 50;         // �����ĺ�����
        y = LOWEST_Y;   // ������������
        try {
            image1 = ImageIO.read(new File("image/����1.png")); // ��ȡ������ͼƬ
            image2 = ImageIO.read(new File("image/����2.png"));
            image3 = ImageIO.read(new File("image/����3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ̤��
    private void step() {
        // ÿ��250���룬����һ��ͼƬ����Ϊ����3ͼƬ�����Գ���3ȡ�࣬����չʾ������
        int tmp = stepTimer / 250 % 3;
        switch (tmp) {
            case 1 :
                image = image1;
                break;
            case 2 :
                image = image2;
                break;
            default :
                image = image3;
        }
        stepTimer += FREASH; // ��ʱ������
    }

    // ��Ծ
    public void jump() {
        if (!jumpState) { // ���û������Ծ״̬
            Sound.jump(); // ������Ծ��Ч
        }
        jumpState = true; // ������Ծ״̬
    }

    // �ƶ�
    public void move() {
        step(); // ����̤��
        if (jumpState) { // ���������Ծ
            if (y == LOWEST_Y) { // �����������ڵ�����͵�---��Խ��������ԽС��
                jumpValue = -4; // ������Ϊ��ֵ--������
            }
            if (y <= LOWEST_Y - JUMP_HIGHT) {// ���������ߵ�
                jumpValue = 4; // ������Ϊ��ֵ--������
            }
            y += jumpValue; // �����귢���仯
            if (y == LOWEST_Y) { // ����ٴ����
                jumpState = false; // ֹͣ��Ծ
            }
        }
    }

    public Rectangle getFootBounds() { // ��ȡ�����ĽŲ��߽����

        return new Rectangle(x + 30, y + 59, 29, 18); // ���ں�������ײ���
    }

    public Rectangle getHeadBounds() { // ��ȡ������ͷ���߽����
        return new Rectangle(x + 66, y + 25, 32, 22);
    }

}


