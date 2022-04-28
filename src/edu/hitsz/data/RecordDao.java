package edu.hitsz.data;

import java.io.IOException;
import java.util.List;
/**
 * 数据访问模式的接口
 *
 * @author ZLX
 * @date 2022/4/28
 */
public interface RecordDao {
    /**
     * 返回所有历史记录对象数组
     *
     * @return java.util.List<edu.hitsz.data.HistoryRecord>
     * @author ZLX
     * @date 2022/4/28
     */
    public List<HistoryRecord> getAll();

    /**
     * 向类中保存历史记录对象的数组中添加历史记录
     *
     * @param historyRecord 历史记录对象
     * @author ZLX
     * @date 2022/4/28
     */
    public void addRecord(HistoryRecord historyRecord);

    /**
     * 通过记录的排名来删除某一记录
     *
     * @param rank 该记录的排名的值
     * @throws IOException 删除后与文件同步时，没有打开文件
     */
    public void deleteByRow(int rank) throws IOException;

    /**
     * 通过得分对历史记录对象排序
     */
    public void sortByScore();

    /**
     * 将保存的历史记录写入csv文件中
     *
     * @throws IOException 没有打开要写入的文件
     */
    public void writeToFile() throws IOException;

    /**
     * 产生一个游戏记录的对象
     *
     * @param score 游戏的分数
     * @param id    玩家的id
     * @return edu.hitsz.data.HistoryRecord
     */
    public HistoryRecord makeRecord(int score, String id);

}
