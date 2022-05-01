package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * 创建普通敌机的工厂
 *
 * @author Lau-Shaw Chau
 * @date 2022/04/04
 */
public class MobEnemyFactory implements EnemyFactory {

    @Override
    public AbstractAircraft creatEnemy(double magnification) {
        int mobHp = 25;
        int mobSpeedY = 10;
        return new MobEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())) * 1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2) * 1,
                0,
                (int) (mobSpeedY * magnification) * 1,
                mobHp * 1
        );
    }
}
