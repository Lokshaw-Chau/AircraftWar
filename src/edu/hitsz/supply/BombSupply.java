package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;

/**
 * BombSupply
 *
 * @author Lau-Shaw Chau
 * @date 2022/04/04
 */
public class BombSupply extends AbstractSuppply {
    public BombSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void takeEffect(HeroAircraft heroAircraft) {
        System.out.println("Bombsupply Active");
    }

}
