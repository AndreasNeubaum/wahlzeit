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

/**
 * Test cases for a variety of value object classes.
 */
public class LocationTest
{
	/**
	 *
	 */
	@Test
	public void testZeroDistance()
	{
		Location c1 = new Location(new SphericCoordinate(20, 7));
		Location c2 = new Location(new SphericCoordinate(20, 7));

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(0, distance1, 1.0e-10);
		Assert.assertEquals(0, distance2, 1.0e-10);
	}

	@Test
	public void testZeroLongitude()
	{
		Location c1 = new Location(new SphericCoordinate(30, 0));
		Location c2 = new Location(new SphericCoordinate(60, 0));

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(3335.847799, distance1, 1.0e-6);//6371*arctan(1/sqrt(3))
		Assert.assertEquals(3335.847799, distance2, 1.0e-6);
	}

	@Test
	public void testZeroLatitude()
	{
		Location c1 = new Location(new SphericCoordinate(0, 30));
		Location c2 = new Location(new SphericCoordinate(0, 60));

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(3335.847799, distance1, 1.0e-6);//6371*arctan(1/sqrt(3))
		Assert.assertEquals(3335.847799, distance2, 1.0e-6);
	}

	@Test
	public void testBothZero()
	{
		Location c1 = new Location(new SphericCoordinate(0, 0));
		Location c2 = new Location(new SphericCoordinate(0, 0));

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(0, distance1, 1.0e-10);
		Assert.assertEquals(0, distance2, 1.0e-10);
	}

	@Test(expected = NullPointerException.class)
	public void testOneNull()
	{
		Location c1 = new Location(new SphericCoordinate(0, 0));

		double distance1 = c1.getDistance(null);
	}

	@Test
	public void testTwoQuadrants1()
	{
		Location c1 = new Location(new SphericCoordinate(330, 0));
		Location c2 = new Location(new SphericCoordinate(30, 0));

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(6671.695599, distance1, 1.0e-6);//6371*(pi/3)
		Assert.assertEquals(6671.695599, distance2, 1.0e-6);
	}

	@Test
	public void testTwoQuadrants2()
	{
		Location c1 = new Location(new SphericCoordinate(720, 0));
		Location c2 = new Location(new SphericCoordinate(30, 0));

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(3335.847799, distance1, 1.0e-6);//6371*arctan(1/sqrt(3))
		Assert.assertEquals(3335.847799, distance2, 1.0e-6);
	}

	@Test
	public void testTwoQuadrants3()
	{
		Location c1 = new Location(new SphericCoordinate(690, 0));
		Location c2 = new Location(new SphericCoordinate(30, 0));

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(6671.695599, distance1, 1.0e-6);//6371*(pi/3)
		Assert.assertEquals(6671.695599, distance2, 1.0e-6);
	}

	@Test
	public void testTwoQuadrants4()
	{
		Location c1 = new Location(new SphericCoordinate(330, 30));
		Location c2 = new Location(new SphericCoordinate(690, 30));

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(0, distance1, 1.0e-10);//6371*0
		Assert.assertEquals(0, distance2, 1.0e-10);
	}

	@Test
	public void testPrime1()
	{
		Location c1 = new Location(new SphericCoordinate(7, 23));
		Location c2 = new Location(new SphericCoordinate(47, 59));

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(5609.6695006, distance1, 1.0e-7);
		Assert.assertEquals(5609.6695006, distance2, 1.0e-7);
	}

	@Test
	public void testPrime2()
	{
		Location c1 = new Location(new SphericCoordinate(61, 7));
		Location c2 = new Location(new SphericCoordinate(23, 41));

		double distance1 = c1.getDistance(c2);
		double distance2 = c2.getDistance(c1);

		Assert.assertEquals(4962.111585, distance1, 1.0e-6);
		Assert.assertEquals(4962.111585, distance2, 1.0e-6);
	}

	@Test
	public void testKonstruktor()
	{
		Location c = new Location(new SphericCoordinate(47, 11));
		Assert.assertEquals(47, ((SphericCoordinate)c.coordinate).getLatitude(), 1.0e-6);
		Assert.assertEquals(11, ((SphericCoordinate)c.coordinate).getLongitude(), 1.0e-6);
	}



}
