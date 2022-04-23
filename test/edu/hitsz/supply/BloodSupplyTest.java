package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BloodSupplyTest {
    HeroAircraft testHeroAircraft;
    AbstractSuppply testBloodSupply;

    @BeforeEach
    void setUp() {
        testHeroAircraft = HeroAircraft.creatHeroAircraft();
        SupplyFactory supplyFactory = new BloodSupplyFactory();
        testBloodSupply = supplyFactory.creatSupply(1, 1);
    }

    @Test
    void takeEffect() {
        System.out.println("**--Test takeEffect executed--**");
        testBloodSupply.takeEffect(testHeroAircraft);
        assertEquals(11, testHeroAircraft.getHp());
        HeroAircraft.creatHeroAircraft();
        testBloodSupply.takeEffect(testHeroAircraft);
        assertEquals(100, testHeroAircraft.getHp());
    }

    @Test
    void getLocationX() {
        System.out.println("**--Test getLocationX executed--**");
        assertEquals(1, testBloodSupply.getLocationX());
    }
}