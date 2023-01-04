package com.mr.service;

/**
 * @Description:
 * @Author: �Ƽ���
 * @CreateDate�� 2022/6/9-17:43
 * @UpdataUser:
 * @UpdataDate: 2022/6/9-17:43
 * @UpdataRemark:
 * @Version: 1.0
 */
import java.io.FileNotFoundException;

// ��Ч��
public class Sound {
    // �����ļ���
    public static final String DIR = "music\\";
    // ��������
    public static final String BACKGROUND = "background.wav";
    // ��Ծ��Ч
    public static final String JUMP = "jump.wav";
    // ײ����Ч
    public static final String HIT = "hit.wav";

    // flag�Ƿ񲥷�mp3��ʽ��Ƶ
    public static void play(String file,boolean circulate){
        MusicPlayer player = null;
        try {
            // ����������
            player = new MusicPlayer(file, circulate);
            // ��������ʼ����
            player.play();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void jump(){
        // ������Ծ��Ч����ѭ�����ţ�
        play(DIR + JUMP,false);
    }

    public static void hit(){
        // ���ŵ�ײ����Ч����ѭ�����ţ�
        play(DIR + HIT, false);
    }

    public static void background(){
        // ���ű�����Ч��ѭ�����ţ�
        play(DIR + BACKGROUND,true);
    }
}

