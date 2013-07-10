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

import java.net.Inet4Address;
import java.net.Inet6Address;

/**
 * This object describes the attributes of node. 
 * These attributes are used to identify a node and used to identify 
 * the end-points of a link. 
 * @author nitinb
 *
 */
public class BgpLsNodeDescriptor {
	private BgpLsType type;
	private long autonomousSystem = 0;
	private long memberAS = 0;
	//private long bgpIdentifier = 0;  draft v2
	private byte[] isoNodeId;
	private byte[] ipv4RouterId;
	private byte[] ipv6RouterId;
	
	public static final int ISO_NODE_ID_LENGTH = 7;
	public static final int IPV6_ROUTER_ID_LENGTH = 16;
	
	private boolean validAutonomousSystem = false;
	//private boolean validBgpIdentifier = false;  draft v2
	private boolean validMemberAS = false;
	
	/**
	 * Sets the type (local or remote) of node descriptor
	 * @param type node descriptor type
	 */
	public BgpLsNodeDescriptor(BgpLsType type) {
		this.type = type;
	}
	
	/**
	 * Gets the node descriptor type (local or remote)
	 * @return node descriptor type
	 */
	public BgpLsType getType() {
		return type;
	}
	
	/**
	 * Checks if the node descriptor is a well formed object. In other words, does
	 * the node descriptor contain it's mandatory attributes.
	 * @return
	 */
	public boolean isValidNodeDescriptor() {
		if (isoNodeId == null && ipv4RouterId == null && ipv6RouterId == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * Sets the autonomous system of this node descriptor
	 * @param autonomousSystem autonomous system
	 */
	public void setAutonomousSystem(long autonomousSystem) {
		this.autonomousSystem = autonomousSystem;
		validAutonomousSystem = true;
	}
	
	/**
	 * Gets the autonomous system of this node descriptor
	 * @return autonomous system
	 */
	public long getAutonomousSystem() {
		return autonomousSystem;
	}
	
	/**
	 * Returns TRUE if the autonomous system is valid
	 * @return
	 */
	public boolean validAutonomousSystem() {
		return validAutonomousSystem;
	}
	
	/* draft v2
	public void setBgpIdentifier(long bgpIdentifier) {
		this.bgpIdentifier = bgpIdentifier;
		validBgpIdentifier = true;
	}
	
	public boolean isValidBgpIdentifier() {
		return validBgpIdentifier;
	}
	
	public long getBgpIdentifier() {
		return bgpIdentifier;
	}*/
	
	/**
	 * Sets the ISO node id of this node descriptor
	 * @param isoNodeId ISO node id
	 */
	public void setIsoNodeId(byte[] isoNodeId) {
		this.isoNodeId = isoNodeId;
	}
	
	/**
	 * Gets the ISO node id of this node descriptor
	 * @return ISO node id
	 */
	public byte[] getIsoNodeId() {
		return isoNodeId;
	}
	
	/**
	 * Converts the ISO node id into a string
	 * @return String form of ISO node id
	 */
	public String getIsoNodeIdString() {
		if (isoNodeId != null) {
			int p1 = (int)isoNodeId[0] << 8 | (int)isoNodeId[1];
			int p2 = (int)isoNodeId[2] << 8 | (int)isoNodeId[3]; 
			int p3 = (int)isoNodeId[4] << 8 | (int)isoNodeId[5];
			int p4 = (int)isoNodeId[6];
			return String.format("%1$d.%2$d,%3$d.%4$d", p1, p2, p3, p4);
		} else {
			return null;
		}
	}
	
	/**
	 * Sets the IPv4 router id of this node descriptor
	 * @param routerId IPv4 router id
	 */
	public void setIPv4RouterId(byte[] routerId) {
		this.ipv4RouterId = routerId;
	}
	
	/**
	 * Gets the IPv4 router id of this node descriptor
	 * @return IPv4 router id
	 */
	public byte[] getIPv4RouterId() {
		return this.ipv4RouterId;
	}
	
	/**
	 * Converts the IPv4 router id into a string
	 * @return String form of IPv4 router id
	 * @throws Exception
	 */
	public String getIPv4RouterIdString() throws Exception {
		byte[] ipData;
		
		if (ipv4RouterId != null) {
			if (ipv4RouterId.length == 5) {
				ipData = new byte[4];
				System.arraycopy(ipv4RouterId, 0, ipData, 0, 4);
			} else {
				ipData = ipv4RouterId;
			}
			
			return Inet4Address.getByAddress(ipData).toString();
		} else {
			return null;
		}
	}
	
	/**
	 * Checks if this node descriptor represents an pseudo-node
	 * @return
	 */
	public boolean isPseudoNode() {
		if ((ipv4RouterId != null && ipv4RouterId[4] != 0) ||
			(ipv6RouterId != null && ipv6RouterId[16] != 0) ||
			(isoNodeId != null && isoNodeId[6] != 0)) {
			return true;
		}
		
		return false;
	}

	/**
	 * Sets the IPv6 router id of this node descriptor
	 * @param routerId IPv6 router id
	 */
	public void setIPv6RouterId(byte[] routerId) {
		this.ipv6RouterId = routerId;
	}

	/**
	 * Gets the IPv6 router id of this node descriptor
	 * @return IPv6 router id
	 */
	public byte[] getIPv6RouterId() {
		return ipv6RouterId;
	}

	/**
	 * Converts the IPv6 router id into a string
	 * @return String form of IPv6 router id
	 * @throws Exception
	 */
	public String getIPv6RouterIdString() throws Exception {
		byte[] ipData;
		
		if (ipv6RouterId != null) {
			if (ipv6RouterId.length == 17) {
				ipData = new byte[16];
				System.arraycopy(ipv6RouterId, 0, ipData, 0, 16);
			} else {
				ipData = ipv6RouterId;
			}
			return Inet6Address.getByAddress(ipData).toString();
		} else {
			return null;
		}
	}
	
	/**
	 * Returns TRUE if the member AS is valid
	 * @return
	 */
	public boolean isValidMemberAS() {
		return validMemberAS;
	}
	
	/**
	 * Gets the member AS
	 * @return member AS
	 */
	public long getMemberAS() {
		return memberAS;
	}

	/**
	 * Sets the member AS
	 * @param memberAS member AS
	 */
	public void setMemberAS(long memberAS) {
		this.memberAS = memberAS;
		validMemberAS = true;
	}
}
