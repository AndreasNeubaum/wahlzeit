package org.wahlzeit.model;


import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractCoordinateTest
{
    @Test
    public void equalsItselfTest()
    {
        SphericCoordinate c1 = SphericCoordinate.getCoordinate(50, 60, 70);
        SphericCoordinate c2 = SphericCoordinate.getCoordinate(50, 60, 70);

        assertTrue(c1.isEqual(c2));
        assertTrue(c1.isEqual(c1));
        assertTrue(c2.isEqual(c2));
    }

    @Test
    public void equalsOtherFormatTest()
    {
        SphericCoordinate sc = SphericCoordinate.getCoordinate(31.002719133874, 56.30993247402, 7);
        CartesianCoordinate cc = CartesianCoordinate.getCoordinate(2, 3, 6);

        assertTrue(cc.isEqual(sc));
        assertTrue(sc.isEqual(cc));
    }

    @Test
    public void equalsOtherFormatTest3()
    {
        SphericCoordinate sc = SphericCoordinate.getCoordinate(31.002719133874, 146.30993247402, 7);
        CartesianCoordinate cc = CartesianCoordinate.getCoordinate(-3, 2, 6);

        assertTrue(cc.isEqual(sc));
        assertTrue(sc.isEqual(cc));
    }

    @Test
    public void equalsOtherFormatTest4()
    {
        SphericCoordinate sc = SphericCoordinate.getCoordinate(31.002719133874, -123.690067526, 7);
        CartesianCoordinate cc = CartesianCoordinate.getCoordinate(-2, -3, 6);

        assertTrue(cc.isEqual(sc));
        assertTrue(sc.isEqual(cc));
    }

    @Test
    public void equalsOtherFormatTest5()
    {
        SphericCoordinate sc = SphericCoordinate.getCoordinate(31.002719133874, -33.690067526, 7);
        CartesianCoordinate cc = CartesianCoordinate.getCoordinate(3, -2, 6);

        assertTrue(cc.isEqual(sc));
        assertTrue(sc.isEqual(cc));
    }

    @Test
    public void getDistanceDifferentFormatsTest()
    {
        SphericCoordinate sc = SphericCoordinate.getCoordinate(31.002719133874, -33.690067526, 7);
        CartesianCoordinate cc = CartesianCoordinate.getCoordinate(3, -2, 6);

        assertTrue(sc.getDistance(cc) < 0.001);
        assertTrue(cc.getDistance(sc) < 0.001);
    }
}
