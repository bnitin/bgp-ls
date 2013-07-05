/**
 * 
 */
package org.topology.bgp_ls.config.nodes.impl;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.topology.bgp_ls.config.nodes.AddressFamilyRoutingConfiguration;
import org.topology.bgp_ls.config.nodes.RoutingConfiguration;
import org.topology.bgp_ls.net.AddressFamilyKey;

/**
 * @author rainer
 *
 */
public class RoutingConfigurationParser {

	private AddressFamilyRoutingConfigurationParser afParser;

	public RoutingConfigurationParser() {
		afParser = new AddressFamilyRoutingConfigurationParser();
	}
	
	public RoutingConfiguration parseConfiguration(HierarchicalConfiguration config) throws ConfigurationException {
		RoutingConfigurationImpl result = new RoutingConfigurationImpl();
		Set<AddressFamilyKey> keys = new HashSet<AddressFamilyKey>();

		for(HierarchicalConfiguration afConfig: config.configurationsAt("AddressFamily")) {
			AddressFamilyRoutingConfiguration afRouting = afParser.parseConfiguration(afConfig);
			
			if(keys.contains(afRouting.getKey()))
				throw new ConfigurationException("Duplicate address family: " + afRouting.getKey());
			
			result.getRoutingConfigurations().add(afRouting);
			keys.add(afRouting.getKey());
		}
		
		return result;
	}
}
