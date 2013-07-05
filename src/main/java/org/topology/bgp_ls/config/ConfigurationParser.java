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
 * File: org.bgp4.config.ConfigurationParser.java 
 */
/**
 * Copyright 2013 Nitin Bahadur (nitinb@gmail.com)
 * 
 * License: same as above
 * 
 * Modified to run as an independent java application, one that does not
 * require webserver or app server
 */
package org.topology.bgp_ls.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.topology.bgp_ls.config.impl.ConfigurationParserImpl;
import org.topology.bgp_ls.config.nodes.ServerConfiguration;
import org.topology.bgp_ls.config.nodes.RoutingConfiguration;
import org.topology.bgp_ls.config.nodes.impl.HttpServerConfigurationParser;
import org.topology.bgp_ls.config.nodes.impl.RoutingConfigurationParser;
import org.topology.bgp_ls.netty.SingletonRegistry;

/**
 * @author Rainer Bieniek (Rainer.Bieniek@web.de)
 *
 */
public class ConfigurationParser {

	private ConfigurationParserImpl parserImpl;
	private RoutingConfigurationParser routingParser;
	private HttpServerConfigurationParser httpServerParser;

    public ConfigurationParser () {
            parserImpl = new ConfigurationParserImpl();
            routingParser = new RoutingConfigurationParser();
            httpServerParser = (HttpServerConfigurationParser)SingletonRegistry.getInstance(HttpServerConfigurationParser.class.getName()); 
    }

	public Configuration parseConfiguration(XMLConfiguration configuration) throws ConfigurationException {
		return parserImpl.parseConfiguration(configuration);
	}

	public RoutingConfiguration parseRoutingConfiguration(HierarchicalConfiguration configuration) throws ConfigurationException {
		return routingParser.parseConfiguration(configuration);
	}
	
	public ServerConfiguration parseHttpServerConfiguration(HierarchicalConfiguration configuration) throws ConfigurationException {
		return httpServerParser.parseConfig(configuration);
	}
}
