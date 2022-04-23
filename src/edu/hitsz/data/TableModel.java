package edu.hitsz.data;

import javax.swing.table.DefaultTableModel;

/**
 * @author ZLX
 */
public class TableModel extends DefaultTableModel {
    private static TableModel tableModel = new TableModel(TableModel.dataProcess(RecordDaompl.getInstance()), TableModel.getColumnName());

    private TableModel(String[][] data, String[] columnName) {
        super(data, columnName);
    }

    private static String[][] dataProcess(RecordDaompl recordDaompl) {
        int recordNum = recordDaompl.getAll().size();
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

    public static TableModel getInstance() {
        return tableModel;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
