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
    public AbstractAircraft creatEnemy(double magnification) {
        int eliteHp = 100;
        int eliteSpeedY = 5;
        EliteEnemy eliteEnemy = new EliteEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.BOSS_ENEMY_IMAGE.getWidth())) * 1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2) * 1,
                (int) ((Math.random() - 0.5) * 10 * magnification) * 1,
                (int) (eliteSpeedY * magnification) * 1,
                (int) (eliteHp * magnification) * 1
        );
        eliteEnemy.setStrategy(new EnemtStraightShoot());
        return eliteEnemy;
    }
}
