package edu.hitsz.supply;

import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;

/**
 * 所有补给类的抽象父类
 *
 * @author Lau-Shaw Chau
 * @date 2022/04/04
 */
public abstract class AbstractSupply extends AbstractFlyingObject {

    public AbstractSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    /**
     * 对传入的heroAircraft变量产生补给的效果
     */
    abstract public void takeEffect();

    @Override
    public void forward() {
        super.forward();

        // 判定 x 轴出界
        if (locationX <= 0 || locationX >= Main.WINDOW_WIDTH) {
            vanish();
        }

        // 判定 y 轴出界
        if (speedY > 0 && locationY >= Main.WINDOW_HEIGHT) {
            // 向下飞行出界
            vanish();
        } else if (locationY <= 0) {
            // 向上飞行出界
            vanish();
        }
    }
}
