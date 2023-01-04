package com.mr.view;

/**
 * @Description:
 * @Author: 黄嘉宇
 * @CreateDate： 2022/6/9-17:48
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

// 主窗体
public class MainFrame extends JFrame {

    public MainFrame() {
        restart(); // 开始
        setBounds(340, 150, 821, 260); // 设置横纵坐标和宽高
        setTitle("奔跑吧！小恐龙！"); // 标题
        Sound.background(); // 播放背景音乐
        ScoreRecorder.init(); // 读取得分记录
        addListener(); // 添加监听
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 关闭窗体则停止程序
    }

    // 重新开始
    public void restart() {
        Container c = getContentPane(); // 获取主容器对象
        c.removeAll(); // 删除容器中所有组件
        GamePanel panel = new GamePanel(); // 创建新的游戏面板
        c.add(panel);
        addKeyListener(panel); // 添加键盘事件
        c.validate(); // 容器重新验证所有组件
    }

    // 添加监听
    private void addListener() {
       addWindowListener(new WindowAdapter() {
           @Override
           public void windowClosing(WindowEvent e) {
               ScoreRecorder.saveScore();
           }
       });
    }
}


