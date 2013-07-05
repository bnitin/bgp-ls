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
package org.topology.bgp_ls.bgpd;

import org.topology.bgp_ls.netty.service.BGPv4Service;

/**
 * @author Rainer Bieniek (Rainer.Bieniek@web.de)
 *
 */
public class BgpDaemonApplicationListener extends Thread implements Runnable {
	private BGPv4Service bgpService;

	/**
	 * 
	 */
	public BgpDaemonApplicationListener() {
	}

	public void listen() {
		bgpService = new BGPv4Service();
		bgpService.startService();		
	}
	
	public void run () {
		listen();
	}
}
