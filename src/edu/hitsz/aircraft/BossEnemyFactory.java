package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.strategy.EnemyScratteredShoot;

/**
 * BossEnemyFactory
 *
 * @author Lau-Shaw Chau
 * @date 2022/04/04
 */
public class BossEnemyFactory implements EnemyFactory {
    @Override
    public AbstractAircraft creatEnemy(double magnification) {
        int bossHp = 2000;
        BossEnemy bossEnemy = new BossEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.BOSS_ENEMY_IMAGE.getWidth())) * 1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2) * 1,
                (int) ((Math.random() - 0.5) * 10 * magnification) * 1,
                0,
                (int) (bossHp * magnification) * 1
        );
        bossEnemy.setStrategy(new EnemyScratteredShoot());
        return bossEnemy;
    }

}
