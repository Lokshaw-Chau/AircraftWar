package edu.hitsz.aircraft;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MobEnemyTest {

    AbstractAircraft testAircraft2;

    @BeforeEach
    void setUp() {
        EnemyFactory enemyFactory = new MobEnemyFactory();
        testAircraft2 = enemyFactory.creatEnemy();
    }

    @Test
    @DisplayName("test crash")
    void crash() {
        AbstractAircraft testAircraft1 = HeroAircraft.creatHeroAircraft();
        System.out.println("**--Test crash executed--**");
        assertTrue(testAircraft1.crash(testAircraft2));
        HeroAircraft.creatHeroAircraft();
        assertFalse(testAircraft1.crash(testAircraft2));
    }

    @Test
    void forward() {
        System.out.println("**--Test forward Executed--**");
        testAircraft2.forward();
        assertEquals(11, testAircraft2.getLocationY());
    }
}