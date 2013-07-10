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
import org.topology.bgp_ls.net.attributes.bgplsnlri.BgpLsLinkDescriptor;
import org.topology.bgp_ls.net.attributes.bgplsnlri.BgpLsLinkNLRI;
import org.topology.bgp_ls.net.attributes.bgplsnlri.BgpLsNodeDescriptor;
import org.topology.bgp_ls.net.attributes.bgplsnlri.BgpLsType;
import org.topology.bgp_ls.net.attributes.export.MultiProtocolNLRIExport;
import org.codehaus.jackson.JsonGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exports a link object in JSON format
 * @author nitinb
 *
 */
public class BgpLsLinkNLRIExport {

	private static final Logger log = LoggerFactory.getLogger(BgpLsLinkNLRI.class);

	/**
	 * Exports a link object in JSON format
	 * @param linkNLRI link object
	 * @param attr link state attribute
	 * @throws Exception
	 */
	synchronized public static void export(MultiProtocolNLRIInformation linkNLRI, PathAttribute attr) throws Exception {
		LinkStateAttribute lsa = (LinkStateAttribute)attr;
		JsonGenerator exportWriter = (JsonGenerator)MultiProtocolNLRIExport.getExportWriter();
		
		try {
			exportWriter.writeFieldName("link");
			exportWriter.writeStartObject();
			writeNodeDescriptors(exportWriter, (BgpLsLinkNLRI)linkNLRI);
			writeLinkAttribute(exportWriter, lsa);			
			exportWriter.writeEndObject();
		} catch(Exception e) {
			log.error("Failed to export BGP-LS Link NLRI", e);
			throw e;
		}
	}
	
	/**
	 * Exports the node descriptors in JSON format
	 * @param exportWriter handle to json writer
	 * @param linkNLRI link object
	 * @throws Exception
	 */
	private static void writeNodeDescriptors(JsonGenerator exportWriter, BgpLsLinkNLRI linkNLRI) throws Exception {
		BgpLsNodeDescriptor localDescriptor = linkNLRI.getLocalNodeDescriptors();
		BgpLsNodeDescriptor remoteDescriptor = linkNLRI.getRemoteNodeDescriptors();
		BgpLsLinkDescriptor ld = linkNLRI.getLinkDescriptors();
		BgpLsType idType = BgpLsLinkNLRI.lookupCommonRouterId(localDescriptor, remoteDescriptor);

		try {
			// write the local node info
			exportWriter.writeFieldName("local");
			exportWriter.writeStartObject();
			switch (idType) {
			case IPv4RouterId:
				exportWriter.writeStringField("ipv4 node-id", localDescriptor.getIPv4RouterIdString());	
				break;
			case IPv6RouterId:
				exportWriter.writeStringField("ipv6 node-id", localDescriptor.getIPv6RouterIdString());	
				break;
			case ISONodeId:
				exportWriter.writeStringField("iso node-id", localDescriptor.getIsoNodeIdString());
				break;
			default:
				break;
			}
			if (localDescriptor.isPseudoNode()) {
				exportWriter.writeBooleanField("PseudoNode", true);
			}
			if (ld != null) {
				if (ld.isValidIPv4InterfaceAddress()) {
					exportWriter.writeStringField("ipv4 address", ld.getIPv4InterfaceAddress().toString());
				}
				if (ld.isValidIPv6InterfaceAddress()) {
					exportWriter.writeStringField("ipv6 address", ld.getIPv6InterfaceAddress().toString());
				}
			}
			exportWriter.writeEndObject();

		} catch(Exception e) {
			log.error("Failed to export Link NLRI local descriptor", e);
			throw e;
		}
		
		try {
			// write the remote node info
			exportWriter.writeFieldName("remote");
			exportWriter.writeStartObject();
			switch (idType) {
			case IPv4RouterId:
				exportWriter.writeStringField("ipv4 node-id", remoteDescriptor.getIPv4RouterIdString());	
				break;
			case IPv6RouterId:
				exportWriter.writeStringField("ipv6 node-id", remoteDescriptor.getIPv6RouterIdString());	
				break;
			case ISONodeId:
				exportWriter.writeStringField("iso node-id", remoteDescriptor.getIsoNodeIdString());
				break;
			default:
				break;
			}
			if (remoteDescriptor.isPseudoNode()) {
				exportWriter.writeBooleanField("PseudoNode", true);
			}
			if (ld != null) {
				if (ld.isValidIPv4NeighborAddress()) {
					exportWriter.writeStringField("ipv4 address", ld.getIPv4NeighborAddress().toString());
				}
				if (ld.isValidIPv6NeighborAddress()) {
					exportWriter.writeStringField("ipv6 address", ld.getIPv6NeighborAddress().toString());
				}
			}
			exportWriter.writeEndObject();
		} catch (Exception e) {
			log.error("Failed to export Link NLRI remote descriptor", e);
			throw e;
		}
	}
	
	/**
	 * Exports the link attributes in JSON format
	 * @param exportWriter handle to JSON writer
	 * @param attr link attributes
	 * @throws Exception
	 */
	synchronized private static void writeLinkAttribute(JsonGenerator exportWriter, LinkStateAttribute attr) throws Exception {
		
		if (attr == null) {
			return;
		}
		
		try {
			exportWriter.writeFieldName("attributes");
			exportWriter.writeStartObject();
			if (attr.isValidMaxReservableLinkBandwidth()) {
				exportWriter.writeNumberField("link bandwidth", attr.getMaxReservableLinkBandwidth());
			} else {
				exportWriter.writeNumberField("link bandwidth", attr.getMaxLinkBandwidth());
			}
			if (attr.getUnreservedBandwidth() != null) {
				exportWriter.writeNumberField("available bandwidth", attr.getUnreservedBandwidth()[0]);
			}
			exportWriter.writeEndObject();
		} catch (Exception e) {
			log.error("Failed to export Link NLRI link attribute", e);
			throw e;
		}
	}
}
