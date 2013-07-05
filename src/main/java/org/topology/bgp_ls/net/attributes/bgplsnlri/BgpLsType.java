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
 * The TLV/sub-tlv types are based on draft-ietf-idr-ls-distribution-01
 * @author nitinb
 *
 */
public enum BgpLsType {
	LocalNodeDescriptor,
	RemoteNodeDescriptor,
	AutonomousSystem,
	MemberAS,
	ISONodeId,
	IPv4RouterId,
	IPv6RouterId,
	LinkIdentifiers,
	IPv4InterfaceAddress,
	IPv4NeighborAddress,
	IPv6InterfaceAddress,
	IPv6NeighborAddress,
	LinkMultiTopologyId,
	AdminGroup,
	MaxLinkBandwidth,
	MaxReservableLinkBandwidth,
	UnreservedBandwidth,
	LinkProtectionType,
	MPLSProtocolMask,
	Metric,
	SharedRiskLinkGroup,
	OSPFLinkAttribute,
	ISISLinkAttribute,
	LinkAreaId,
	NodeMultiTopologyId,	
	NodeFlagBits,
	OSPFNodeProperties,
	ISISNodeProperties,
	NodeAreaId,
	IGPFlags,
	RouteTag,
	ExtendedTag,
	PrefixMetric,
	OSPFForwardingAddress,
	Unknown;
	
	public int toCode() {
		switch(this) {
		case LocalNodeDescriptor:
			return 256;
		case RemoteNodeDescriptor:
			return 257;
		case AutonomousSystem:
			return 258;
		case MemberAS:
			return 259;
		case ISONodeId:
			return 260;
		case IPv4RouterId:
			return 261;
		case IPv6RouterId:
			return 262;
		case LinkIdentifiers:
			return 263;
		case IPv4InterfaceAddress:
			return 264;
		case IPv4NeighborAddress:
			return 265;
		case IPv6InterfaceAddress:
			return 266;
		case IPv6NeighborAddress:
			return 267;
		case LinkMultiTopologyId:
			return 268;
		case AdminGroup:
			return 269;
		case MaxLinkBandwidth:
			return 270;
		case MaxReservableLinkBandwidth:
			return 271;
		case UnreservedBandwidth:
			return 272;
		case LinkProtectionType:
			return 273;
		case MPLSProtocolMask:
			return 274;
		case Metric:
			return 275;
		case SharedRiskLinkGroup:
			return 276;
		case OSPFLinkAttribute:
			return 277;
		case ISISLinkAttribute:
			return 278;
		case LinkAreaId:
			return 279;
		case NodeMultiTopologyId:
			return 280;
		case NodeFlagBits:
			return 281;
		case OSPFNodeProperties:
			return 282;
		case ISISNodeProperties:
			return 283;
		case NodeAreaId:
			return 284;
		case IGPFlags:
			return 285;
		case RouteTag:
			return 286;
		case ExtendedTag:
			return 287;
		case PrefixMetric:
			return 288;
		case OSPFForwardingAddress:
			return 289;
		default:
			throw new IllegalArgumentException("unknown type: " + this);
		}
	}
	
	public static BgpLsType fromCode(int code) {
		switch(code) {
		case 256:
			return LocalNodeDescriptor;
		case 257:
			return RemoteNodeDescriptor;
		case 258:
			return AutonomousSystem;
		case 259:
			return MemberAS;
		case 260:
			return ISONodeId;
		case 261:
			return IPv4RouterId;
		case 262:
			return IPv6RouterId;
		case 263:
			return LinkIdentifiers;
		case 264:
			return IPv4InterfaceAddress;
		case 265:
			return IPv4NeighborAddress;
		case 266:
			return IPv6InterfaceAddress;
		case 267:
			return IPv6NeighborAddress;
		case 268:
			return LinkMultiTopologyId;
		case 269:
			return AdminGroup;
		case 270:	
			return MaxLinkBandwidth;
		case 271:
			return MaxReservableLinkBandwidth;
		case 272:	
			return UnreservedBandwidth;
		case 273:
			return LinkProtectionType;
		case 274:	
			return MPLSProtocolMask;
		case 275:	
			return Metric;
		case 276:
			return SharedRiskLinkGroup;
		case 277:
			return OSPFLinkAttribute;
		case 278:
			return ISISLinkAttribute;
		case 279:
			return LinkAreaId;
		case 280:
			return NodeMultiTopologyId;
		case 281:
			return NodeFlagBits;
		case 282:
			return OSPFNodeProperties;
		case 283:
			return ISISNodeProperties;
		case 284:
			return NodeAreaId;
		case 285:
			return IGPFlags;
		case 286:
			return RouteTag;
		case 287:
			return ExtendedTag;
		case 288:
			return PrefixMetric;
		case 289:
			return OSPFForwardingAddress;
		default:
			return Unknown;
		}
	}
	
	public String toString() {
		switch(this) {
		case LocalNodeDescriptor:
			return "LocalNodeDescriptor";
		case RemoteNodeDescriptor:
			return "RemoteNodeDescriptor";
		case AutonomousSystem:
			return "AutonomousSystem";
		case MemberAS:
			return "MemberAS";
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
		case LinkMultiTopologyId:
			return "LinkMultiTopologyId";
		case AdminGroup:
			return "AdminGroup";
		case MaxLinkBandwidth:
			return "MaxLinkBandwidth";
		case MaxReservableLinkBandwidth:
			return "MaxReservableLinkBandwidth";
		case UnreservedBandwidth:
			return "UnreservedBandwidth";
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
		case LinkAreaId:
			return "LinkAreaId";
		case NodeMultiTopologyId:
			return "NodeMultiTopologyId";
		case NodeFlagBits:
			return "NodeFlagBits";
		case OSPFNodeProperties:
			return "OSPFNodeProperties";
		case ISISNodeProperties:
			return "ISISNodeProperties";
		case NodeAreaId:
			return "NodeAreaId";
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
