package edu.hitsz.supply;

/**
 * FireSupplyFactory
 *
 * @author Lau-Shaw Chau
 * @date 2022/04/04
 */
public class FireSupplyFactory implements SupplyFactory {
    @Override
    public AbstractSuppply creatSupply(int locationX, int locationY) {

        return new FireSupply(locationX, locationY, 0, 5);
    }
}
