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
package org.topology.bgp_ls.netty.protocol.update.bgplsnlri;

import org.topology.bgp_ls.net.attributes.bgplsnlri.BgpLsNodeDescriptor;
import org.topology.bgp_ls.net.attributes.bgplsnlri.BgpLsType;
import org.topology.bgp_ls.netty.protocol.update.OptionalAttributeErrorException;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Decodes a node descriptor tlv
 * @author nitinb
 *
 */
public class BgpLsNodeDescriptorCodec {

	private static final Logger log = LoggerFactory.getLogger(BgpLsNodeDescriptorCodec.class);

	/**
	 * Decodes a node descriptor tlv
	 * @param buffer Data stream containing the tlv
	 * @param nd node descriptor to update
	 */
	public static void decodeNodeDescriptor(ChannelBuffer buffer, BgpLsNodeDescriptor nd) {

		while(buffer.readable()) {
			int subType = buffer.readUnsignedShort();
			int valueLength = buffer.readUnsignedShort();
			
			ChannelBuffer valueBuffer = ChannelBuffers.buffer(valueLength);
			buffer.readBytes(valueBuffer);
			
			switch(BgpLsType.fromCode(subType)) {
			case AutonomousSystem:
				decodeAutonomousSystem(valueBuffer, nd);
				break;
			case MemberAS:
				decodeMemberAS(valueBuffer, nd);
				break;
			case ISONodeId:
				decodeISONodeId(valueBuffer, nd);
				break;
			case IPv4RouterId:
				decodeIPv4RouterId(valueBuffer, nd);
				break;
			case IPv6RouterId:
				decodeIPv6RouterId(valueBuffer, nd);
				break;
			default:
				log.error("Unsupported descriptor type " + subType);
				break;
			}
		}
	}

	/**
	 * Decodes IPv6 router id sub-tlv
	 * @param buffer Data stream containing the sub-tlv
	 * @param nd node descriptor to update
	 */
	private static void decodeIPv6RouterId(ChannelBuffer buffer,
			BgpLsNodeDescriptor nd) {
		try {
			if (buffer.readableBytes() != 17) {
				log.error("Invalid length (" + buffer.readableBytes() + ") for ipv6 router id");
				throw new OptionalAttributeErrorException();
			}
			byte[] routerId = new byte[17];
			buffer.readBytes(routerId);
			nd.setIPv6RouterId(routerId);
			
		} catch(RuntimeException e) {
			log.error("failed to decode ipv4 router id for node descriptor", e);
			throw new OptionalAttributeErrorException();
		}				
	}

	/**
	 * Decodes IPv4 router id sub-tlv
	 * @param buffer Data stream containing the sub-tlv
	 * @param nd node descriptor to update
	 */
	private static void decodeIPv4RouterId(ChannelBuffer buffer,
			BgpLsNodeDescriptor nd) {
		try {
			if (buffer.readableBytes() != 5) {
				log.error("Invalid length (" + buffer.readableBytes() + ") for ipv4 router id");
				throw new OptionalAttributeErrorException();
			}
			byte[] routerId = new byte[5];
			buffer.readBytes(routerId);
			nd.setIPv4RouterId(routerId);
			
		} catch(RuntimeException e) {
			log.error("failed to decode ipv4 router id for node descriptor", e);
			throw new OptionalAttributeErrorException();
		}		
	}

	/**
	 * Decodes ISO node id sub-tlv
	 * @param buffer Data stream containing the sub-tlv
	 * @param nd node descriptor to update
	 */
	private static void decodeISONodeId(ChannelBuffer buffer,
			BgpLsNodeDescriptor nd) {
		try {
			if (buffer.readableBytes() != 7) {
				log.error("Invalid length (" + buffer.readableBytes() + ") for ISO node id");
				throw new OptionalAttributeErrorException();
			}
			byte[] isoNodeId = new byte[7];
			buffer.readBytes(isoNodeId);
			nd.setIsoNodeId(isoNodeId);
			
		} catch(RuntimeException e) {
			log.error("failed to decode ISO node id for node descriptor", e);
			throw new OptionalAttributeErrorException();
		}				
	}

	/**
	 * Decodes member AS sub-tlv
	 * @param buffer Data stream containing the sub-tlv
	 * @param nd node descriptor to update
	 */
	private static void decodeMemberAS(ChannelBuffer buffer,
			BgpLsNodeDescriptor nd) {
		try {
			if (buffer.readableBytes() != 4) {
				log.error("Invalid length (" + buffer.readableBytes() + ") for member AS");
				throw new OptionalAttributeErrorException();
			}
			long memberAS = buffer.readUnsignedInt();
			nd.setMemberAS(memberAS);
			
		} catch(RuntimeException e) {
			log.error("failed to decode member AS for node descriptor", e);
			throw new OptionalAttributeErrorException();
		}
	}

	/**
	 * Decodes autonomous system sub-tlv
	 * @param buffer Data stream containing the sub-tlv
	 * @param nd node descriptor to update
	 */
	private static void decodeAutonomousSystem(ChannelBuffer buffer,
			BgpLsNodeDescriptor nd) {
		try {
			if (buffer.readableBytes() != 4) {
				log.error("Invalid length (" + buffer.readableBytes() + ") for autonomous system");
				throw new OptionalAttributeErrorException();
			}
			long autonomousSystem = buffer.readUnsignedInt();
			nd.setAutonomousSystem(autonomousSystem);
			
		} catch(RuntimeException e) {
			log.error("failed to decode Autonomous system for node descriptor", e);
			throw new OptionalAttributeErrorException();
		}				
	}
}
