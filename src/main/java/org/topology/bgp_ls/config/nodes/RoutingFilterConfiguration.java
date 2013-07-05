/**
 * 
 */
package org.topology.bgp_ls.config.nodes;

/**
 * @author rainer
 *
 */
public interface RoutingFilterConfiguration extends	Comparable<RoutingFilterConfiguration> {

	/**
	 * get the routing filter name
	 * 
	 * @return
	 */
	public String getName();
	
}
