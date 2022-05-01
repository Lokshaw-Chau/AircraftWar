package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;

/**
 * 血量补给
 *
 * @author Lau-Shaw Chau
 * @date 2022/04/04
 */
public class BloodSupply extends AbstractSupply {
    int maxHp = HeroAircraft.heroAircraft.getMaxHp();
    int heal = 20;

    public BloodSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void takeEffect() {
        System.out.println("BloodSupply Active");
        HeroAircraft.getInstance().decreaseHp(-heal);
        if (HeroAircraft.getInstance().getHp() > maxHp) {
            HeroAircraft.getInstance().decreaseHp(HeroAircraft.getInstance().getHp() - maxHp);
        }
    }

}
