package com.mr.service;

/**
 * @Description:
 * @Author: �Ƽ���
 * @CreateDate�� 2022/6/9-17:41
 * @UpdataUser:
 * @UpdataDate: 2022/6/9-17:41
 * @UpdataRemark:
 * @Version: 1.0
 */
import com.mr.view.GamePanel;
import com.mr.view.MainFrame;
import com.mr.view.ScoreDialog;

import java.awt.*;

// ˢ��֡�߳���
public class FreshThread extends Thread{
    public static final int FREASH = 20;
    GamePanel p; // ��Ϸ���

    public FreshThread(GamePanel p) {
        this.p = p;
    }

    public void run() {
        while (!p.isFinish()) { // �����Ϸδ����
            p.repaint(); // �ػ���Ϸ���
            try {
                Thread.sleep(FREASH); // ����ˢ��ʱ������
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Container c = p.getParent(); // ��ȡ��常����
        while (!(c instanceof MainFrame)) { // ���������������������
            c = c.getParent(); // ������ȡ�������ĸ�����
        }
        MainFrame frame = (MainFrame) c; // ������ǿ��ת��Ϊ��������
        new ScoreDialog(frame); // �����÷ּ�¼�Ի���
        frame.restart(); // ���������ؿ�ʼ��Ϸ
    }
}

