package edu.hitsz.strategy;

import edu.hitsz.bullet.BaseBullet;

import java.util.List;
/**
 * 所有策略的接口
 *
 * @author ZLX
 * @date 2022/4/28
 */
public interface Strategy {
    /**
     * 调用不同的策略射击
     *
     * @param locationX 子弹产生的x坐标
     * @param locationY 子弹产生的y坐标
     * @param speed     子弹的y轴方向的速度
     * @param direction 子弹的方向（-1向上，1向下）
     * @param shootNum  每次发射子弹的数目
     * @param power     子弹的威力
     * @return java.util.List<edu.hitsz.bullet.BaseBullet>
     */
    public List<BaseBullet> doShoot(int locationX, int locationY, int speed, int direction, int shootNum, int power);

}
