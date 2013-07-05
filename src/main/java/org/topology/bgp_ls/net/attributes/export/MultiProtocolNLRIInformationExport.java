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
package org.topology.bgp_ls.net.attributes.export;

import java.util.Set;

import org.topology.bgp_ls.net.attributes.LinkStateAttribute;
import org.topology.bgp_ls.net.attributes.MultiProtocolNLRIInformation;
import org.topology.bgp_ls.net.attributes.PathAttribute;
import org.topology.bgp_ls.net.attributes.bgplsnlri.BgpLsLinkNLRI;
import org.topology.bgp_ls.net.attributes.bgplsnlri.BgpLsNodeNLRI;
import org.topology.bgp_ls.net.attributes.bgplsnlri.BgpLsIPTopologyPrefixNLRI;
import org.topology.bgp_ls.net.attributes.bgplsnlri.export.BgpLsLinkNLRIExport;
import org.topology.bgp_ls.net.attributes.bgplsnlri.export.BgpLsNodeNLRIExport;
import org.topology.bgp_ls.net.attributes.bgplsnlri.export.BgpLsIPTopologyPrefixNLRIExport;


/**
 * @author nitinb
 *
 */
public class MultiProtocolNLRIInformationExport {

	private static PathAttribute getLinkStateAttr(Set<PathAttribute> attrs) {
		for(PathAttribute pa : attrs) {
			if(pa.getClass().equals(LinkStateAttribute.class)) {
				return pa;
			}
		}
		return null;
	}
		
	public static void export(MultiProtocolNLRIInformation nlri, Set<PathAttribute> attrs) throws Exception {
		if (nlri instanceof BgpLsLinkNLRI) {
			BgpLsLinkNLRIExport.export(nlri, getLinkStateAttr(attrs));
		} else if (nlri instanceof BgpLsNodeNLRI) {
			BgpLsNodeNLRIExport.export(nlri, getLinkStateAttr(attrs));
		} else if (nlri instanceof BgpLsIPTopologyPrefixNLRI) {
			BgpLsIPTopologyPrefixNLRIExport.export(nlri, getLinkStateAttr(attrs));
		}
	}
}
