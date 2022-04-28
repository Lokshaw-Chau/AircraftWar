package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.strategy.Strategy;

import java.util.List;

/**
 * 精英机
 *
 * @author Lau-Shaw Chau
 * @date 2022/04/04
 */
public class EliteEnemy extends AbstractAircraft {
    /** 攻击方式 */
    /**
     * 子弹一次发射数量
     */
    private int shootNum = 1;
    /**
     * 子弹伤害
     */
    private int power = 30;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = 1;
    private Strategy strategy;

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT) {
            vanish();
        }
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public List<BaseBullet> shoot() {
        return strategy.doShoot(
                this.getLocationX(),
                this.getLocationY(),
                this.getSpeedY(),
                direction,
                shootNum,
                power
        );
    }

    @Override
    public void explode() {
        vanish();
        HeroAircraft.getInstance().addScore(30);
    }
}
