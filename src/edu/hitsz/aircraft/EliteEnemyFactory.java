package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.strategy.EnemtStraightShoot;

/**
 * EliteEnemyFactory
 *
 * @author Lau-Shaw Chau
 * @date 2022/04/04
 */
public class EliteEnemyFactory implements EnemyFactory {
    @Override
    public AbstractAircraft creatEnemy() {
        EliteEnemy eliteEnemy = new EliteEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth())) * 1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2) * 1,
                (int) ((Math.random() - 0.5) * 5) * 1,
                5,
                60
        );
        eliteEnemy.setStrategy(new EnemtStraightShoot());
        return eliteEnemy;
    }
}
