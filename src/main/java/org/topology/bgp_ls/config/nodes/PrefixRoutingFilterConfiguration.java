/**
 * 
 */
package org.topology.bgp_ls.config.nodes;

import java.util.Set;

import org.topology.bgp_ls.net.NetworkLayerReachabilityInformation;

/**
 * @author rainer
 *
 */
public interface PrefixRoutingFilterConfiguration extends RoutingFilterConfiguration {

	/**
	 * get the route prefixes which are filtered out 
	 * 
	 * @return
	 */
	public Set<NetworkLayerReachabilityInformation> getFilterPrefixes();
}
