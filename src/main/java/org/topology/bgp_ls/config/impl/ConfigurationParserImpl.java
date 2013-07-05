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
 * File: org.bgp4.config.impl.ConfigurationParserImpl.java 
 */
/**
 * Copyright 2013 Nitin Bahadur (nitinb@gmail.com)
 * 
 * License: same as above
 * 
 * Modified to run as an independent java application, one that does not
 * require webserver or app server
 */
package org.topology.bgp_ls.config.impl;

import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.topology.bgp_ls.config.Configuration;
import org.topology.bgp_ls.config.nodes.impl.BgpServerConfigurationParser;
import org.topology.bgp_ls.config.nodes.impl.HttpServerConfigurationParser;
import org.topology.bgp_ls.config.nodes.impl.PeerConfigurationParser;
import org.topology.bgp_ls.config.nodes.impl.RoutingProcessorConfigurationParser;
import org.topology.bgp_ls.netty.SingletonRegistry;

/**
 * @author Rainer Bieniek (Rainer.Bieniek@web.de)
 *
 */
public class ConfigurationParserImpl {

	private BgpServerConfigurationParser bgpServerConfigurationParser;
	private HttpServerConfigurationParser httpServerConfigurationParser;
	private PeerConfigurationParser peerConfigurationParser;
	private RoutingProcessorConfigurationParser routingConfigurationParser;
	
    public ConfigurationParserImpl () {
    	bgpServerConfigurationParser = new BgpServerConfigurationParser();
    	httpServerConfigurationParser = (HttpServerConfigurationParser)SingletonRegistry.getInstance(HttpServerConfigurationParser.class.getName()); 
    	peerConfigurationParser = new PeerConfigurationParser();
    	routingConfigurationParser = new RoutingProcessorConfigurationParser();
    }

	public Configuration parseConfiguration(XMLConfiguration configuration) throws ConfigurationException {
		ConfigurationImpl configImpl = new ConfigurationImpl(); 
		List<HierarchicalConfiguration> bgpServerNodes = configuration.configurationsAt("BgpServer");
		List<HierarchicalConfiguration> httpServerNodes = configuration.configurationsAt("HttpServer");
		List<HierarchicalConfiguration> bgpPeerNodes = configuration.configurationsAt("BgpPeers.BgpPeer");
		List<HierarchicalConfiguration> routingProcessorNodes = configuration.configurationsAt("RoutingProcessor");
		
		if(bgpServerNodes.size() > 1)
			throw new ConfigurationException("Duplicate <BgpServer /> element");
		else if(bgpServerNodes.size() == 1) {
			configImpl.setBgpServerConfiguration(bgpServerConfigurationParser.parseConfig(bgpServerNodes.get(0)));
		}
		
		if(httpServerNodes.size() > 1)
			throw new ConfigurationException("Duplicate <HttpServer /> element");
		else if(httpServerNodes.size() == 1)
			configImpl.setHttpServerConfiguration(httpServerConfigurationParser.parseConfig(httpServerNodes.get(0)));
		
		
		if(routingProcessorNodes.size() > 1) 
			throw new ConfigurationException("Duplicate <RoutingProcessor /> element");
		else if(routingProcessorNodes.size() == 1)
			configImpl.setRoutingProcessorConfiguration(routingConfigurationParser.parseConfiguration(routingProcessorNodes.get(0)));
		
		
		for(HierarchicalConfiguration bgpPeerNode : bgpPeerNodes) {
			configImpl.addPeer(peerConfigurationParser.parseConfiguration(bgpPeerNode));
		}
				
		return configImpl;
	}

}
