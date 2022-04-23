package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;

/**
 * BloodSupply
 *
 * @author Lau-Shaw Chau
 * @date 2022/04/04
 */
public class BloodSupply extends AbstractSuppply {
    int maxHp = 1000;
    int heal = 10;

    public BloodSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void takeEffect(HeroAircraft heroAircraft) {
        heroAircraft.decreaseHp(-heal);
        if (heroAircraft.getHp() > maxHp) {
            heroAircraft.decreaseHp(heroAircraft.getHp() - maxHp);
        }
    }

}
