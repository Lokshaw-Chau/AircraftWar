package edu.hitsz.aircraft;

/**
 * 所有敌机工厂类的接口
 *
 * @author Lau-Shaw Chau
 * @date 2022/04/04
 */
public interface EnemyFactory {
    /**
     * 创建一个敌机对象
     *
     * @param magnification 敌机参数增强的倍率
     * @return AbstractAircraft 敌机对象
     */
    public AbstractAircraft creatEnemy(double magnification);
}
