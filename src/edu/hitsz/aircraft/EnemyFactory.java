package edu.hitsz.aircraft;

/**
 * EnemyFactory
 *
 * @author Lau-Shaw Chau
 * @date 2022/04/04
 */
public interface EnemyFactory {
    /**
     * 创建一个敌机对象
     *
     * @return AbstractAircraft 敌机对象
     */
    public AbstractAircraft creatEnemy(double magnification);
}
