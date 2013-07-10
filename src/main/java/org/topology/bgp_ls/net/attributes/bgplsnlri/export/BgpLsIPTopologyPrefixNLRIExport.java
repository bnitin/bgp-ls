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
package org.topology.bgp_ls.net.attributes.bgplsnlri.export;

import java.util.List;

import org.topology.bgp_ls.net.attributes.LinkStateAttribute;
import org.topology.bgp_ls.net.attributes.MultiProtocolNLRIInformation;
import org.topology.bgp_ls.net.attributes.PathAttribute;
import org.topology.bgp_ls.net.attributes.bgplsnlri.BgpLsIPTopologyPrefixNLRI;
import org.topology.bgp_ls.net.attributes.bgplsnlri.BgpLsNLRIType;
import org.topology.bgp_ls.net.attributes.bgplsnlri.IPPrefix;
import org.topology.bgp_ls.net.attributes.export.MultiProtocolNLRIExport;
import org.codehaus.jackson.JsonGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exports a topology prefix in JSON format
 * @author nitinb
 *
 */
public class BgpLsIPTopologyPrefixNLRIExport {

	private static final Logger log = LoggerFactory.getLogger(BgpLsIPTopologyPrefixNLRI.class);

	/**
	 * Exports the topology prefix in JSON format
	 * @param prefixNLRI prefix object
	 * @param attr link state attribute
	 * @throws Exception
	 */
	synchronized public static void export(MultiProtocolNLRIInformation prefixNLRI, PathAttribute attr) throws Exception {
		LinkStateAttribute lsa = (LinkStateAttribute)attr;
		JsonGenerator exportWriter = (JsonGenerator)MultiProtocolNLRIExport.getExportWriter();
		List<IPPrefix> prefixList = ((BgpLsIPTopologyPrefixNLRI)prefixNLRI).getPrefixList();
		BgpLsNLRIType nlriType = ((BgpLsIPTopologyPrefixNLRI)prefixNLRI).getNlriType();

		if (prefixList.isEmpty()) {
			return;
		}
		
		try {
			if (nlriType == BgpLsNLRIType.IPV4_TOPOLOGY_PREFIX_NLRI) {
				exportWriter.writeFieldName("prefix:ipv4");
			} else if (nlriType == BgpLsNLRIType.IPV6_TOPOLOGY_PREFIX_NLRI) {
				exportWriter.writeFieldName("prefix:ipv6");
			} else {
					throw new Exception("Unknown IP topology prefix type" + nlriType);
			}
			exportWriter.writeStartObject(); // prefix
		
			for (IPPrefix prefix: prefixList) {
				exportWriter.writeNullField(prefix.toString());
			}
			writeLinkAttribute(exportWriter, lsa);			
			exportWriter.writeEndObject(); // prefix
		} catch(Exception e) {
			log.error("Failed to export IP topology Prefix NLRI", e);
			throw e;
		}
	}
	
	private static void writeLinkAttribute (JsonGenerator exportWriter, LinkStateAttribute attr) {
		//TODO: nothing interesting to dump right now
	}
}
