package edu.hitsz.aircraft;

import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.supply.AbstractSuppply;
import edu.hitsz.supply.BombSupplyFactory;
import edu.hitsz.supply.FireSupplyFactory;
import edu.hitsz.supply.SupplyFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 所有种类飞机的抽象父类：
 * 敌机（BOSS, ELITE, MOB），英雄飞机
 *
 * @author hitsz
 */
public abstract class AbstractAircraft extends AbstractFlyingObject {
    /**
     * 生命值
     */
    protected int maxHp;
    protected int hp;

    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
    }

    public void decreaseHp(int decrease) {
        hp -= decrease;
        if (hp <= 0) {
            hp = 0;
            vanish();
        }
    }

    public int getHp() {
        return hp;
    }

    public List<AbstractSuppply> makeSupply() {
        List<AbstractSuppply> supplyList = new LinkedList<>();
        SupplyFactory supplyFactory = null;
        Random random = new Random();
        int num = random.nextInt(4);
        if (num == 1) {
            //supplyFactory = new BloodSupplyFactory();
        }
        if (num >= 1) {
            supplyFactory = new BombSupplyFactory();
        }
        if (num == 1) {
            supplyFactory = new FireSupplyFactory();
        }
        if (supplyFactory == null) {
            return new LinkedList<>();
        }
        supplyList.add(supplyFactory.creatSupply(locationX, locationY));
        return supplyList;
    }

    /**
     * 飞机射击方法，可射击对象必须实现
     *
     * @return 可射击对象需实现，返回子弹
     * 非可射击对象空实现，返回null
     */
    public abstract List<BaseBullet> shoot();

}


