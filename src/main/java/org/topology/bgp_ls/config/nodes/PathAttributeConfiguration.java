/**
 * 
 */
package org.topology.bgp_ls.config.nodes;

import java.util.Set;

import org.topology.bgp_ls.net.attributes.PathAttribute;

/**
 * @author rainer
 *
 */
public interface PathAttributeConfiguration extends Comparable<PathAttributeConfiguration>{

	/**
	 * 
	 * @return
	 */
	public Set<PathAttribute> getAttributes();
}
