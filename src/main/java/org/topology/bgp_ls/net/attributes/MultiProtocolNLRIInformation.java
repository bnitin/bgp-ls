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
package org.topology.bgp_ls.net.attributes;

import java.util.Set;

import org.topology.bgp_ls.net.AddressFamily;
import org.topology.bgp_ls.net.SubsequentAddressFamily;

/**
 * @author nitinb
 *
 */
public class MultiProtocolNLRIInformation {
	AddressFamily afi;
	SubsequentAddressFamily safi;
	
	protected MultiProtocolNLRIInformation(AddressFamily afi, SubsequentAddressFamily safi) {
		this.afi = afi;
		this.safi = safi;
	}
	
	protected SubsequentAddressFamily getSubsequentAddressFamily() {
		return safi;
	}
	
	public void processNLRIInformation(Set<PathAttribute> attrs) {
	}
}
