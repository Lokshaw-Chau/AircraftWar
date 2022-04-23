package edu.hitsz;

import edu.hitsz.application.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel {
    private static Game game;
    private static boolean musicOn = true;
    public JPanel MainPanel;
    private JCheckBox MusicCheckBox;
    private JButton EasyModeButton;
    private JButton NormalModeButton;
    private JButton HardModeButton;
    private JPanel TopPanel;

    public StartPanel() {
        EasyModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (Main.GAMELOCK) {
                    game = new EasyGame();
                    Main.GAMELOCK.notify();
                }
            }
        });
        NormalModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (Main.GAMELOCK) {
                    game = new NormalGame();
                    Main.GAMELOCK.notify();
                }
            }
        });
        HardModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (Main.GAMELOCK) {
                    game = new HardGame();
                    Main.GAMELOCK.notify();
                }
            }
        });
        MusicCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                musicOn = false;
            }
        });
    }

    public static boolean isMusicOn() {
        return musicOn;
    }

    public static Game getGame() {
        return game;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}
