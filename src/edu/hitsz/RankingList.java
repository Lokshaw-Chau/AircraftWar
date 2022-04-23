package edu.hitsz;

import edu.hitsz.application.EasyGame;
import edu.hitsz.application.NormalGame;
import edu.hitsz.data.RecordDaompl;
import edu.hitsz.data.TableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RankingList {
    public JPanel mainPanel;
    private JTable rankTable;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JButton deleteButton;
    private JScrollPane scrollTable;
    private JLabel mode;

    public RankingList() {
        DefaultTableModel model = TableModel.getInstance();
        rankTable.setModel(model);
        scrollTable.setViewportView(rankTable);
        String gameMode;
        if (StartPanel.getGame() instanceof EasyGame) {
            gameMode = "EASY";
        } else if (StartPanel.getGame() instanceof NormalGame) {
            gameMode = "MEDIUM";

        } else {
            gameMode = "HARD";
        }
        mode.setText("难度：" + gameMode);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = rankTable.getSelectedRow();
                if (row != -1) {
                    TableModel.getInstance().removeRow(row);
                    try {
                        RecordDaompl.getInstance().deleteByRow(row);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}
