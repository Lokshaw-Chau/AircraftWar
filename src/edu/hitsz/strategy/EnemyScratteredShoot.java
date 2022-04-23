package edu.hitsz.strategy;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;

import java.util.LinkedList;
import java.util.List;

public class EnemyScratteredShoot implements Strategy {

    @Override
    public List<BaseBullet> doShoot(int locationX, int locationY, int speed, int direction, int shootNum, int power) {
        List<BaseBullet> res = new LinkedList<>();
        int x = locationX;
        int y = locationY + direction * 2;
        int speedX = (int) ((Math.random() - 0.5) * 10) * 1;
        int speedY = speed + direction * 5;
        BaseBullet baseBullet;
        for (int i = 0; i < shootNum; i++) {
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            baseBullet = new EnemyBullet(x + (i * 2 - shootNum + 1) * 10, y, speedX + (i * 2 - shootNum + 1) * 2, speedY, power);
            res.add(baseBullet);
        }
        return res;
    }
}
