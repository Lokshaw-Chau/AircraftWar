package edu.hitsz.supply;

import edu.hitsz.basic.AbstractFlyingObject;

import java.util.ArrayList;
/**
 * 炸弹生效所需的观察者模式的通知类
 *
 * @author ZLX
 * @date 2022/4/28
 */
public class BombSupplyPublisher {
    private static BombSupplyPublisher bombSupplyPublisher = new BombSupplyPublisher();
    private ArrayList<AbstractFlyingObject> flyingObjectList = new ArrayList<>();

    private BombSupplyPublisher() {
    }

    public void addFlyingObject(AbstractFlyingObject flyingObject) {
        flyingObjectList.add(flyingObject);
    }

    public void removeFlyingObject() {
        flyingObjectList.clear();
    }

    public static BombSupplyPublisher getInstance() {
        return bombSupplyPublisher;
    }

    public void induceExplosion() {
        for (AbstractFlyingObject item : flyingObjectList) {
            item.explode();
        }
        removeFlyingObject();
    }
}
