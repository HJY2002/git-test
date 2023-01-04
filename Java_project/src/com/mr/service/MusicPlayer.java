package com.mr.service;

/**
 * @Description:
 * @Author: 黄嘉宇
 * @CreateDate： 2022/6/9-17:41
 * @UpdataUser:
 * @UpdataDate: 2022/6/9-17:41
 * @UpdataRemark:
 * @Version: 1.0
 */
import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// 音乐播放器类
public class MusicPlayer implements Runnable {
    // 音乐文件
    File soundFile;
    // 父线程
    Thread thread;
    // 是否循环播放
    boolean circulate;

    public MusicPlayer(String filepath,boolean circulate) throws FileNotFoundException {
        this.circulate = circulate;
        soundFile = new File(filepath);
        if(!soundFile.exists()){ // 如果文件不存在
            throw  new FileNotFoundException(filepath + "未找到");
        }
    }

    @Override
    public void run() {
        byte[] auBuffer = new byte[1024 * 128]; // 创建128k缓冲区
        do{
            AudioInputStream audioInputStream = null; // 创建音频输入流对象
            SourceDataLine auline = null; // 混频器源数据行
            try {
                // 从音乐文件中获取音频输入流
                audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                AudioFormat format = audioInputStream.getFormat(); // 获取音频格式
                // 按照源数据行类型和指定音频格式创建数据行对象
                DataLine.Info info = new DataLine.Info(SourceDataLine.class,
                        format);
                // 利用音频系统类获得与指定 Line.Info 对象中的描述匹配的行，并转换为源数据行对象
                auline = (SourceDataLine) AudioSystem.getLine(info);
                auline.open(format); // 按照指定格式打开源数据行
                auline.start(); // 源数据行开启读写活动
                int byteCount = 0; // 记录音频输入流读出的字节数
                while (byteCount != -1) { // 如果音频输入流中读取的字节数不为-1
                    // 从音频数据流中读出128K的数据
                    byteCount = audioInputStream.read(auBuffer, 0,
                            auBuffer.length);
                    if (byteCount >= 0) { // 如果读出有效数据
                        auline.write(auBuffer, 0, byteCount); // 将有效数据写入数据行中
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } finally {
                auline.drain(); // 清空数据行
                auline.close(); // 关闭数据行
            }
        }while (circulate); // 根据循环标志判断是否循环播放
    }

    public void play(){
        // 创建线程对象
        thread = new Thread(this);
        // 开启线程
        thread.start();
    }

    public void stop(){
        // 强制关闭线程
        thread.stop();
    }
}


