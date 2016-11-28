/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test cases for a variety of value object classes.
 */
public class SphericCoordinateTest
{
	/**
	 *
	 */
	@Test
	public void testZeroDistance()
	{
		Coordinate c1 = new SphericCoordinate(20, 7);
		Coordinate c2 = new SphericCoordinate(20, 7);

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(0, distance1, 1.0e-10);
		Assert.assertEquals(0, distance2, 1.0e-10);
	}

	@Test
	public void testZeroLongitude()
	{
		Coordinate c1 = new SphericCoordinate(30, 0);
		Coordinate c2 = new SphericCoordinate(60, 0);

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(3335.847799, distance1, 1.0e-6);//6371*arctan(1/sqrt(3))
		Assert.assertEquals(3335.847799, distance2, 1.0e-6);
	}

	@Test
	public void testZeroLatitude()
	{
		Coordinate c1 = new SphericCoordinate(0, 30);
		Coordinate c2 = new SphericCoordinate(0, 60);

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(3335.847799, distance1, 1.0e-6);//6371*arctan(1/sqrt(3))
		Assert.assertEquals(3335.847799, distance2, 1.0e-6);
	}

	@Test
	public void testBothZero()
	{
		Coordinate c1 = new SphericCoordinate(0, 0);
		Coordinate c2 = new SphericCoordinate(0, 0);

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(0, distance1, 1.0e-10);
		Assert.assertEquals(0, distance2, 1.0e-10);
	}

	@Test(expected = AssertionError.class)
	public void testOneNull()
	{
		Coordinate c1 = new SphericCoordinate(0, 0);

		double distance1 = c1.getDistance(null);
	}

	@Test
	public void testTwoQuadrants1()
	{
		Coordinate c1 = new SphericCoordinate(-30, 0);
		Coordinate c2 = new SphericCoordinate(30, 0);

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(6671.695599, distance1, 1.0e-6);//6371*(pi/3)
		Assert.assertEquals(6671.695599, distance2, 1.0e-6);
	}

	@Test
	public void testTwoQuadrants2()
	{
		Coordinate c1 = new SphericCoordinate(0, 0);
		Coordinate c2 = new SphericCoordinate(30, 0);

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(3335.847799, distance1, 1.0e-6);//6371*arctan(1/sqrt(3))
		Assert.assertEquals(3335.847799, distance2, 1.0e-6);
	}

	@Test
	public void testTwoQuadrants4()
	{
		Coordinate c1 = new SphericCoordinate(-30, 30);
		Coordinate c2 = new SphericCoordinate(-30, 30);

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(0, distance1, 1.0e-10);//6371*0
		Assert.assertEquals(0, distance2, 1.0e-10);
	}

	@Test
	public void testPrime1()
	{
		Coordinate c1 = new SphericCoordinate(7, 23);
		Coordinate c2 = new SphericCoordinate(47, 59);

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(5609.6695006, distance1, 1.0e-7);
		Assert.assertEquals(5609.6695006, distance2, 1.0e-7);
	}

	@Test
	public void testPrime2()
	{
		Coordinate c1 = new SphericCoordinate(61, 7);
		Coordinate c2 = new SphericCoordinate(23, 41);

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(4962.111585, distance1, 1.0e-6);
		Assert.assertEquals(4962.111585, distance2, 1.0e-6);
	}

	@Test
	public void testGetter()
	{
		SphericCoordinate c = new SphericCoordinate(47, 11);
		Assert.assertEquals(47, c.getLatitude(), 1.0e-6);
		Assert.assertEquals(11, c.getLongitude(), 1.0e-6);
		Assert.assertEquals(SphericCoordinate.EarthRadius, c.getRadius(), 1.0e-6);
	}

	@Test
	public void testRadiusConstructor()
	{
		SphericCoordinate c = new SphericCoordinate(47, 11, 123);
		Assert.assertEquals(123, c.getRadius(), 1.0e-6);
	}

	@Test(expected = AssertionError.class)
	public void otherFormatCoordinateTestDifferentRadius()
	{
		SphericCoordinate sc = new SphericCoordinate(10,20,30);
		CartesianCoordinate c = new CartesianCoordinate(20, 30, 40);
		sc.getDistance(c);
	}

	@Test//no exception
	public void otherFormatCoordinateTestSameRadius()
	{
		//pythagoräisches quadrupel: 2*2+3*3+6*6=7*7
		SphericCoordinate sc = new SphericCoordinate(10,20,7);//r=7
		CartesianCoordinate c = new CartesianCoordinate(2, 3, 6);
		assertTrue(sc.getDistance(c)>0);
	}
}

	//Rotation um die Latitude?



