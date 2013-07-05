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
 * File: org.bgp4j.apps.bgpd.config.ConfigurationFileProcessor.java 
 */
package org.topology.bgp_ls.bgpd.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.topology.bgp_ls.config.ConfigurationParser;
import org.topology.bgp_ls.config.global.ApplicationConfiguration;
import org.topology.bgp_ls.netty.SingletonRegistry;

/**
 * This class manages the access to the configuration file provided on the command line. 
 * 
 * NB: The current version just parses the config file once. A future release will support automatic
 * detection of an updated configuration file and soft configuration changes
 * 
 * @author Rainer Bieniek (rainer@bgp4j.org)
 *
 */
public class ConfigurationFileProcessor {

	private ConfigurationParser configurationParser;
	private ApplicationConfiguration applicationConfiguration;
	
	
    public ConfigurationFileProcessor () {
            configurationParser = new ConfigurationParser();
            applicationConfiguration =  (ApplicationConfiguration)SingletonRegistry.getInstance(ApplicationConfiguration.class.getName());
    }

	public void processConfigFile(String configFile) throws ConfigurationException {
		applicationConfiguration.importConfiguration(configurationParser.parseConfiguration(new XMLConfiguration(configFile)));		
	}
	
	public ApplicationConfiguration getApplicationConfiguration() {
		return applicationConfiguration;
	}
}
