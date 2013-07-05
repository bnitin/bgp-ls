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
package org.topology.bgp_ls.netty.protocol.update;

import org.topology.bgp_ls.net.attributes.LinkStateAttribute;
import org.topology.bgp_ls.net.attributes.bgplsnlri.BgpLsType;
import org.jboss.netty.buffer.ChannelBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author nitinb
 *
 */
public class LinkStateAttributeCodec {

	private static final Logger log = LoggerFactory.getLogger(UpdatePacketDecoder.class);

	public LinkStateAttributeCodec() {}

	public void decodeAttr(LinkStateAttribute attr, int type, ChannelBuffer buffer) {
		switch (BgpLsType.fromCode(type)) {
		case AdminGroup:
			decodeAdminGroup(attr, buffer);
			break;
		case MaxLinkBandwidth:
			decodeMaxLinkBandwidth(attr, buffer);
			break;
		case MaxReservableLinkBandwidth:
			decodeMaxReservableLinkBandwidth(attr, buffer);
			break;
		case UnreservedBandwidth:
			decodeUnreservedBandwidth(attr, buffer);
			break;
		//case TEDefaultMetric: draft v2
		//	decodeTEDefaultMetric(attr, buffer);
		//	break;
		case LinkProtectionType:
			decodeLinkProtectionType(attr, buffer);
			break;
		case MPLSProtocolMask:
			decodeMPLSProtocolMask(attr, buffer);
			break;
		case Metric:
			decodeMetric(attr, buffer);
			break;
		case SharedRiskLinkGroup:
			decodeSharedRiskLinkGroup(attr, buffer);
			break;
		case OSPFLinkAttribute:
			decodeOSPFLinkAttribute(attr, buffer);
			break;
		case ISISLinkAttribute:
			decodeISISLinkAttribute(attr, buffer);
			break;
		case LinkAreaId:
			decodeLinkAreaId(attr, buffer);
			break;
		case NodeMultiTopologyId:
			decodeNodeMultiTopologyId(attr, buffer);
			break;
		case NodeFlagBits:
			decodeNodeFlagBits(attr, buffer);
			break;
		case OSPFNodeProperties:
			decodeOspfNodeProperties(attr, buffer);
			break;
		case ISISNodeProperties:
			decodeIsisNodeProperities(attr, buffer);
			break;
		case NodeAreaId:
			decodeNodeAreaId(attr, buffer);
			break;
		case IGPFlags:
			decodeIgpFlags(attr, buffer);
			break;
		case RouteTag:
			decodeRouteTag(attr, buffer);
			break;
		case ExtendedTag:
			decodeExtendedTag(attr, buffer);
			break;
		case PrefixMetric:
			decodePrefixMetric(attr, buffer);
			break;
		case OSPFForwardingAddress:
			decodeOspfForwardingAddress(attr, buffer);
			break;
		case Unknown:
		default:
			log.info("Unknown Link state attribute sub-type " + type);
			break;
		}
	}

	/**
	 * @param attr
	 * @param buffer
	 */
	private void decodeOspfForwardingAddress(LinkStateAttribute attr,
			ChannelBuffer buffer) {
		try {
			int length = buffer.readableBytes();
			byte[] ospfForwardingAddress = new byte[length];
			
			buffer.readBytes(ospfForwardingAddress);
			attr.setOspfForwardingAddress(ospfForwardingAddress);
			
		} catch(RuntimeException e) {
			log.error("failed to decode OSPF forwarding address LINK_STATE attribute", e);
			throw new OptionalAttributeErrorException();
		}		
	}

	/**
	 * @param attr
	 * @param buffer
	 */
	private void decodePrefixMetric(LinkStateAttribute attr,
			ChannelBuffer buffer) {
		try {
			if (buffer.readableBytes() != 4) {
				log.error("Invalid length (" + buffer.readableBytes() + ") for prefix metric");
				throw new OptionalAttributeErrorException();
			}
			long prefixMetric = buffer.readUnsignedInt();
			attr.setPrefixMetric(prefixMetric);
			
		} catch(RuntimeException e) {
			log.error("failed to decode Prefix metric LINK_STATE attribute", e);
			throw new OptionalAttributeErrorException();
		}				
	}

	/**
	 * @param attr
	 * @param buffer
	 */
	private void decodeExtendedTag(LinkStateAttribute attr, ChannelBuffer buffer) {
		try {
			int length = buffer.readableBytes();
			if (buffer.readableBytes() != 8) {
				log.error("Invalid length (" + buffer.readableBytes() + ") for extended tag");
				throw new OptionalAttributeErrorException();
			}
			byte[] extendedTag = new byte[length];
			
			buffer.readBytes(extendedTag);
			attr.setExtendedTag(extendedTag);
			
		} catch(RuntimeException e) {
			log.error("failed to decode Extended tag LINK_STATE attribute", e);
			throw new OptionalAttributeErrorException();
		}				
	}

	/**
	 * @param attr
	 * @param buffer
	 */
	private void decodeRouteTag(LinkStateAttribute attr, ChannelBuffer buffer) {
		try {
			if (buffer.readableBytes() != 4) {
				log.error("Invalid length (" + buffer.readableBytes() + ") for route tag");
				throw new OptionalAttributeErrorException();
			}
			long routeTag = buffer.readUnsignedInt();
			attr.setRouteTag(routeTag);
			
		} catch(RuntimeException e) {
			log.error("failed to decode Route tag LINK_STATE attribute", e);
			throw new OptionalAttributeErrorException();
		}				
	}

	/**
	 * @param attr
	 * @param buffer
	 */
	private void decodeIgpFlags(LinkStateAttribute attr, ChannelBuffer buffer) {
		try {
			if (buffer.readableBytes() != 4) {
				log.error("Invalid length (" + buffer.readableBytes() + ") for igp flags");
				throw new OptionalAttributeErrorException();
			}
			long igpFlags = buffer.readUnsignedInt();
			attr.setIgpFlags(igpFlags);
			
		} catch(RuntimeException e) {
			log.error("failed to decode IGP Flags LINK_STATE attribute", e);
			throw new OptionalAttributeErrorException();
		}		
	}

	/**
	 * @param attr
	 * @param buffer
	 */
	private void decodeNodeAreaId(LinkStateAttribute attr, ChannelBuffer buffer) {
		try {
			int length = buffer.readableBytes();
			byte[] nodeAreaId = new byte[length];
			
			buffer.readBytes(nodeAreaId);
			attr.setNodeAreaId(nodeAreaId);
			
		} catch(RuntimeException e) {
			log.error("failed to decode Area Identifier LINK_STATE attribute", e);
			throw new OptionalAttributeErrorException();
		}		
	}

	/**
	 * @param attr
	 * @param buffer
	 */
	private void decodeIsisNodeProperities(LinkStateAttribute attr,
			ChannelBuffer buffer) {
		try {
			int length;
			byte[] isisNodeProperties;
			
			length = buffer.readableBytes();
			
			if (length == 0) {
				log.error("Invalid zero length of ISIS node properties");
				throw new OptionalAttributeErrorException();
			}
			
			isisNodeProperties = new byte[length];
			buffer.readBytes(isisNodeProperties);
			
			attr.setIsisNodeProperties(isisNodeProperties);
			
		} catch(RuntimeException e) {
			log.error("failed to decode ISIS node properties LINK_STATE attribute", e);
			throw new OptionalAttributeErrorException();
		}		
	}

	/**
	 * @param attr
	 * @param buffer
	 */
	private void decodeOspfNodeProperties(LinkStateAttribute attr,
			ChannelBuffer buffer) {
		try {
			int length;
			byte[] ospfNodeProperties;
			
			length = buffer.readableBytes();
			
			if (length == 0) {
				log.error("Invalid zero length of OSPF node properties");
				throw new OptionalAttributeErrorException();
			}
			
			ospfNodeProperties = new byte[length];
			buffer.readBytes(ospfNodeProperties);
			
			attr.setOspfNodeProperties(ospfNodeProperties);
			
		} catch(RuntimeException e) {
			log.error("failed to decode OSPF node properties LINK_STATE attribute", e);
			throw new OptionalAttributeErrorException();
		}			
	}

	/**
	 * @param attr
	 * @param buffer
	 */
	private void decodeNodeFlagBits(LinkStateAttribute attr,
			ChannelBuffer buffer) {
		try {
			if (buffer.readableBytes() != 1) {
				log.error("Invalid length (" + buffer.readableBytes() + ") for node flag bits");
				throw new OptionalAttributeErrorException();
			}
			short nodeFlagBits = buffer.readUnsignedByte();
			attr.setNodeFlagBits(nodeFlagBits);
			
		} catch(RuntimeException e) {
			log.error("failed to decode MPLS protocol mask LINK_STATE attribute", e);
			throw new OptionalAttributeErrorException();
		}		
		
	}

	/**
	 * @param attr
	 * @param buffer
	 */
	private void decodeNodeMultiTopologyId(LinkStateAttribute attr,
			ChannelBuffer buffer) {
		try {
			if (buffer.readableBytes() != 2) {
				log.error("Invalid length (" + buffer.readableBytes() + ") for node multi-topology id");
				throw new OptionalAttributeErrorException();
			}
			int nodeMultiTopologyId = buffer.readUnsignedShort();
			attr.setNodeMultiTopologyId(nodeMultiTopologyId);
			
		} catch(RuntimeException e) {
			log.error("failed to decode MPLS protocol mask LINK_STATE attribute", e);
			throw new OptionalAttributeErrorException();
		}		
	}

	/**
	 * @param attr
	 * @param buffer
	 */
	private void decodeISISLinkAttribute(LinkStateAttribute attr,
			ChannelBuffer buffer) {
		try {
			int length;
			byte[] isisLinkAttribute;
			
			length = buffer.readableBytes();
			
			if (length == 0) {
				log.error("Invalid zero length of ISIS link attribute");
				throw new OptionalAttributeErrorException();
			}
			
			isisLinkAttribute = new byte[length];
			buffer.readBytes(isisLinkAttribute);
			
			attr.setIsisLinkAttribute(isisLinkAttribute);
			
		} catch(RuntimeException e) {
			log.error("failed to decode ISIS specific LINK_STATE attribute", e);
			throw new OptionalAttributeErrorException();
		}		
	}

	/**
	 * @param attr
	 * @param buffer
	 */
	private void decodeOSPFLinkAttribute(LinkStateAttribute attr,
			ChannelBuffer buffer) {
		try {
			int length;
			byte[] ospfLinkAttribute;
			
			length = buffer.readableBytes();
			if (length == 0) {
				log.error("Invalid zero length of OSPF link attribute");
				throw new OptionalAttributeErrorException();
			}
			
			ospfLinkAttribute = new byte[length];
			buffer.readBytes(ospfLinkAttribute);
			
			attr.setOspfLinkAttribute(ospfLinkAttribute);
			
		} catch(RuntimeException e) {
			log.error("failed to decode OSPF specific LINK_STATE attribute", e);
			throw new OptionalAttributeErrorException();
		}		
		
	}

	/**
	 * @param attr
	 * @param buffer
	 */
	private void decodeSharedRiskLinkGroup(LinkStateAttribute attr,
			ChannelBuffer buffer) {
		
		try {
			int bufferSize = buffer.readableBytes();
			if (bufferSize % 4 != 0) {
				log.error("SRLG link attribute length (" + bufferSize + ") is not a multiple of 4");
				throw new OptionalAttributeErrorException();
			}
			int numGroups = bufferSize / 4;
			
			for(int i = 0; i < numGroups; i++) {
				attr.addSharedRiskLinkGroup(buffer.readUnsignedInt());
			}
		} catch(RuntimeException e) {
			log.error("failed to decode SRLG LINK_STATE attribute", e);
			throw new OptionalAttributeErrorException();
		}		
	}

	/**
	 * @param attr
	 * @param buffer
	 */
	private void decodeMetric(LinkStateAttribute attr, ChannelBuffer buffer) {
		try {
			if (buffer.readableBytes() != 3) {
				log.error("Invalid length (" + buffer.readableBytes() + ") for metric");
				throw new OptionalAttributeErrorException();
			}
			int metric = buffer.readUnsignedShort();
			metric = (metric << 8) | buffer.readUnsignedByte();
			attr.setMetric(metric);
			
		} catch(RuntimeException e) {
			log.error("failed to decode Metric LINK_STATE attribute", e);
			throw new OptionalAttributeErrorException();
		}		
	}

	/**
	 * @param attr
	 * @param buffer
	 */
	private void decodeMPLSProtocolMask(LinkStateAttribute attr,
			ChannelBuffer buffer) {
		try {
			if (buffer.readableBytes() != 1) {
				log.error("Invalid length (" + buffer.readableBytes() + ") for MPLS protocol mask");
				throw new OptionalAttributeErrorException();
			}
			short mplsProtocolMask = buffer.readUnsignedByte();
			attr.setMplsProtocolMask(mplsProtocolMask);
			
		} catch(RuntimeException e) {
			log.error("failed to decode MPLS protocol mask LINK_STATE attribute", e);
			throw new OptionalAttributeErrorException();
		}		
	}

	/**
	 * @param attr
	 * @param buffer
	 */
	private void decodeLinkProtectionType(LinkStateAttribute attr,
			ChannelBuffer buffer) {
		try {
			if (buffer.readableBytes() != 2) {
				log.error("Invalid length (" + buffer.readableBytes() + ") for link protection type");
				throw new OptionalAttributeErrorException();
			}
			
			short linkProtectionType = buffer.readUnsignedByte();
			attr.setLinkProtectionType(linkProtectionType);
			
		} catch(RuntimeException e) {
			log.error("failed to decode Link protection type LINK_STATE attribute", e);
			throw new OptionalAttributeErrorException();
		}		
	}

	/* draft v2
	private void decodeTEDefaultMetric(LinkStateAttribute attr,
			ChannelBuffer buffer) {
		try {
			if (buffer.readableBytes() != 3) {
				log.error("Invalid length (" + buffer.readableBytes() + ") for TE default metric");
				throw new OptionalAttributeErrorException();
			}
			int teDefaultMetric = buffer.readUnsignedShort();
			teDefaultMetric = (teDefaultMetric << 8) | buffer.readUnsignedByte();
			attr.setTeDefaultMetric(teDefaultMetric);
			
		} catch(RuntimeException e) {
			log.error("failed to decode TE default metric LINK_STATE attribute", e);
			throw new OptionalAttributeErrorException();
		}		
	}*/

	/**
	 * @param attr
	 * @param buffer
	 */
	private void decodeUnreservedBandwidth(LinkStateAttribute attr,
			ChannelBuffer buffer) {
		try {
			if (buffer.readableBytes() != 8*4) {
				log.error("Invalid length (" + buffer.readableBytes() + ") for Unreserved bandwidth");
				throw new OptionalAttributeErrorException();
			}
			
			float[] unreservedBandwidth = new float[8];
			for (int i = 0; i < 8; i++) {
				unreservedBandwidth[i] = readIEEEfloat(buffer) * 8;
			}
			attr.setUnreservedBandwidth(unreservedBandwidth);
			
		} catch(RuntimeException e) {
			log.error("failed to decode Unreserved bandwidth LINK_STATE attribute", e);
			throw new OptionalAttributeErrorException();
		}		
	}

	/**
	 * @param attr
	 * @param buffer
	 */
	private void decodeMaxLinkBandwidth(LinkStateAttribute attr,
			ChannelBuffer buffer) {
		try {
			if (buffer.readableBytes() != 4) {
				log.error("Invalid length (" + buffer.readableBytes() + ") for max link bandwidth");
				throw new OptionalAttributeErrorException();
			}
			attr.setMaxLinkBandwidth(readIEEEfloat(buffer) * 8);
			
		} catch(RuntimeException e) {
			log.error("failed to decode Max link bandwidth LINK_STATE attribute", e);
			throw new OptionalAttributeErrorException();
		}		
	}

	/**
	 * @param attr
	 * @param buffer
	 */
	private void decodeMaxReservableLinkBandwidth(LinkStateAttribute attr,
			ChannelBuffer buffer) {
		try {
			if (buffer.readableBytes() != 4) {
				log.error("Invalid length (" + buffer.readableBytes() + ") for max link bandwidth");
				throw new OptionalAttributeErrorException();
			}
			attr.setMaxReservableLinkBandwidth(readIEEEfloat(buffer) * 8);
			
		} catch(RuntimeException e) {
			log.error("failed to decode Max link bandwidth LINK_STATE attribute", e);
			throw new OptionalAttributeErrorException();
		}		
	}
	
	/**
	 * @param attr
	 * @param buffer
	 */
	private void decodeAdminGroup(LinkStateAttribute attr, ChannelBuffer buffer) {
		try {
			if (buffer.readableBytes() != 4) {
				log.error("Invalid length (" + buffer.readableBytes() + ") for admin group");
				throw new OptionalAttributeErrorException();
			}
			
			long adminGroup = buffer.readUnsignedInt();
			attr.setAdminGroup(adminGroup);
			
		} catch(RuntimeException e) {
			log.error("failed to decode Admin group LINK_STATE attribute", e);
			throw new OptionalAttributeErrorException();
		}		
	}

	/**
	 * @param attr
	 * @param buffer
	 */
	private void decodeLinkAreaId(LinkStateAttribute attr, ChannelBuffer buffer) {
		try {
			if (buffer.readableBytes() != 4) {
				log.error("Invalid length (" + buffer.readableBytes() + ") for link area id");
				throw new OptionalAttributeErrorException();
			}
			
			long linkAreaId = buffer.readUnsignedInt();
			attr.setLinkAreaId(linkAreaId);
			
		} catch(RuntimeException e) {
			log.error("failed to decode link area-id LINK_STATE attribute", e);
			throw new OptionalAttributeErrorException();
		}
	}
	
	/* draft v2
	private void decodeIdentifier(LinkStateAttribute attr, ChannelBuffer buffer) {
		try {
			int type = buffer.readUnsignedShort();
			int valueLength = buffer.readUnsignedShort();
			ChannelBuffer valueBuffer = ChannelBuffers.buffer(valueLength);
			buffer.readBytes(valueBuffer);

			switch(BgpLsIdentifierType.fromCode(type)) {
			case Area:
				decodeAreaIdentifier(attr, valueBuffer);
				break;
			default:
				log.error("Unknown identifier sub-type " + type);
				break;
			}
			
		} catch(RuntimeException e) {
			log.error("failed to decode Identifier LINK_STATE attribute", e);
			throw new OptionalAttributeErrorException();
		}		
	} */
	
	/**
	 * Reads a floating point value encoded in IEEE format and then
	 * converts it to a regular float
	 * @param buffer
	 * @return java float
	 */
	private float readIEEEfloat(ChannelBuffer buffer) {
		int value = buffer.readInt();
		value = (((value & 0xFF) << 24) | (((value >> 8) & 0xFF) << 16) | (((value >> 16) & 0xFF) << 8) |
                ((value >> 24) & 0xFF));
		return Float.intBitsToFloat(value);
	}
}
