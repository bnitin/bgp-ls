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

import org.topology.bgp_ls.net.attributes.LinkStateAttribute;
import org.topology.bgp_ls.net.attributes.MultiProtocolNLRIInformation;
import org.topology.bgp_ls.net.attributes.PathAttribute;
import org.topology.bgp_ls.net.attributes.bgplsnlri.BgpLsNodeDescriptor;
import org.topology.bgp_ls.net.attributes.bgplsnlri.BgpLsNodeNLRI;
import org.topology.bgp_ls.net.attributes.export.MultiProtocolNLRIExport;
import org.codehaus.jackson.JsonGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exports a node object in JSON format
 * @author nitinb
 *
 */
public class BgpLsNodeNLRIExport {

	private static final Logger log = LoggerFactory.getLogger(BgpLsNodeNLRI.class);

	/**
	 * Exports a node object in JSON format
	 * @param nodeNLRI node object
	 * @param attr link state attributes
	 * @throws Exception
	 */
	synchronized public static void export(MultiProtocolNLRIInformation nodeNLRI, PathAttribute attr) throws Exception {
		LinkStateAttribute lsa = (LinkStateAttribute)attr;
		JsonGenerator exportWriter = (JsonGenerator)MultiProtocolNLRIExport.getExportWriter();
		
		BgpLsNodeDescriptor localDescriptor = ((BgpLsNodeNLRI)nodeNLRI).getLocalNodeDescriptors();

		try {
			exportWriter.writeFieldName("node");
			exportWriter.writeStartObject();
			
			if (localDescriptor.getIPv4RouterId() != null) {
				exportWriter.writeStringField("ipv4 node-id", localDescriptor.getIPv4RouterIdString());	
			} 
			if (localDescriptor.getIPv6RouterId() != null) {
				exportWriter.writeStringField("ipv6 node-id", localDescriptor.getIPv6RouterIdString());	
			} 
			if (localDescriptor.getIsoNodeId() != null) {
				exportWriter.writeStringField("iso node-id", localDescriptor.getIsoNodeIdString());
			}
			if (localDescriptor.isPseudoNode()) {
				exportWriter.writeBooleanField("PseudoNode", true);
			}
			
			writeLinkAttribute(exportWriter, lsa);			
			exportWriter.writeEndObject(); // node
		} catch (Exception e) {
			log.error("Failed to export Node NLRI", e);
			throw e;
		}
	}
	
	private static void writeLinkAttribute (JsonGenerator exportWriter, LinkStateAttribute attr) {
		//TODO: nothing interesting to dump right now
	}
}
