package edu.hitsz.application;

import edu.hitsz.RankingList;
import edu.hitsz.StartPanel;
import edu.hitsz.data.RecordDaompl;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * 程序入口
 *
 * @author hitsz
 */
public class Main {

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;
    public static final Object GAMELOCK = new Object();
    public static final Object RANKLOCK = new Object();

    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("Hello Aircraft War");

        // 获得屏幕的分辨率，初始化 Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Aircraft War");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        //设置窗口的大小和位置,居中放置
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StartPanel startPanel = new StartPanel();
        //打开起始页
        frame.add(startPanel.MainPanel);
        frame.setVisible(true);
        Game game;
        //选择难度后进入游戏
        synchronized (Main.GAMELOCK) {
            GAMELOCK.wait();
            frame.getContentPane().removeAll();
            game = StartPanel.getGame();
            frame.add(game);
            frame.setVisible(true);
            game.action();
        }
        //游戏结束后获取分数进入排行榜
        synchronized (Main.RANKLOCK) {
            RANKLOCK.wait();
            int score = game.getScore();
            //维护数据
            //创建访问记录的对象
            RecordDaompl recordDaompl = RecordDaompl.getInstance();
            //制作本次游戏的记录并将本次记录添加到历史记录中
            String prompt = "你本次的成绩是" + score + "，请输入你的用户名以保存记录（请不要输入\",\"\"\\n\"等字符）";
            String ID = JOptionPane.showInputDialog(null, prompt, "Default");
            recordDaompl.addRecord(recordDaompl.makeRecord(score, ID));
            //进行排序
            recordDaompl.sortByScore();
            RankingList rankingList = new RankingList();
            frame.getContentPane().removeAll();
            frame.add(rankingList.mainPanel);
            frame.setVisible(true);
            recordDaompl.writeToFile();
        }
    }
}
