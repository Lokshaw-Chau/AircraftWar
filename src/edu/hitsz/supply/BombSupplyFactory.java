package edu.hitsz.supply;

/**
 * 创建炸弹道具的工厂
 *
 * @author Lau-Shaw Chau
 * @date 2022/04/04
 */
public class BombSupplyFactory implements SupplyFactory {
    @Override
    public AbstractSupply creatSupply(int locationX, int locationY) {
        return new BombSupply(locationX, locationY, 0, 5);
    }
}