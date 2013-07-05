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

import java.util.List;
import java.util.Set;

import org.topology.bgp_ls.net.attributes.MultiProtocolNLRI;
import org.topology.bgp_ls.net.attributes.MultiProtocolNLRIInformation;
import org.topology.bgp_ls.net.attributes.PathAttribute;
import org.topology.bgp_ls.net.attributes.PathAttributeType;
import org.topology.bgp_ls.netty.SingletonRegistry;
import org.codehaus.jackson.JsonGenerator;

/**
 * @author nitinb
 *
 */
public class MultiProtocolNLRIExport {

	private static JsonGenerator exportWriter = (JsonGenerator)SingletonRegistry.getInstance(JsonGenerator.class.getName());

	public static Object getExportWriter() {
		return (Object)exportWriter;
	}
	
	synchronized public static void export(MultiProtocolNLRI nlri, Set<PathAttribute> attrs) throws Exception {
		List<MultiProtocolNLRIInformation> nlris = nlri.getNlris();
		
		if (exportWriter == null) {
			return;
		}
		
		exportWriter.writeStartObject();
		if (nlri.getPathAttributeType() == PathAttributeType.MULTI_PROTOCOL_REACHABLE) {
			exportWriter.writeObjectFieldStart("update"); 
		} else if (nlri.getPathAttributeType() == PathAttributeType.MULTI_PROTOCOL_UNREACHABLE) {
			exportWriter.writeObjectFieldStart("delete");
		} else {
			throw new Exception("Unknown multiprotocol attr type" + nlri.getPathAttributeType());
		}
		for (MultiProtocolNLRIInformation nlriInfo: nlris) {
			MultiProtocolNLRIInformationExport.export(nlriInfo, attrs);
		}
		exportWriter.writeEndObject();
		exportWriter.writeEndObject();
		
		exportWriter.flush();
	}
}
