package edu.hitsz.data;

import edu.hitsz.StartPanel;
import edu.hitsz.application.EasyGame;
import edu.hitsz.application.NormalGame;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
/**
 * 访问历史游戏记录
 *
 * @author ZLX
 * @date 2022/4/28
 */
public class RecordDaompl implements RecordDao {
    private static RecordDaompl recordDaompl;

    static {
        try {
            recordDaompl = new RecordDaompl();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final File file;
    private List<HistoryRecord> historyRecords;

    private RecordDaompl() throws IOException {
        historyRecords = new ArrayList<HistoryRecord>();
        Path path;
        if (StartPanel.getGame() instanceof EasyGame) {
            path = Paths.get("historyrecord_easy.csv");
        } else if (StartPanel.getGame() instanceof NormalGame) {
            path = Paths.get("historyrecord_nomal.csv");
        } else {
            path = Paths.get("historyrecord_hard.csv");
        }
        file = path.toFile();
        if (!file.isFile()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        //从文件里读入历史记录，这样才能保持一个与客户端无关榜单
        try (BufferedReader br = Files.newBufferedReader(path)) {
            // CSV文件的分隔符
            String delimiter = ",";
            // 按行读取
            String line;
            while ((line = br.readLine()) != null) {
                // 分割
                String[] columns = line.split(delimiter);
                // 读入数据
                HistoryRecord historyRecord = new HistoryRecord();
                historyRecord.setRank(Integer.parseInt(columns[0]));
                historyRecord.setPlayid(columns[1]);
                historyRecord.setScore(Integer.parseInt(columns[2]));
                historyRecord.setTime(columns[3]);
                historyRecords.add(historyRecord);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static RecordDaompl getInstance() {
        return recordDaompl;
    }

    @Override
    public List<HistoryRecord> getAll() {
        return historyRecords;
    }

    @Override
    public void addRecord(HistoryRecord historyRecord) {
        historyRecords.add(historyRecord);
    }

    @Override
    public void deleteByRow(int row) throws IOException {
        historyRecords.removeIf(item -> item.getRank() == row + 1);
        sortByScore();
        writeToFile();
    }

    @Override
    public void sortByScore() {
        Collections.sort(historyRecords, (o1, o2) -> o2.getScore() - o1.getScore());
        int i = 1;
        for (HistoryRecord item : historyRecords) {
            item.setRank(i++);
        }
    }

    @Override
    public void writeToFile() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (HistoryRecord item : historyRecords) {
                writer.write(item.getRank() + "," + item.getPlayerid() + "," + item.getScore() + "," + item.getTime());
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public HistoryRecord makeRecord(int score, String id) {
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setPlayid(id);
        historyRecord.setScore(score);
        Date date = new Date();
        var simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        historyRecord.setTime(simpleDateFormat.format(date));
        return historyRecord;
    }
}
