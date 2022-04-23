package edu.hitsz.supply;

/**
 * BloodSupplyFactory
 *
 * @author Lau-Shaw Chau
 * @date 2022/04/04
 */
public class BloodSupplyFactory implements SupplyFactory {
    @Override
    public AbstractSuppply creatSupply(int locationX, int locationY) {
        return new BloodSupply(locationX, locationY, 0, 5);
    }
}
