package com.mr.service;

/**
 * @Description:
 * @Author: 黄嘉宇
 * @CreateDate： 2022/6/9-17:43
 * @UpdataUser:
 * @UpdataDate: 2022/6/9-17:43
 * @UpdataRemark:
 * @Version: 1.0
 */
import java.io.FileNotFoundException;

// 音效类
public class Sound {
    // 音乐文件夹
    public static final String DIR = "music\\";
    // 背景音乐
    public static final String BACKGROUND = "background.wav";
    // 跳跃音效
    public static final String JUMP = "jump.wav";
    // 撞击音效
    public static final String HIT = "hit.wav";

    // flag是否播放mp3格式音频
    public static void play(String file,boolean circulate){
        MusicPlayer player = null;
        try {
            // 创建播放器
            player = new MusicPlayer(file, circulate);
            // 播放器开始播放
            player.play();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void jump(){
        // 播放跳跃音效（不循环播放）
        play(DIR + JUMP,false);
    }

    public static void hit(){
        // 播放第撞击音效（不循环播放）
        play(DIR + HIT, false);
    }

    public static void background(){
        // 播放背景音效（循环播放）
        play(DIR + BACKGROUND,true);
    }
}

