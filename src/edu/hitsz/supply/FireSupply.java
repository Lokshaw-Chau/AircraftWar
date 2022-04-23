package edu.hitsz.supply;
/**
 * FireSupply
 *
 * @author Lau-Shaw Chau
 * @date 2022/04/20
 */

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.strategy.HeroScratteredShoot;
import edu.hitsz.strategy.HeroStraightShoot;

public class FireSupply extends AbstractSuppply {
    public FireSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void takeEffect(HeroAircraft heroAircraft) {
        Runnable task = () -> {
            try {
                synchronized (heroAircraft) {
                    heroAircraft.setShootNum(heroAircraft.getShootNum() + 1);
                    heroAircraft.setStategy(new HeroScratteredShoot());
                }
                Thread.sleep(5000);
                synchronized (heroAircraft) {
                    heroAircraft.setShootNum(heroAircraft.getShootNum() - 1);
                    if (heroAircraft.getShootNum() == 2) {
                        heroAircraft.setStategy(new HeroStraightShoot());
                    } else {
                        heroAircraft.setStategy(new HeroScratteredShoot());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(task).start();
    }
}
