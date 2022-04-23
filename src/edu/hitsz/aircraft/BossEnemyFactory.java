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
    public AbstractAircraft creatEnemy() {
        BossEnemy bossEnemy = new BossEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.BOSS_ENEMY_IMAGE.getWidth())) * 1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2) * 1,
                (int) ((Math.random() - 0.5) * 10) * 1,
                0,
                2000);
        bossEnemy.setStrategy(new EnemyScratteredShoot());
        return bossEnemy;
    }

}
