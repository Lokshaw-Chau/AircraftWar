package edu.hitsz.supply;

/**
 * BombSupplyFactory
 *
 * @author Lau-Shaw Chau
 * @date 2022/04/04
 */
public class BombSupplyFactory implements SupplyFactory {
    @Override
    public AbstractSuppply creatSupply(int locationX, int locationY) {
        return new BombSupply(locationX, locationY, 0, 5);
    }
}