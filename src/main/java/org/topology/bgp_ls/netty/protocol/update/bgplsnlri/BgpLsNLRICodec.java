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

import org.topology.bgp_ls.net.AddressFamily;
import org.topology.bgp_ls.net.NetworkLayerReachabilityInformation;
import org.topology.bgp_ls.net.SubsequentAddressFamily;
import org.topology.bgp_ls.net.attributes.MultiProtocolNLRIInformation;
import org.topology.bgp_ls.net.attributes.bgplsnlri.BgpLsIPTopologyPrefixNLRI;
import org.topology.bgp_ls.net.attributes.bgplsnlri.BgpLsLinkDescriptor;
import org.topology.bgp_ls.net.attributes.bgplsnlri.BgpLsLinkNLRI;
import org.topology.bgp_ls.net.attributes.bgplsnlri.BgpLsNLRIInformation;
import org.topology.bgp_ls.net.attributes.bgplsnlri.BgpLsNLRIType;
import org.topology.bgp_ls.net.attributes.bgplsnlri.BgpLsNodeDescriptor;
import org.topology.bgp_ls.net.attributes.bgplsnlri.BgpLsNodeNLRI;
import org.topology.bgp_ls.net.attributes.bgplsnlri.BgpLsProtocolId;
import org.topology.bgp_ls.net.attributes.bgplsnlri.BgpLsType;
import org.topology.bgp_ls.net.attributes.bgplsnlri.IPPrefix;
import org.topology.bgp_ls.netty.protocol.update.MultiProtocolNLRICodec;
import org.topology.bgp_ls.netty.protocol.update.OptionalAttributeErrorException;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author nitinb
 *
 */
public class BgpLsNLRICodec extends MultiProtocolNLRICodec {	
	private static final Logger log = LoggerFactory.getLogger(BgpLsNLRICodec.class);

	public MultiProtocolNLRIInformation decodeNLRI(ChannelBuffer buffer) {
		
		int type = buffer.readUnsignedShort();
		int valueLength = buffer.readUnsignedShort();

		// not enough bytes in the buffer to read the NLRI
		if (buffer.readableBytes() < valueLength) {
			log.error("Failed to decode BGP-LS NLRI type " + type + " due to completely read NLRI");
			return null;
		}
		
		ChannelBuffer valueBuffer = ChannelBuffers.buffer(valueLength);
		buffer.readBytes(valueBuffer);
	
		return decodeNLRIInternal(valueBuffer, SubsequentAddressFamily.NLRI_BGP_LS, type);
	}

	protected BgpLsNLRIInformation decodeNLRIInternal(ChannelBuffer buffer, SubsequentAddressFamily safi, int type) {
		
		BgpLsNLRIInformation nlri = null;

		switch(BgpLsNLRIType.fromCode(type)) {
		case LINK_NLRI:
			nlri = decodeBgpLsLinkNLRI(buffer, safi);
			break;
		case NODE_NLRI:
			nlri = decodeBgpLsNodeNLRI(buffer, safi);
			break;
		case IPV4_TOPOLOGY_PREFIX_NLRI:
			nlri = decodeIPTopologyPrefixNLRI(buffer, safi, BgpLsNLRIType.fromCode(type));
			break;
		case IPV6_TOPOLOGY_PREFIX_NLRI:
			nlri = decodeIPTopologyPrefixNLRI(buffer, safi, BgpLsNLRIType.fromCode(type));
			break;
		default:
			log.error("Unsupported BGP-LS NLRI type "+ type);
			return null;
		}
	
		return nlri;
	}
	
	/**
	 * @param safi
	 * @param fromCode
	 * @return
	 */
	private BgpLsNLRIInformation decodeIPTopologyPrefixNLRI(
			ChannelBuffer buffer, SubsequentAddressFamily safi, BgpLsNLRIType family) {

		BgpLsIPTopologyPrefixNLRI nlri = new BgpLsIPTopologyPrefixNLRI(safi, family);
		AddressFamily addressFamily;
		
		if (family == BgpLsNLRIType.IPV4_TOPOLOGY_PREFIX_NLRI) {
			addressFamily = AddressFamily.IPv4;
		} else {
			addressFamily = AddressFamily.IPv6;
		}
		
		try {
			short protocolId = buffer.readUnsignedByte();
			nlri.setProtocolId(BgpLsProtocolId.fromCode(protocolId));
			buffer.readUnsignedByte(); // reserved
			int instanceIdentifier = buffer.readUnsignedShort();
			nlri.setInstanceIdentifier(instanceIdentifier);
			
			// get local node descriptor
			int type = buffer.readUnsignedShort();
			int length = buffer.readUnsignedShort();
				
			if (buffer.readableBytes() < length) {
				log.error("Unsufficient bytes (expected " + length + ", got " + buffer.readableBytes() +
								") to decode BGP-LS link nlri");
				throw new OptionalAttributeErrorException();
			}
			ChannelBuffer valueBuffer = ChannelBuffers.buffer(length);
			buffer.readBytes(valueBuffer);
				
			switch(BgpLsType.fromCode(type)) {
			case LocalNodeDescriptor:
				BgpLsNodeDescriptor nodeDescriptor = new BgpLsNodeDescriptor(BgpLsType.fromCode(type));
				BgpLsNodeDescriptorCodec.decodeNodeDescriptor(valueBuffer, nodeDescriptor);
				nlri.setLocalNodeDescriptors(nodeDescriptor);
				break;
			default:
				log.error("Unknown descriptor of type " + type);
				byte[] junk = new byte[length];
				buffer.readBytes(junk);
				break;
			}
			
			// get list of IP prefixes
			while(buffer.readable()) {
				short prefixLength = buffer.readUnsignedByte();
				if (prefixLength != 4 && prefixLength != 16) {
					log.error("Invalid prefix length" + prefixLength);
					throw new OptionalAttributeErrorException();
				}
				
				byte[] prefix = new byte[prefixLength];
				buffer.readBytes(prefix);
				nlri.addPrefix(new IPPrefix(prefixLength, prefix, addressFamily));
			}
			
		} catch (RuntimeException e) {
			log.error("Unable to decode IP Prefix NLRI");
			throw new OptionalAttributeErrorException();
		}
		return nlri;
	}

	/**
	 * @param safi
	 * @return
	 */
	private BgpLsNLRIInformation decodeBgpLsNodeNLRI(ChannelBuffer buffer, SubsequentAddressFamily safi) {
		
		BgpLsNodeNLRI nlri = new BgpLsNodeNLRI(safi);

		try {
			short protocolId = buffer.readUnsignedByte();
			nlri.setProtocolId(BgpLsProtocolId.fromCode(protocolId));
			buffer.readUnsignedByte(); // reserved
			int instanceIdentifier = buffer.readUnsignedShort();
			nlri.setInstanceIdentifier(instanceIdentifier);
		
			// get local node descriptor
			int type = buffer.readUnsignedShort();
			int length = buffer.readUnsignedShort();
				
			if (buffer.readableBytes() < length) {
				log.error("Unsufficient bytes (expected " + length + ", got " + buffer.readableBytes() +
								") to decode BGP-LS link nlri");
				throw new OptionalAttributeErrorException();
			}
			ChannelBuffer valueBuffer = ChannelBuffers.buffer(length);
			buffer.readBytes(valueBuffer);
				
			switch(BgpLsType.fromCode(type)) {
			case LocalNodeDescriptor:
				BgpLsNodeDescriptor nodeDescriptor = new BgpLsNodeDescriptor(BgpLsType.fromCode(type));
				BgpLsNodeDescriptorCodec.decodeNodeDescriptor(valueBuffer, nodeDescriptor);
				nlri.setLocalNodeDescriptors(nodeDescriptor);
				break;
			default:
				log.error("Unknown descriptor of type " + type);
				byte[] junk = new byte[length];
				buffer.readBytes(junk);
				break;
			}
			
		} catch (RuntimeException e) {
			log.error("Unable to decode IP Prefix NLRI");
			throw new OptionalAttributeErrorException();
		}
		
		return nlri;
	}

	/**
	 * @param safi
	 * @return
	 */
	private BgpLsNLRIInformation decodeBgpLsLinkNLRI(ChannelBuffer buffer, SubsequentAddressFamily safi) {
		BgpLsLinkNLRI nlri = new BgpLsLinkNLRI(safi);
		ChannelBuffer valueBuffer;
		
		try {
			short protocolId = buffer.readUnsignedByte();
			nlri.setProtocolId(BgpLsProtocolId.fromCode(protocolId));
			
			buffer.readUnsignedByte(); // reserved
			
			int instanceIdentifier = buffer.readUnsignedShort();
			nlri.setInstanceIdentifier(instanceIdentifier);
		
			// get local node descriptor
			int type = buffer.readUnsignedShort();
			int length = buffer.readUnsignedShort();
				
			if (buffer.readableBytes() < length) {
				log.error("Unsufficient bytes (expected " + length + ", got " + buffer.readableBytes() +
								") to decode BGP-LS link nlri");
				throw new OptionalAttributeErrorException();
			}
			valueBuffer = ChannelBuffers.buffer(length);
			buffer.readBytes(valueBuffer);
				
			switch(BgpLsType.fromCode(type)) {
			case LocalNodeDescriptor:
				BgpLsNodeDescriptor nodeDescriptor = new BgpLsNodeDescriptor(BgpLsType.fromCode(type));
				BgpLsNodeDescriptorCodec.decodeNodeDescriptor(valueBuffer, nodeDescriptor);
				nlri.setLocalNodeDescriptors(nodeDescriptor);
				break;
			default:
				log.error("Unknown descriptor of type " + type);
				byte[] junk = new byte[length];
				buffer.readBytes(junk);
				break;
			}
			
			// get remote node descriptor
			type = buffer.readUnsignedShort();
			length = buffer.readUnsignedShort();
				
			if (buffer.readableBytes() < length) {
				log.error("Unsufficient bytes (expected " + length + ", got " + buffer.readableBytes() +
								") to decode BGP-LS link nlri");
				throw new OptionalAttributeErrorException();
			}
			valueBuffer = ChannelBuffers.buffer(length);
			buffer.readBytes(valueBuffer);
				
			switch(BgpLsType.fromCode(type)) {
			case RemoteNodeDescriptor:
				BgpLsNodeDescriptor nodeDescriptor = new BgpLsNodeDescriptor(BgpLsType.fromCode(type));
				BgpLsNodeDescriptorCodec.decodeNodeDescriptor(valueBuffer, nodeDescriptor);
				nlri.setRemoteNodeDescriptors(nodeDescriptor);
				break;
			default:
				log.error("Unknown descriptor of type " + type);
				byte[] junk = new byte[length];
				buffer.readBytes(junk);
				break;
			}
						
			// get link descriptors
			if(buffer.readable()) {
				BgpLsLinkDescriptor linkDescriptors = new BgpLsLinkDescriptor();
				BgpLsLinkDescriptorCodec.decodeLinkDescriptor(buffer, linkDescriptors);
				nlri.setLinkDescriptors(linkDescriptors);
			}
			
		} catch (RuntimeException e) {
			log.error("Unable to decode IP Prefix NLRI");
			throw new OptionalAttributeErrorException();
		}
		
		return nlri;
	}

	public int calculateEncodedNLRILength(NetworkLayerReachabilityInformation nlri) {
		return 0;
	}

	public ChannelBuffer encodeNLRI(NetworkLayerReachabilityInformation nlri) {
		
		return null;
	}
}
