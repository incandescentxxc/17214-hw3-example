package edu.cmu.cs214.hw3.model.towerTest;

import org.junit.Before;
import org.junit.Test;

import edu.cmu.cs214.hw3.model.tower.Tower;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class towerTest {
    private Tower tower;

    @Before
    public void setUp() {
        tower = new Tower(1);
    }

    @Test
    public void testBuildUpBelowLevelThree() {
        assertTrue(tower.canBuildUp());
        assertTrue(tower.canBuildUp());
        tower.buildUp();
        tower.buildUp();
        assertEquals(3, tower.getLevel());
    }

    @Test
    public void testLevelUpAtLevelThree() {
        testBuildUpBelowLevelThree();
        assertTrue(tower.canBuildUp()); // dome
        tower.buildUp();
        assertEquals(3, tower.getLevel());
        assertTrue(tower.hasDome());
    }

    @Test
    public void testBuildUpAboveLevel3() {
        testLevelUpAtLevelThree();
        assertFalse(tower.canBuildUp());
        assertEquals(3, tower.getLevel());
        assertTrue(tower.hasDome());
    }

}
