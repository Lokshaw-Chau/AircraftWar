package edu.hitsz.strategy;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;
/**
 * 英雄机的直射策略
 *
 * @author ZLX
 * @date 2022/4/28
 */
public class HeroStraightShoot implements Strategy {
    @Override
    public List<BaseBullet> doShoot(int locationX, int locationY, int speed, int direction, int shootNum, int power) {
        List<BaseBullet> res = new LinkedList<>();
        int x = locationX;
        int y = locationY + direction * 2;
        int speedX = 0;
        int speedY = speed + direction * 5;
        BaseBullet baseBullet;
        for (int i = 0; i < shootNum; i++) {
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            baseBullet = new HeroBullet(x + (i * 2 - shootNum + 1) * 10, y, speedX, speedY, power);
            res.add(baseBullet);
        }
        return res;
    }
}
