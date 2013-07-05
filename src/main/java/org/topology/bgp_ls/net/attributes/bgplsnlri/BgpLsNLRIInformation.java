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

import java.util.Set;

import org.topology.bgp_ls.net.AddressFamily;
import org.topology.bgp_ls.net.SubsequentAddressFamily;
import org.topology.bgp_ls.net.attributes.MultiProtocolNLRIInformation;
import org.topology.bgp_ls.net.attributes.PathAttribute;

/**
 * @author nitinb
 *
 */
public class BgpLsNLRIInformation extends MultiProtocolNLRIInformation {
	
	private BgpLsNLRIType nlriType;
	private byte[] routeDistinguisher;
		
	/**
	 * @param afi
	 * @param safi
	 */
	public BgpLsNLRIInformation(SubsequentAddressFamily safi, BgpLsNLRIType nlriType) {
		super(AddressFamily.BGP_LS, safi);
		this.nlriType = nlriType;
	}


	/**
	 * @return the nlriType
	 */
	public BgpLsNLRIType getNlriType() {
		return nlriType;
	}

	/**
	 * @return the routeDistinguisher
	 */
	public byte[] getRouteDistinguisher() {
		return routeDistinguisher;
	}

	/**
	 * @param routeDistinguisher the routeDistinguisher to set
	 */
	public void setRouteDistinguisher(byte[] routeDistinguisher) {
		if (getSubsequentAddressFamily() == SubsequentAddressFamily.NLRI_MPLS_VPN) {
			this.routeDistinguisher = routeDistinguisher;
		}
	}

	@Override
	public void processNLRIInformation(Set<PathAttribute> attrs) {
	}
}
