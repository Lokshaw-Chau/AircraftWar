package edu.hitsz.supply;

import edu.hitsz.basic.AbstractFlyingObject;

import java.util.ArrayList;

public class BombSupplyPublisher {
    private static BombSupplyPublisher bombSupplyPublisher = new BombSupplyPublisher();
    private ArrayList<AbstractFlyingObject> flyingObjectList = new ArrayList<>();

    private BombSupplyPublisher() {

    }

    public void addFlyingObject(AbstractFlyingObject flyingObject) {
        flyingObjectList.add(flyingObject);
    }

    private void removeFlyingObject() {
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
