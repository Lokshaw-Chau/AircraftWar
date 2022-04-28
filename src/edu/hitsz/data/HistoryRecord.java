package edu.hitsz.data;
/**
 * 单次游戏的记录
 *
 * @author ZLX
 * @date 2022/4/28
 */
public class HistoryRecord {
    private int rank;
    private String playid;
    private String time;
    private int score;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setPlayid(String playid) {
        this.playid = playid;
    }

    public String getPlayerid() {
        return playid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
