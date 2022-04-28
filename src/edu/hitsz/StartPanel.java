package edu.hitsz;

import edu.hitsz.application.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * 选择难度的图形界面
 *
 * @author ZLX
 * @date 2022/4/28
 */
public class StartPanel {
    private static AbstractGame game;
    private static boolean musicOn = true;
    public JPanel mainPanel;
    private JCheckBox musicCheckBox;
    private JButton easyModeButton;
    private JButton normalModeButton;
    private JButton hardModeButton;
    private JPanel topPanel;

    public StartPanel() {
        easyModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (Main.GAMELOCK) {
                    game = new EasyGame();
                    Main.GAMELOCK.notify();
                }
            }
        });
        normalModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (Main.GAMELOCK) {
                    game = new NormalGame();
                    Main.GAMELOCK.notify();
                }
            }
        });
        hardModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (Main.GAMELOCK) {
                    game = new HardGame();
                    Main.GAMELOCK.notify();
                }
            }
        });
        musicCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                musicOn = false;
            }
        });
    }

    public static boolean isMusicOn() {
        return musicOn;
    }

    public static AbstractGame getGame() {
        return game;
    }

}
