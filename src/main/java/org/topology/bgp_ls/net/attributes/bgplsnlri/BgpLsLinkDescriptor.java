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

import java.net.InetAddress;

/**
 * @author nitinb
 *
 */
public class BgpLsLinkDescriptor {
	private long linkLocalIdentifier = 0;
	private long linkRemoteIdentifier = 0;
	private InetAddress ipv4InterfaceAddress;
	private InetAddress ipv4NeighborAddress;
	private InetAddress ipv6InterfaceAddress;
	private InetAddress ipv6NeighborAddress;
	private int multiTopologyId = 0;
	
	private boolean validLinkLocalIdentifier = false;
	private boolean validLinkRemoteIdentifier = false;
	private boolean validIPv4InterfaceAddress = false;
	private boolean validIPv4NeighborAddress = false;
	private boolean validIPv6InterfaceAddress = false;
	private boolean validIPv6NeighborAddress = false;
	private boolean validMultiTopologyId = false;
	
	public BgpLsLinkDescriptor() {}
	
	public void setLinkLocalIdentifier(long linkLocalIdentifier) {
		this.linkLocalIdentifier = linkLocalIdentifier;
		validLinkLocalIdentifier = true;
	}
	
	public long getLinkLocalIdentifier() {
		return linkLocalIdentifier;
	}
	
	public boolean isValidLinkLocalIdentifier() {
		return validLinkLocalIdentifier;
	}
	
	public void setLinkRemoteIdentifier(long linkRemoteIdentifier) {
		this.linkRemoteIdentifier = linkRemoteIdentifier;
		validLinkRemoteIdentifier = true;
	}
	
	public long getLinkRemoteIdentifier() {
		return linkRemoteIdentifier;
	}
	
	public boolean isValidLinkRemoteIdentifier() {
		return validLinkRemoteIdentifier;
	}
	
	public void setIPv4InterfaceAddress(InetAddress address) {
		this.ipv4InterfaceAddress = address;
		validIPv4InterfaceAddress = true;
	}
	
	public InetAddress getIPv4InterfaceAddress() {
		return ipv4InterfaceAddress;
	}
	
	public boolean isValidIPv4InterfaceAddress() {
		return validIPv4InterfaceAddress;
	}
	
	public void setIPv4NeighborAddress(InetAddress address) {
		this.ipv4NeighborAddress = address;
		validIPv4NeighborAddress = true;
	}
	
	public InetAddress getIPv4NeighborAddress() {
		return ipv4NeighborAddress;
	}
	
	public boolean isValidIPv4NeighborAddress() {
		return validIPv4NeighborAddress;
	}
	
	public void setIPv6InterfaceAddress(InetAddress address) {
		this.ipv6InterfaceAddress = address;
		validIPv6InterfaceAddress = true;
	}
	
	public InetAddress getIPv6InterfaceAddress() {
		return ipv6InterfaceAddress;
	}
	
	public boolean isValidIPv6InterfaceAddress() {
		return validIPv6InterfaceAddress;
	}
	
	public void setIPv6NeighborAddress(InetAddress address) {
		this.ipv6NeighborAddress = address;
		validIPv6NeighborAddress = true;
	}
	
	public InetAddress getIPv6NeighborAddress() {
		return ipv6NeighborAddress;
	}
	
	public boolean isValidIPv6NeighborAddress() {
		return validIPv6NeighborAddress;
	}
	
	public void setMultiTopologyId(int multiTopologyId) {
		this.multiTopologyId = multiTopologyId;
		validMultiTopologyId = true;
	}
	
	public int getMultiTopologyId() {
		return multiTopologyId;
	}
	
	public boolean isValidMultiTopologyId() {
		return validMultiTopologyId;
	}
}
