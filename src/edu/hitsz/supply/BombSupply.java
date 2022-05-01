package edu.hitsz.supply;

/**
 * 炸弹道具
 *
 * @author Lau-Shaw Chau
 * @date 2022/04/04
 */
public class BombSupply extends AbstractSupply {
    public BombSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void takeEffect() {
        BombSupplyPublisher.getInstance().induceExplosion();
        System.out.println("BombSupply Active");
    }

}
