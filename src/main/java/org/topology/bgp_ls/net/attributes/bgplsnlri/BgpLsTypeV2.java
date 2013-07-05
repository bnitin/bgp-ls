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
 * The TLV/sub-tlv types are based on draft-ietf-idr-ls-distribution-02
 * @author nitinb
 *
 */
public enum BgpLsTypeV2 {
	Identifier,
	LocalNodeDescriptor,
	RemoteNodeDescriptor,
	AutonomousSystem,
	BgpIdentifier,
	ISONodeId,
	IPv4RouterId,
	IPv6RouterId,
	LinkIdentifiers,
	IPv4InterfaceAddress,
	IPv4NeighborAddress,
	IPv6InterfaceAddress,
	IPv6NeighborAddress,
	AdminGroup,
	MaxLinkBandwidth,
	MaxReservableLinkBandwidth,
	UnreservedBandwidth,
	TEDefaultMetric,
	LinkProtectionType,
	MPLSProtocolMask,
	Metric,
	SharedRiskLinkGroup,
	OSPFLinkAttribute,
	ISISLinkAttribute,
	NodeFlagBits,
	OSPFNodeProperties,
	ISISNodeProperties,
	IGPFlags,
	RouteTag,
	ExtendedTag,
	PrefixMetric,
	OSPFForwardingAddress,
	Unknown;
	
	public int toCode() {
		switch(this) {
		case Identifier:
			return 256;
		case LocalNodeDescriptor:
			return 257;
		case RemoteNodeDescriptor:
			return 258;
		case AutonomousSystem:
			return 259;
		case BgpIdentifier:
			return 260;
		case ISONodeId:
			return 261;
		case IPv4RouterId:
			return 262;
		case IPv6RouterId:
			return 263;
		case LinkIdentifiers:
			return 264;
		case IPv4InterfaceAddress:
			return 265;
		case IPv4NeighborAddress:
			return 266;
		case IPv6InterfaceAddress:
			return 267;
		case IPv6NeighborAddress:
			return 268;
		case AdminGroup:
			return 269;
		case MaxLinkBandwidth:
			return 270;
		case MaxReservableLinkBandwidth:
			return 271;
		case UnreservedBandwidth:
			return 272;
		case TEDefaultMetric:
			return 273;
		case LinkProtectionType:
			return 274;
		case MPLSProtocolMask:
			return 275;
		case Metric:
			return 276;
		case SharedRiskLinkGroup:
			return 277;
		case OSPFLinkAttribute:
			return 278;
		case ISISLinkAttribute:
			return 279;
		case NodeFlagBits:
			return 280;
		case OSPFNodeProperties:
			return 281;
		case ISISNodeProperties:
			return 282;
		case IGPFlags:
			return 283;
		case RouteTag:
			return 284;
		case ExtendedTag:
			return 285;
		case PrefixMetric:
			return 286;
		case OSPFForwardingAddress:
			return 287;
		default:
			throw new IllegalArgumentException("unknown type: " + this);
		}
	}
	
	public static BgpLsTypeV2 fromCode(int code) {
		switch(code) {
		case 256:
			return Identifier;
		case 257:
			return LocalNodeDescriptor;
		case 258:
			return RemoteNodeDescriptor;
		case 259:
			return AutonomousSystem;
		case 260:
			return BgpIdentifier;
		case 261:
			return ISONodeId;
		case 262:
			return IPv4RouterId;
		case 263:
			return IPv6RouterId;
		case 264:
			return LinkIdentifiers;
		case 265:
			return IPv4InterfaceAddress;
		case 266:
			return IPv4NeighborAddress;
		case 267:
			return IPv6InterfaceAddress;
		case 268:
			return IPv6NeighborAddress;
		case 269:
			return AdminGroup;
		case 270:	
			return MaxLinkBandwidth;
		case 271:
			return MaxReservableLinkBandwidth;
		case 272:	
			return UnreservedBandwidth;
		case 273:	
			return TEDefaultMetric;
		case 274:
			return LinkProtectionType;
		case 275:	
			return MPLSProtocolMask;
		case 276:	
			return Metric;
		case 277:
			return SharedRiskLinkGroup;
		case 278:
			return OSPFLinkAttribute;
		case 279:
			return ISISLinkAttribute;
		case 280:
			return NodeFlagBits;
		case 281:
			return OSPFNodeProperties;
		case 282:
			return ISISNodeProperties;
		case 283:
			return IGPFlags;
		case 284:
			return RouteTag;
		case 285:
			return ExtendedTag;
		case 286:
			return PrefixMetric;
		case 287:
			return OSPFForwardingAddress;
		default:
			return Unknown;
		}
	}
	
	public String toString() {
		switch(this) {
		case Identifier:
			return "Identifier";
		case LocalNodeDescriptor:
			return "LocalNodeDescriptor";
		case RemoteNodeDescriptor:
			return "RemoteNodeDescriptor";
		case AutonomousSystem:
			return "AutonomousSystem";
		case BgpIdentifier:
			return "BgpIdentifier";
		case ISONodeId:
			return "ISONodeId";
		case IPv4RouterId:
			return "IPv4RouterId";
		case IPv6RouterId:
			return "IPv6RouterId";
		case LinkIdentifiers:
			return "LinkIdentifiers";
		case IPv4InterfaceAddress:
			return "IPv4InterfaceAddress";
		case IPv4NeighborAddress:
			return "IPv4NeighborAddress";
		case IPv6InterfaceAddress:
			return "IPv6InterfaceAddress";
		case IPv6NeighborAddress:
			return "IPv6NeighborAddress";
		case AdminGroup:
			return "AdminGroup";
		case MaxLinkBandwidth:
			return "MaxLinkBandwidth";
		case MaxReservableLinkBandwidth:
			return "MaxReservableLinkBandwidth";
		case UnreservedBandwidth:
			return "UnreservedBandwidth";
		case TEDefaultMetric:
			return "TEDefaultMetric";
		case LinkProtectionType:
			return "LinkProtectionType";
		case MPLSProtocolMask:
			return "MPLSProtocolMask";
		case Metric:
			return "Metric";
		case SharedRiskLinkGroup:
			return "SharedRiskLinkGroup";
		case OSPFLinkAttribute:
			return "OSPFLinkAttribute";
		case ISISLinkAttribute:
			return "ISISLinkAttribute";
		case NodeFlagBits:
			return "NodeFlagBits";
		case OSPFNodeProperties:
			return "OSPFNodeProperties";
		case ISISNodeProperties:
			return "ISISNodeProperties";
		case IGPFlags:
			return "IGPFlags";
		case RouteTag:
			return "RouteTag";
		case ExtendedTag:
			return "ExtendedTag";
		case PrefixMetric:
			return "PrefixMetric";
		case OSPFForwardingAddress:
			return "OSPFForwardingAddress";
		default:
			return "Unknown";
		}
	}
}
