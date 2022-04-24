package edu.hitsz;

import edu.hitsz.application.EasyGame;
import edu.hitsz.application.NormalGame;
import edu.hitsz.data.HistoryRecord;
import edu.hitsz.data.RecordDaompl;

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
        DefaultTableModel model = new DefaultTableModel(dataProcess(RecordDaompl.getInstance()), getColumnName());
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
                    try {
                        RecordDaompl.getInstance().deleteByRow(row);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    model.setDataVector(dataProcess(RecordDaompl.getInstance()), getColumnName());
                }
            }
        });
    }

    private static String[][] dataProcess(RecordDaompl recordDaompl) {
        int recordNum = recordDaompl.getAll().size();
        if (recordNum == 0) {
            return null;
        }
        String[][] data = new String[recordNum][4];
        int i = 0;
        for (HistoryRecord item : recordDaompl.getAll()) {
            data[i][0] = Integer.toString(item.getRank());
            data[i][1] = item.getPlayerid();
            data[i][2] = Integer.toString(item.getScore());
            data[i][3] = item.getTime();
            i++;
        }
        return data;
    }

    private static String[] getColumnName() {
        String[] columnName = {"排名", "ID", "得分", "时间"};
        return columnName;
    }
}
