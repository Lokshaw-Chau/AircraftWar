package edu.hitsz.aircraft;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HeroAircraftTest {
    private HeroAircraft testAircraft;

    @BeforeEach
    void setUp() {
        testAircraft = HeroAircraft.creatHeroAircraft();
    }

    @Test
    @DisplayName("test creatHeroAircraft")
    void creatHeroAircraft() {
        System.out.println("**--Test creatHeroAircraft executed--**");
        assertEquals(testAircraft, HeroAircraft.creatHeroAircraft());
        assertEquals(2, testAircraft.hp);
    }

    @Test
    @DisplayName("test notValid")
    void notValid() {
        System.out.println("**--Test notValid executed--**");
        testAircraft.vanish();
        assertTrue(testAircraft.notValid());
    }
}
