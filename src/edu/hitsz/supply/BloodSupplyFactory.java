package edu.hitsz.supply;

/**
 * 创建血量补给的工厂
 *
 * @author Lau-Shaw Chau
 * @date 2022/04/04
 */
public class BloodSupplyFactory implements SupplyFactory {
    @Override
    public AbstractSupply creatSupply(int locationX, int locationY) {
        return new BloodSupply(locationX, locationY, 0, 5);
    }
}
