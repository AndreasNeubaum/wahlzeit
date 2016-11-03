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
package org.wahlzeit.services.mailing;

import com.google.appengine.tools.development.testing.LocalBlobstoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import junit.framework.TestCase;
import org.junit.*;
import org.wahlzeit.main.ServiceMain;
import org.wahlzeit.services.EmailAddress;
import org.wahlzeit.services.LogBuilder;


import java.io.File;

import static org.junit.Assert.*;

/**
 *
 */
public class EmailServiceTest /*extends TestCase*/ //nur fuer JUnit 3.8
{

	/**
	 *
	 */
	protected EmailService emailService = null;

	/**
	 *
	 */
	protected EmailAddress validAddress;


	/*
	* wird in der ServiceMain.java startUp() benötigt, der den UserManager initialisiert, um die GoogleAPI korrekt zu initialisieren
	* --> sonst excetion in 2. Zeile
	* */
	private static final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalBlobstoreServiceTestConfig());


	@BeforeClass
	public static void beforeClass() throws Exception
	{
		helper.setUp();

		//Im Pfad muss der rootDirectroy config/templates/default existieren
		// configures logging
		String contextPath = "/home/andreas/IdeaProjects/wahlzeit/build/exploded-app";//sc.getContextPath();
		System.setProperty("contextPath", contextPath);
		//log.config(LogBuilder.createSystemMessage().
		//		addParameter("System property context path", contextPath).toString());

		// determines file system root path to resources
		File dummyFile = new File(/*sc.getRealPath(*/"/home/andreas/IdeaProjects/wahlzeit/build/exploded-app/dummy.txt"/*)*/);
		String rootDir = dummyFile.getParent();
		//log.config(LogBuilder.createSystemMessage().
		//		addParameter("Root directory", rootDir).toString());

		ServiceMain.getInstance().startUp(false, rootDir);
	}

	@AfterClass
	public static void afterClass() throws Exception
	{
		ServiceMain.getInstance().shutDown();//klappt nicht wegen ObjectifyService
		helper.tearDown();
	}


	/**
	 *
	 */
	@Before
	public void setUp() throws Exception {
		//super.setUp();

		emailService = EmailServiceManager.getDefaultService();

		validAddress = EmailAddress.getFromString("test@test.de");
	}

	/**
	 *
	 */
	@Test
	public void testSendInvalidEmail() {
		try {
			assertFalse(emailService.sendEmailIgnoreException(validAddress, null, "lol", "hi"));
			assertFalse(emailService.sendEmailIgnoreException(null, validAddress, null, "body"));
			assertFalse(emailService.sendEmailIgnoreException(validAddress, null, "hi", "       "));
		} catch (Exception ex) {
			fail("Silent mode does not allow exceptions");
		}
	}

	/**
	 *
	 */
	@Test
	public void testSendValidEmail() {
		try {
			assertTrue(emailService.sendEmailIgnoreException(validAddress, validAddress, "hi", "test"));
		} catch (Exception ex) {
			fail("Silent mode does not allow exceptions");
		}
	}
}
