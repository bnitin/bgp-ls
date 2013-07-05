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

/**
 * @author nitinb
 *
 */
public class BgpLsIdentifier {
	
	//private static final BgpLsType type = BgpLsType.Identifier; draft v2
	private byte[] instanceId;
	private byte[] domainId;
	private byte[] areaIdentifier;
	private short ospfRouteType;
	private int multiTopologyId;
	
	BgpLsIdentifier() {}
	
	/* draft v2
	public BgpLsType getType() {
		return type;
	}*/
	
	public boolean isValidIdentifier() {
		if (instanceId == null) {
			return false;
		}	
		return true;
	}
	
	public void setInstanceIdentifier (byte[] instanceId) {
		this.instanceId = instanceId;
	}
	
	public byte[] getInstanceIdentifier() {
		return instanceId;
	}
	
	public void setDomainIdentifier (byte[] domainId) {
		this.domainId = domainId;
	}
	
	public byte[] getDomainIdentifier () {
		return domainId;
	}
	
	public void setAreaIdentifier (byte[] areaIdentifier) {
		this.areaIdentifier = areaIdentifier;
	}
	
	public byte[] getAreaIdentifier() {
		return areaIdentifier;
	}
	
	public void setOspfRouteType(short ospfRouteType) {
		this.ospfRouteType = ospfRouteType;
	}
	
	public short getOspfRouteType() {
		return ospfRouteType;
	}
	
	public void setMultiTopologyId(int multiTopologyId) {
		this.multiTopologyId = multiTopologyId;
	}
	
	public int getMultiTopologyId() {
		return multiTopologyId;
	}
}
