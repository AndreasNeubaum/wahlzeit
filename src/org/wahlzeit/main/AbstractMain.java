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

package org.wahlzeit.main;

import javax.servlet.http.*;

import org.wahlzeit.services.*;

/**
 * 
 * @author dirkriehle
 *
 */
public abstract class AbstractMain {
	
	/**
	 * 
	 */
	protected SysSession mainSession = null;
	
	/**
	 * 
	 */
	protected AbstractMain() {
		// do nothing
	}
	
	/**
	 * 
	 */
	protected void handleArgv(String[] argv) {
		// do nothing
	}

	/**
	 * 
	 */
	protected void startUp() throws Exception {
		SysConfig.setInstance(createSysConfig());
		
		mainSession = new SysSession("system");
		SessionManager.setThreadLocalSession(mainSession);
	}
	
	/**
	 * 
	 */
	protected SysConfig createSysConfig() {
		return createDevSysConfig();
	}
	/**
	 * 
	 */
	protected SysConfig createProdSysConfig() {
		return new SysConfig("wahlzeit.org"); 
	}
	
	/**
	 * 
	 */
	protected SysConfig createDevSysConfig() {
		return new SysConfig("localhost", "8080");
	}
	
	/**
	 * 
	 */
	protected void shutDown() throws Exception {
		SysConfig.dropInstance();
	}
	
}
