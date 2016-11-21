package org.wahlzeit.model;


import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractCoordinateTest
{
    @Test
    public void equalsItselfTest()
    {
        SphericCoordinate c1 = new SphericCoordinate(50, 60, 70);
        SphericCoordinate c2 = new SphericCoordinate(50, 60, 70);

        assertTrue(c1.isEqual(c2));
        assertTrue(c1.isEqual(c1));
        assertTrue(c2.isEqual(c2));
    }

    @Test
    public void equalsOtherFormatTest()
    {
        SphericCoordinate sc = new SphericCoordinate(31.002719133874, 56.30993247402, 7);
        CartesianCoordinate cc = new CartesianCoordinate(2, 3, 6);

        assertTrue(cc.isEqual(sc));
        assertTrue(sc.isEqual(cc));
    }

    @Test
    public void equalsOtherFormatTest3()
    {
        SphericCoordinate sc = new SphericCoordinate(31.002719133874, 146.30993247402, 7);
        CartesianCoordinate cc = new CartesianCoordinate(-3, 2, 6);

        assertTrue(cc.isEqual(sc));
        assertTrue(sc.isEqual(cc));
    }

    @Test
    public void equalsOtherFormatTest4()
    {
        SphericCoordinate sc = new SphericCoordinate(31.002719133874, 236.30993247402, 7);
        CartesianCoordinate cc = new CartesianCoordinate(-2, -3, 6);

        assertTrue(cc.isEqual(sc));
        assertTrue(sc.isEqual(cc));
    }

    @Test
    public void equalsOtherFormatTest5()
    {
        SphericCoordinate sc = new SphericCoordinate(31.002719133874, 326.30993247402, 7);
        CartesianCoordinate cc = new CartesianCoordinate(3, -2, 6);

        assertTrue(cc.isEqual(sc));
        assertTrue(sc.isEqual(cc));
    }

    @Test
    public void getDistanceDifferentFormatsTest()
    {
        SphericCoordinate sc = new SphericCoordinate(31.002719133874, 326.30993247402, 7);
        CartesianCoordinate cc = new CartesianCoordinate(3, -2, 6);

        assertTrue(sc.getDistance(cc) < 0.001);
        assertTrue(cc.getDistance(sc) < 0.001);
    }
}
