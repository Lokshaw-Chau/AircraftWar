package edu.hitsz.data;

import java.io.IOException;
import java.util.List;

public interface RecordDao {

    public List<HistoryRecord> getAll();

    public void addRecord(HistoryRecord historyRecord);

    public void deleteByRow(int rank) throws IOException;

    public void sortByScore();

    public void writeToFile() throws IOException;

    public HistoryRecord makeRecord(int score, String ID);

}
