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
 * @author nitinb
 *
 */
public class BgpLsIPTopologyPrefixNLRI extends BgpLsNLRIInformation {
	
	private BgpLsProtocolId protocolId;
	private int instanceIdentifier;
	private BgpLsNodeDescriptor localNodeDescriptors;
	private List<IPPrefix> prefixList;
	
	public BgpLsIPTopologyPrefixNLRI(SubsequentAddressFamily safi, BgpLsNLRIType nlriType) {
		super(safi, nlriType);
		prefixList = new LinkedList<IPPrefix>();
	}
	
	public boolean isValidPrefixNLRI() {
		if (prefixList.size() == 0) {
			return false;
		}
		
		for(IPPrefix prefix: prefixList) {
			if (!prefix.isValidPrefix()) {
				return false;
			}
		}
		
		return true;
	}
	
	public void setProtocolId(BgpLsProtocolId protocolId) {
		this.protocolId = protocolId;
	}
	
	public BgpLsProtocolId getProtocolId() {
		return protocolId;
	}
		
	public void setLocalNodeDescriptors(BgpLsNodeDescriptor localNodeDescriptors) {
		this.localNodeDescriptors = localNodeDescriptors;
	}
	
	public BgpLsNodeDescriptor getLocalNodeDescriptors() {
		return localNodeDescriptors;
	}
	
	public void setPrefixList(List<IPPrefix> prefixList) {
		this.prefixList = prefixList;
	}
	
	public List<IPPrefix> getPrefixList() {
		return prefixList;
	}
	
	public void addPrefix(IPPrefix prefix) {
		prefixList.add(prefix);
	}

	/**
	 * @return the instanceIdentifier
	 */
	public int getInstanceIdentifier() {
		return instanceIdentifier;
	}

	/**
	 * @param instanceIdentifier the instanceIdentifier to set
	 */
	public void setInstanceIdentifier(int instanceIdentifier) {
		this.instanceIdentifier = instanceIdentifier;
	}
}
