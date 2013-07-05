/**
 * 
 */
package org.topology.bgp_ls.config.nodes.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.topology.bgp_ls.config.nodes.AddressFamilyRoutingConfiguration;
import org.topology.bgp_ls.config.nodes.RouteConfiguration;
import org.topology.bgp_ls.net.AddressFamily;
import org.topology.bgp_ls.net.AddressFamilyKey;
import org.topology.bgp_ls.net.SubsequentAddressFamily;
import org.topology.bgp_ls.netty.SingletonRegistry;

/**
 * @author rainer
 *
 */
public class AddressFamilyRoutingConfigurationParser {

	private RouteConfigurationParser routeParser;
	
	public AddressFamilyRoutingConfigurationParser() {
		routeParser = (RouteConfigurationParser)SingletonRegistry.getInstance(RouteConfigurationParser.class.getName());
	}
	
	AddressFamilyRoutingConfiguration parseConfiguration(HierarchicalConfiguration config) throws ConfigurationException {
		try {
			AddressFamilyKey key = new AddressFamilyKey(AddressFamily.fromString(config.getString("[@addressFamily]")), 
					SubsequentAddressFamily.fromString(config.getString("[@subsequentAddressFamily]")));
			
			List<RouteConfiguration> routes = new LinkedList<RouteConfiguration>();
			
			for(HierarchicalConfiguration routeConfig : config.configurationsAt("Route"))
				routes.add(routeParser.parseConfiguration(routeConfig));
			
			return new AddressFamilyRoutingConfigurationImpl(key, routes);
		} catch(IllegalArgumentException  e) {
			throw new ConfigurationException("Invalid value: ", e);
		}
	}
}
