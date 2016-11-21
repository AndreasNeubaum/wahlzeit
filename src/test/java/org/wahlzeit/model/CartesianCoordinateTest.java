package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by andreas on 14.11.16.
 */
public class CartesianCoordinateTest
{
    @Test
    public void constructorAndGetterTest()
    {
        CartesianCoordinate c = new CartesianCoordinate(10, 20, 30);
        assertEquals(10, c.getX(), 1.0e-6);
        assertEquals(20, c.getY(), 1.0e-6);
        assertEquals(30, c.getZ(), 1.0e-6);
    }

    @Test
    public void otherFormatCoordinateTest()
    {
        SphericCoordinate sc = new SphericCoordinate(10,20,30);
        CartesianCoordinate c = new CartesianCoordinate(20, 30, 40);
        c.getDistance(sc);
    }

    @Test(expected = NullPointerException.class)
    public void otherNullTest()
    {
        CartesianCoordinate c = new CartesianCoordinate(30, 40, 50);
        c.getDistance(null);
    }

    @Test
    public void getDistanceSameCoordinateTest()
    {
        CartesianCoordinate c1 = new CartesianCoordinate(40, 50, 60);
        CartesianCoordinate c2 = new CartesianCoordinate(40, 50, 60);
        assertEquals(0, c1.getDistance(c2), 1.0e-6);
    }

    @Test
    public void getDistanceRealValue()
    {
        CartesianCoordinate c1 = new CartesianCoordinate(40, 50, 60);
        CartesianCoordinate c2 = new CartesianCoordinate(70, 90, 120);
        double expectedDistance = Math.sqrt(6100);
        assertEquals(expectedDistance, c1.getDistance(c2), 1.0e-6);
        assertEquals(expectedDistance, c2.getDistance(c1), 1.0e-6);
    }

    @Test
    public void equalsItselfTest()
    {
        CartesianCoordinate c1 = new CartesianCoordinate(50, 60, 70);
        CartesianCoordinate c2 = new CartesianCoordinate(50, 60, 70);

        assertTrue(c1.isEqual(c2));
        assertTrue(c1.isEqual(c1));
        assertTrue(c2.isEqual(c2));
    }

}
