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
import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// ���ֲ�������
public class MusicPlayer implements Runnable {
    // �����ļ�
    File soundFile;
    // ���߳�
    Thread thread;
    // �Ƿ�ѭ������
    boolean circulate;

    public MusicPlayer(String filepath,boolean circulate) throws FileNotFoundException {
        this.circulate = circulate;
        soundFile = new File(filepath);
        if(!soundFile.exists()){ // ����ļ�������
            throw  new FileNotFoundException(filepath + "δ�ҵ�");
        }
    }

    @Override
    public void run() {
        byte[] auBuffer = new byte[1024 * 128]; // ����128k������
        do{
            AudioInputStream audioInputStream = null; // ������Ƶ����������
            SourceDataLine auline = null; // ��Ƶ��Դ������
            try {
                // �������ļ��л�ȡ��Ƶ������
                audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                AudioFormat format = audioInputStream.getFormat(); // ��ȡ��Ƶ��ʽ
                // ����Դ���������ͺ�ָ����Ƶ��ʽ���������ж���
                DataLine.Info info = new DataLine.Info(SourceDataLine.class,
                        format);
                // ������Ƶϵͳ������ָ�� Line.Info �����е�����ƥ����У���ת��ΪԴ�����ж���
                auline = (SourceDataLine) AudioSystem.getLine(info);
                auline.open(format); // ����ָ����ʽ��Դ������
                auline.start(); // Դ�����п�����д�
                int byteCount = 0; // ��¼��Ƶ�������������ֽ���
                while (byteCount != -1) { // �����Ƶ�������ж�ȡ���ֽ�����Ϊ-1
                    // ����Ƶ�������ж���128K������
                    byteCount = audioInputStream.read(auBuffer, 0,
                            auBuffer.length);
                    if (byteCount >= 0) { // ���������Ч����
                        auline.write(auBuffer, 0, byteCount); // ����Ч����д����������
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } finally {
                auline.drain(); // ���������
                auline.close(); // �ر�������
            }
        }while (circulate); // ����ѭ����־�ж��Ƿ�ѭ������
    }

    public void play(){
        // �����̶߳���
        thread = new Thread(this);
        // �����߳�
        thread.start();
    }

    public void stop(){
        // ǿ�ƹر��߳�
        thread.stop();
    }
}


