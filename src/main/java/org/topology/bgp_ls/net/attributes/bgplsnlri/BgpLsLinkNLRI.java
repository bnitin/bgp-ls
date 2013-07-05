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

import org.topology.bgp_ls.net.SubsequentAddressFamily;

/**
 * @author nitinb
 *
 */
public class BgpLsLinkNLRI extends BgpLsNLRIInformation {

	private BgpLsProtocolId protocolId;
	private int instanceIdentifier;
	private BgpLsNodeDescriptor localNodeDescriptors;
	private BgpLsNodeDescriptor remoteNodeDescriptors;
	private BgpLsLinkDescriptor linkDescriptors;
	
	public BgpLsLinkNLRI(SubsequentAddressFamily safi) {
		super(safi, BgpLsNLRIType.LINK_NLRI);
	}
	
	public boolean isValidLinkNLRI() {
		// check for validity of node descriptors
		if (!localNodeDescriptors.isValidNodeDescriptor() ||
			!remoteNodeDescriptors.isValidNodeDescriptor()) {
			return false;
		}
		
		// check for a common router-id in local & remote descriptor array
		if (localNodeDescriptors == null || remoteNodeDescriptors == null ||
			lookupCommonRouterId(localNodeDescriptors, remoteNodeDescriptors) == BgpLsType.Unknown) {
			return false;
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
	
	public void setRemoteNodeDescriptors(BgpLsNodeDescriptor remoteNodeDescriptors) {
		this.remoteNodeDescriptors = remoteNodeDescriptors;
	}
	
	public BgpLsNodeDescriptor getRemoteNodeDescriptors() {
		return this.remoteNodeDescriptors;
	}
	
	
	public void setLinkDescriptors(BgpLsLinkDescriptor linkDescriptors) {
		this.linkDescriptors = linkDescriptors;
	}
	
	public BgpLsLinkDescriptor getLinkDescriptors() {
		return this.linkDescriptors;
	}
	
	/**
	 * There should be at least 1 common router-id family (ISO or IPv4 or IPv6)
	 * between the 2 node descriptors
	 * @param a  descriptor-1
	 * @param b  descriptor-2
	 * @return   address family of the common router id. Unknown if none exists
	 */
	public static BgpLsType lookupCommonRouterId(BgpLsNodeDescriptor a, BgpLsNodeDescriptor b) {
		if (a.getIsoNodeId() != null & b.getIsoNodeId()!= null) {
			return BgpLsType.ISONodeId;
		}
		if (a.getIPv4RouterId() != null & b.getIPv4RouterId() != null) {
			return BgpLsType.IPv4RouterId;
		}
		if (a.getIPv6RouterId() != null & b.getIPv6RouterId() != null) {
			return BgpLsType.IPv6RouterId;
		}
		return BgpLsType.Unknown;
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
