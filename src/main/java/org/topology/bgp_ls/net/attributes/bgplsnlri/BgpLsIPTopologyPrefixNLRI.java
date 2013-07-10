/**
 *  Copyright 2013 Nitin Bahadur (nitinb@gmail.com)
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
package org.topology.bgp_ls.net.attributes.bgplsnlri;

import java.util.LinkedList;
import java.util.List;

import org.topology.bgp_ls.net.SubsequentAddressFamily;

/**
 * This object represents a list of IP prefixes associated with a local node
 * @author nitinb
 *
 */
public class BgpLsIPTopologyPrefixNLRI extends BgpLsNLRIInformation {
	
	private BgpLsProtocolId protocolId;
	private int instanceIdentifier;
	private BgpLsNodeDescriptor localNodeDescriptors;
	private List<IPPrefix> prefixList;
	
	/**
	 * @param safi BGP subsequent address family
	 * @param nlriType BGP NLRI type
	 */
	public BgpLsIPTopologyPrefixNLRI(SubsequentAddressFamily safi, BgpLsNLRIType nlriType) {
		super(safi, nlriType);
		prefixList = new LinkedList<IPPrefix>();
	}
	
	/**
	 * Checks if this is a well formed object
	 * @return
	 */
	public boolean isValidPrefixNLRI() {
		// must contain at least 1 prefix
		if (prefixList.size() == 0) {
			return false;
		}
		
		// each prefix must be well formed
		for(IPPrefix prefix: prefixList) {
			if (!prefix.isValidPrefix()) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Sets the protocol type through which the prefixes are learnt
	 * @param protocolId protocol id
	 */
	public void setProtocolId(BgpLsProtocolId protocolId) {
		this.protocolId = protocolId;
	}
	
	/**
	 * Gets the protocol type through which the prefixes are learnt
	 * @return protocol id
	 */
	public BgpLsProtocolId getProtocolId() {
		return protocolId;
	}
		
	/**
	 * Sets the local node descriptors associated with this prefix list
	 * @param localNodeDescriptors local node descriptors
	 */
	public void setLocalNodeDescriptors(BgpLsNodeDescriptor localNodeDescriptors) {
		this.localNodeDescriptors = localNodeDescriptors;
	}
	
	/**
	 * Gets the local node descriptors associated with this prefix list
	 * @return node descriptors
	 */
	public BgpLsNodeDescriptor getLocalNodeDescriptors() {
		return localNodeDescriptors;
	}
	
	/**
	 * Sets the list of prefixes
	 * @param prefixList prefix list
	 */
	public void setPrefixList(List<IPPrefix> prefixList) {
		this.prefixList = prefixList;
	}
	
	/**
	 * Gets the list of prefixes
	 * @return prefix list
	 */
	public List<IPPrefix> getPrefixList() {
		return prefixList;
	}
	
	/**
	 * Adds a prefix to the prefix list
	 * @param prefix IP prefix
	 */
	public void addPrefix(IPPrefix prefix) {
		prefixList.add(prefix);
	}

	/**
	 * Gets the instance identifier of this prefix list
	 * @return instance identifier
	 */
	public int getInstanceIdentifier() {
		return instanceIdentifier;
	}

	/**
	 * Sets the instance identifier of this prefix list
	 * @param instanceIdentifier instance identifier to set
	 */
	public void setInstanceIdentifier(int instanceIdentifier) {
		this.instanceIdentifier = instanceIdentifier;
	}
}
