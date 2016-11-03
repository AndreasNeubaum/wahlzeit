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
public class CoordinateTest
{
	/**
	 *
	 */
	@Test
	public void testZeroDistance()
	{
		Coordinate c1 = new Coordinate(20, 7);
		Coordinate c2 = new Coordinate(20, 7);

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(0, distance1, 1.0e-10);
		Assert.assertEquals(0, distance2, 1.0e-10);
	}

	@Test
	public void testZeroLongitude()
	{
		Coordinate c1 = new Coordinate(30, 0);
		Coordinate c2 = new Coordinate(60, 0);

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(3335.847799, distance1, 1.0e-6);//6371*arctan(1/sqrt(3))
		Assert.assertEquals(3335.847799, distance2, 1.0e-6);
	}

	@Test
	public void testZeroLatitude()
	{
		Coordinate c1 = new Coordinate(0, 30);
		Coordinate c2 = new Coordinate(0, 60);

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(3335.847799, distance1, 1.0e-6);//6371*arctan(1/sqrt(3))
		Assert.assertEquals(3335.847799, distance2, 1.0e-6);
	}

	@Test
	public void testBothZero()
	{
		Coordinate c1 = new Coordinate(0, 0);
		Coordinate c2 = new Coordinate(0, 0);

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(0, distance1, 1.0e-10);
		Assert.assertEquals(0, distance2, 1.0e-10);
	}

	@Test(expected = NullPointerException.class)
	public void testOneNull()
	{
		Coordinate c1 = new Coordinate(0, 0);

		double distance1 = c1.getDistance(null);
	}

	@Test
	public void testTwoQuadrants1()
	{
		Coordinate c1 = new Coordinate(330, 0);
		Coordinate c2 = new Coordinate(30, 0);

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(6671.695599, distance1, 1.0e-6);//6371*(pi/3)
		Assert.assertEquals(6671.695599, distance2, 1.0e-6);
	}

	@Test
	public void testTwoQuadrants2()
	{
		Coordinate c1 = new Coordinate(720, 0);
		Coordinate c2 = new Coordinate(30, 0);

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(3335.847799, distance1, 1.0e-6);//6371*arctan(1/sqrt(3))
		Assert.assertEquals(3335.847799, distance2, 1.0e-6);
	}

	@Test
	public void testTwoQuadrants3()
	{
		Coordinate c1 = new Coordinate(690, 0);
		Coordinate c2 = new Coordinate(30, 0);

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(6671.695599, distance1, 1.0e-6);//6371*(pi/3)
		Assert.assertEquals(6671.695599, distance2, 1.0e-6);
	}

	@Test
	public void testTwoQuadrants4()
	{
		Coordinate c1 = new Coordinate(330, 30);
		Coordinate c2 = new Coordinate(690, 30);

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(0, distance1, 1.0e-10);//6371*0
		Assert.assertEquals(0, distance2, 1.0e-10);
	}

	@Test
	public void testPrime1()
	{
		Coordinate c1 = new Coordinate(7, 23);
		Coordinate c2 = new Coordinate(47, 59);

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(5609.6695006, distance1, 1.0e-7);
		Assert.assertEquals(5609.6695006, distance2, 1.0e-7);
	}

	@Test
	public void testPrime2()
	{
		Coordinate c1 = new Coordinate(61, 7);
		Coordinate c2 = new Coordinate(23, 41);

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(4962.111585, distance1, 1.0e-6);
		Assert.assertEquals(4962.111585, distance2, 1.0e-6);
	}

	@Test
	public void DegToRad30()
	{
		Assert.assertEquals(Math.PI/6, Coordinate.DegToRad(30), 1.0e-6);
	}

	@Test
	public void DegToRad60()
	{
		Assert.assertEquals(Math.PI/3, Coordinate.DegToRad(60), 1.0e-6);
	}

	@Test
	public void DegToRad90()
	{
		Assert.assertEquals(Math.PI/2, Coordinate.DegToRad(90), 1.0e-6);
	}

	@Test
	public void DegToRad120()
	{
		Assert.assertEquals(Math.PI*(2/3.0), Coordinate.DegToRad(120), 1.0e-6);
	}

	@Test
	public void DegToRad180()
	{
		Assert.assertEquals(Math.PI, Coordinate.DegToRad(180), 1.0e-6);
	}

	@Test
	public void DegToRad360()
	{
		Assert.assertEquals(2*Math.PI, Coordinate.DegToRad(360), 1.0e-6);
	}

	@Test
	public void DegToRad720()
	{
		Assert.assertEquals(4*Math.PI, Coordinate.DegToRad(720), 1.0e-6);
	}

	@Test
	public void DegToRad730()
	{
		Assert.assertEquals((73/18.0)*Math.PI, Coordinate.DegToRad(730), 1.0e-6);
	}

	@Test
	public void DegToRad47()
	{
		Assert.assertEquals((47/180.0)*Math.PI, Coordinate.DegToRad(47), 1.0e-6);
	}

	@Test
	public void testGetter()
	{
		Coordinate c = new Coordinate(47, 11);
		Assert.assertEquals(47, c.getLatitude(), 1.0e-6);
		Assert.assertEquals(11, c.getLongitude(), 1.0e-6);
	}



}