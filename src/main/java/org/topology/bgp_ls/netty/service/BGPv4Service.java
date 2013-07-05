/**
 *  Copyright 2012 Rainer Bieniek (Rainer.Bieniek@web.de)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 */
/**
 * Copyright 2013 Nitin Bahadur (nitinb@gmail.com)
 * 
 * License: same as above
 * 
 * Modified to run as an independent java application, one that does not
 * require webserver or app server
 */
package org.topology.bgp_ls.netty.service;

import org.topology.bgp_ls.config.global.ApplicationConfiguration;
import org.topology.bgp_ls.netty.fsm.FSMRegistry;
import org.topology.bgp_ls.netty.SingletonRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Rainer Bieniek (Rainer.Bieniek@web.de)
 *
 */
public class BGPv4Service {
		
	private static final Logger log = LoggerFactory.getLogger(BGPv4Service.class);

	private FSMRegistry fsmRegistry;
	private ApplicationConfiguration applicationConfiguration;
	
	private BGPv4Server serverInstance;

	public BGPv4Service() {
		applicationConfiguration = (ApplicationConfiguration)SingletonRegistry.getInstance(ApplicationConfiguration.class.getName());
		fsmRegistry = (FSMRegistry)SingletonRegistry.getInstance(FSMRegistry.class.getName());	
	}
	
	/**
	 * start the service
	 * 
	 * @param configuration the initial service configuration
	 */
	public void startService() {
		fsmRegistry.createRegistry();
				
		if(applicationConfiguration.getBgpServerConfiguration()!= null) {
			log.info("Starting local BGPv4 server");
			
			this.serverInstance = new BGPv4Server();
			
			serverInstance.startServer();
		}
		
		fsmRegistry.startFiniteStateMachines();
	}

	/**
	 * stop the running service
	 * 
	 */
	public void stopService() {
		fsmRegistry.stopFiniteStateMachines();

		if(serverInstance != null)
			serverInstance.stopServer();

		fsmRegistry.destroyRegistry();
	}
	
}
