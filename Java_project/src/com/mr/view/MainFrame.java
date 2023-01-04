package com.mr.view;

/**
 * @Description:
 * @Author: �Ƽ���
 * @CreateDate�� 2022/6/9-17:48
 * @UpdataUser:
 * @UpdataDate: 2022/6/9-17:48
 * @UpdataRemark:
 * @Version: 1.0
 */
import com.mr.service.ScoreRecorder;
import com.mr.service.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// ������
public class MainFrame extends JFrame {

    public MainFrame() {
        restart(); // ��ʼ
        setBounds(340, 150, 821, 260); // ���ú�������Ϳ��
        setTitle("���ܰɣ�С������"); // ����
        Sound.background(); // ���ű�������
        ScoreRecorder.init(); // ��ȡ�÷ּ�¼
        addListener(); // ��Ӽ���
        setDefaultCloseOperation(EXIT_ON_CLOSE); // �رմ�����ֹͣ����
    }

    // ���¿�ʼ
    public void restart() {
        Container c = getContentPane(); // ��ȡ����������
        c.removeAll(); // ɾ���������������
        GamePanel panel = new GamePanel(); // �����µ���Ϸ���
        c.add(panel);
        addKeyListener(panel); // ��Ӽ����¼�
        c.validate(); // ����������֤�������
    }

    // ��Ӽ���
    private void addListener() {
       addWindowListener(new WindowAdapter() {
           @Override
           public void windowClosing(WindowEvent e) {
               ScoreRecorder.saveScore();
           }
       });
    }
}


