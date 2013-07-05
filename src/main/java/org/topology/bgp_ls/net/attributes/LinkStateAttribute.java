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
package org.topology.bgp_ls.net.attributes;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author nitinb
 *
 */
public class LinkStateAttribute extends PathAttribute {

	private long linkAreaId;
	private long adminGroup;
	private float maxLinkBandwidth;
	private float maxReservableLinkBandwidth;
	private float[] unreservedBandwidth;
	//private int teDefaultMetric; draft v2
	private short linkProtectionType;
	private short mplsProtocolMask;
	private int metric;
	private List<Long> sharedRiskLinkGroups;
	private byte[] ospfLinkAttribute;
	private byte[] isisLinkAttribute;
	private int nodeMultiTopologyId;
	private short nodeFlagBits;
	private byte[] ospfNodeProperties;
	private byte[] isisNodeProperties;
	private byte[] nodeAreaId;
	private long igpFlags;
	private long routeTag;
	private byte[] extendedTag;
	private long prefixMetric;
	private byte[] ospfForwardingAddress;
	
	private boolean validLinkAreaId = false;
	private boolean validAdminGroup = false;
	private boolean validMaxLinkBandwidth = false;
	private boolean validMaxReservableLinkBandwidth = false;
	//private boolean validTeDefaultMetric = false; draft v2
	private boolean validLinkProtectionType = false;
	private boolean validMplsProtocolMask = false;
	private boolean validMetric = false;
	private boolean validNodeMultiTopologyId = false;
	private boolean validNodeFlagBits = false;
	private boolean validIgpFlags = false;
	private boolean validRouteTag = false;
	private boolean validPrefixMetric = false;
	
	/**
	 * @param category
	 */
	public LinkStateAttribute() {
		super(Category.OPTIONAL_NON_TRANSITIVE);
		setSharedRiskLinkGroups(new LinkedList<Long>());
	}

	/**
	 * @return the adminGroup
	 */
	public long getAdminGroup() {
		return adminGroup;
	}

	/**
	 * @param adminGroup the adminGroup to set
	 */
	public void setAdminGroup(long adminGroup) {
		this.adminGroup = adminGroup;
		validAdminGroup = true;
	}

	public boolean isValidAdminGroup() {
		return validAdminGroup;
	}
	
	/**
	 * @return the maxLinkBandwidth
	 */
	public float getMaxLinkBandwidth() {
		return maxLinkBandwidth;
	}

	/**
	 * @param maxLinkBandwidth the maxLinkBandwidth to set
	 */
	public void setMaxLinkBandwidth(float maxLinkBandwidth) {
		this.maxLinkBandwidth = maxLinkBandwidth;
		validMaxLinkBandwidth = true;
	}

	public boolean isValidMaxLinkBandwidth() {
		return validMaxLinkBandwidth;
	}
	
	/**
	 * @return the maxReservableLinkBandwidth
	 */
	public float getMaxReservableLinkBandwidth() {
		return maxReservableLinkBandwidth;
	}

	/**
	 * @param maxReservableLinkBandwidth the maxReservableLinkBandwidth to set
	 */
	public void setMaxReservableLinkBandwidth(float maxReservableLinkBandwidth) {
		this.maxReservableLinkBandwidth = maxReservableLinkBandwidth;
		validMaxReservableLinkBandwidth = true;
	}

	public boolean isValidMaxReservableLinkBandwidth() {
		return validMaxReservableLinkBandwidth;
	}
	
	/**
	 * @return the unreservedBandwidth
	 */
	public float[] getUnreservedBandwidth() {
		return unreservedBandwidth;
	}

	/**
	 * @param unreservedBandwidth the unreservedBandwidth to set
	 */
	public void setUnreservedBandwidth(float unreservedBandwidth[]) {
		this.unreservedBandwidth = unreservedBandwidth;
	}

	/* draft v2
	public int getTeDefaultMetric() {
		return teDefaultMetric;
	}

	public void setTeDefaultMetric(int teDefaultMetric) {
		this.teDefaultMetric = teDefaultMetric;
		validTeDefaultMetric = true;
	}

	public boolean isValidTeDefaultMetric() {
		return validTeDefaultMetric;
	}
	*/
	
	/**
	 * @return the linkProtectionType
	 */
	public short getLinkProtectionType() {
		return linkProtectionType;
	}

	/**
	 * @param linkProtectionType the linkProtectionType to set
	 */
	public void setLinkProtectionType(short linkProtectionType) {
		this.linkProtectionType = linkProtectionType;
		validLinkProtectionType = true;
	}

	public boolean isValidLinkProtectionType() {
		return validLinkProtectionType;
	}
	
	/**
	 * @return the mplsProtocolMask
	 */
	public short getMplsProtocolMask() {
		return mplsProtocolMask;
	}

	/**
	 * @param mplsProtocolMask the mplsProtocolMask to set
	 */
	public void setMplsProtocolMask(short mplsProtocolMask) {
		this.mplsProtocolMask = mplsProtocolMask;
		validMplsProtocolMask = true;
	}

	public boolean isValidMplsProtocolMask() {
		return validMplsProtocolMask;
	}
	
	/**
	 * @return the metric
	 */
	public int getMetric() {
		return metric;
	}

	/**
	 * @param metric the metric to set
	 */
	public void setMetric(int metric) {
		this.metric = metric;
		validMetric = true;
	}

	public boolean isValidMetric() {
		return validMetric;
	}
	
	/**
	 * @return the sharedRiskLinkGroups
	 */
	public List<Long> getSharedRiskLinkGroups() {
		return sharedRiskLinkGroups;
	}

	/**
	 * @param sharedRiskLinkGroups the sharedRiskLinkGroups to set
	 */
	public void setSharedRiskLinkGroups(List<Long> sharedRiskLinkGroups) {
		this.sharedRiskLinkGroups = sharedRiskLinkGroups;
	}

	public void addSharedRiskLinkGroup(long sharedRiskLinkGroup) {
		this.sharedRiskLinkGroups.add(new Long(sharedRiskLinkGroup));
	}
	
	/**
	 * @return the ospfLinkAttribute
	 */
	public byte[] getOspfLinkAttribute() {
		return ospfLinkAttribute;
	}

	/**
	 * @param ospfLinkAttribute the ospfLinkAttribute to set
	 */
	public void setOspfLinkAttribute(byte[] ospfLinkAttribute) {
		this.ospfLinkAttribute = ospfLinkAttribute;
	}

	/**
	 * @return the isisLinkAttribute
	 */
	public byte[] getIsisLinkAttribute() {
		return isisLinkAttribute;
	}

	/**
	 * @param isisLinkAttribute the isisLinkAttribute to set
	 */
	public void setIsisLinkAttribute(byte[] isisLinkAttribute) {
		this.isisLinkAttribute = isisLinkAttribute;
	}

	/* (non-Javadoc)
	 * @see org.topology.bgp_ls.net.attributes.PathAttribute#subclassToString()
	 */
	@Override
	protected ToStringBuilder subclassToString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		

		if (isValidLinkAreaId()) {
			builder.append("linkAreaId", linkAreaId);
		}
		if (isValidAdminGroup()) {
			builder.append("adminGroup", adminGroup);
		}
		if (isValidMaxLinkBandwidth()) {
			builder.append("maxLinkBandwidth", maxLinkBandwidth);
		}
		if (isValidMaxReservableLinkBandwidth()) {
			builder.append("maxReservableLinkBandwidth", maxReservableLinkBandwidth);
		}
		if (unreservedBandwidth != null) {
			for(int i= 0; i < 8; i++) {
				builder.append("unreservedBandwidth["+i+"]", unreservedBandwidth[i]);
			}
		}
		/*if (isValidTeDefaultMetric()) {  draft v2
			builder.append("teDefaultMetric", teDefaultMetric);
		}*/
		if (isValidLinkProtectionType()) {
			builder.append("linkProtectionType", linkProtectionType);
		}
		if (isValidMplsProtocolMask()) {
			builder.append("mplsProtocolMask", mplsProtocolMask);
		}
		if (isValidMetric()) {
			builder.append("metric", metric);
		}
		if (sharedRiskLinkGroups != null) {
			for(Long group : sharedRiskLinkGroups) {
				builder.append("sharedRiskLinkGroup", group.longValue());
			}
		}
		if (ospfLinkAttribute != null ) {
			builder.append("ospfLinkAttribute", ospfLinkAttribute);
		}
		if (isisLinkAttribute != null ) {
			builder.append("isisLinkAttribute", isisLinkAttribute);
		}
		if (isValidNodeMultiTopologyId()) {
			builder.append("nodeMultiTopologyId", nodeMultiTopologyId);
		}
		if (isValidNodeFlagBits()) {
			builder.append("nodeFlagBits", nodeFlagBits);
		}
		if (ospfNodeProperties != null) {
			builder.append("ospfNodeProperties", ospfNodeProperties);
		}
		if (isisNodeProperties != null) {
			builder.append("isisNodeProperties", isisNodeProperties);
		}
		if (nodeAreaId != null) {
			builder.append("nodeAreaId", nodeAreaId);
		}
		if (isValidIgpFlags()) {
			builder.append("igpFlags", igpFlags);
		}
		if (isValidRouteTag()) {
			builder.append("routeTag", routeTag);
		}
		if (extendedTag != null) {
			builder.append("extendedTag", extendedTag);
		}
		if (isValidPrefixMetric()) {
			builder.append("prefixMetric", prefixMetric);
		}
		if (ospfForwardingAddress != null) {
			builder.append("ospfForwardingAddress", ospfForwardingAddress);
		}
		return builder;
	}

	/* (non-Javadoc)
	 * @see org.topology.bgp_ls.net.attributes.PathAttribute#internalType()
	 */
	@Override
	protected PathAttributeType internalType() {
		return PathAttributeType.LINK_STATE;
	}

	/* (non-Javadoc)
	 * @see org.topology.bgp_ls.net.attributes.PathAttribute#subclassEquals(org.topology.bgp_ls.net.attributes.PathAttribute)
	 */
	@Override
	protected boolean subclassEquals(PathAttribute obj) {
		LinkStateAttribute o = (LinkStateAttribute)obj;
		
		return (new EqualsBuilder())
				.append(getLinkAreaId(), o.getLinkAreaId())
				.append(getAdminGroup(), o.getAdminGroup())
				.append(getMaxLinkBandwidth(), o.getMaxLinkBandwidth())
				.append(getMaxReservableLinkBandwidth(), o.getMaxReservableLinkBandwidth())
				.append(getUnreservedBandwidth(), o.getUnreservedBandwidth())
				//.append(getTeDefaultMetric(), o.getTeDefaultMetric())
				.append(getLinkProtectionType(), o.getLinkProtectionType())
				.append(getMplsProtocolMask(), o.getMplsProtocolMask())
				.append(getMetric(), o.getMetric())
				.append(getSharedRiskLinkGroups(), o.getSharedRiskLinkGroups())
				.append(getOspfLinkAttribute(), o.getOspfLinkAttribute())
				.append(getIsisLinkAttribute(), o.getIsisLinkAttribute())
				.append(getNodeMultiTopologyId(), o.getNodeMultiTopologyId())
				.append(getNodeFlagBits(), o.getNodeFlagBits())
				.append(getOspfNodeProperties(), o.getOspfNodeProperties())
				.append(getIsisNodeProperties(), o.getIsisNodeProperties())
				.append(getNodeAreaId(), o.getNodeAreaId())
				.append(getIgpFlags(),  o.getIgpFlags())
				.append(getRouteTag(), o.getRouteTag())
				.append(getExtendedTag(), o.getExtendedTag())
				.append(getPrefixMetric(), o.getPrefixMetric())
				.append(getOspfForwardingAddress(), o.getOspfForwardingAddress())
				.isEquals();
	}

	/* (non-Javadoc)
	 * @see org.topology.bgp_ls.net.attributes.PathAttribute#subclassHashCode()
	 */
	@Override
	protected int subclassHashCode() {
		return (new HashCodeBuilder())
				.append(getLinkAreaId())
				.append(getAdminGroup())
				.append(getMaxLinkBandwidth())
				.append(getMaxReservableLinkBandwidth())
				.append(getUnreservedBandwidth())
				//.append(getTeDefaultMetric())
				.append(getLinkProtectionType())
				.append(getMplsProtocolMask())
				.append(getMetric())
				.append(getSharedRiskLinkGroups())
				.append(getOspfLinkAttribute())
				.append(getIsisLinkAttribute())
				.append(getNodeMultiTopologyId())
				.append(getNodeFlagBits())
				.append(getOspfNodeProperties())
				.append(getIsisNodeProperties())
				.append(getNodeAreaId())
				.append(getIgpFlags())
				.append(getRouteTag())
				.append(getExtendedTag())
				.append(getPrefixMetric())
				.append(getOspfForwardingAddress())
				.toHashCode();
	}

	/* (non-Javadoc)
	 * @see org.topology.bgp_ls.net.attributes.PathAttribute#subclassCompareTo(org.topology.bgp_ls.net.attributes.PathAttribute)
	 */
	@Override
	protected int subclassCompareTo(PathAttribute obj) {
		LinkStateAttribute o = (LinkStateAttribute)obj;

		return (new CompareToBuilder())
				.append(getLinkAreaId(), o.getLinkAreaId())
				.append(getAdminGroup(), o.getAdminGroup())
				.append(getMaxLinkBandwidth(), o.getMaxLinkBandwidth())
				.append(getMaxReservableLinkBandwidth(), o.getMaxReservableLinkBandwidth())
				.append(getUnreservedBandwidth(), o.getUnreservedBandwidth())
				//.append(getTeDefaultMetric(), o.getTeDefaultMetric())
				.append(getLinkProtectionType(), o.getLinkProtectionType())
				.append(getMplsProtocolMask(), o.getMplsProtocolMask())
				.append(getMetric(), o.getMetric())
				.append(getSharedRiskLinkGroups(), o.getSharedRiskLinkGroups())
				.append(getOspfLinkAttribute(), o.getOspfLinkAttribute())
				.append(getIsisLinkAttribute(), o.getIsisLinkAttribute())
				.append(getNodeMultiTopologyId(), o.getNodeMultiTopologyId())
				.append(getNodeFlagBits(), o.getNodeFlagBits())
				.append(getOspfNodeProperties(), o.getOspfNodeProperties())
				.append(getIsisNodeProperties(), o.getIsisNodeProperties())
				.append(getNodeAreaId(), o.getNodeAreaId())
				.append(getIgpFlags(),  o.getIgpFlags())
				.append(getRouteTag(), o.getRouteTag())
				.append(getExtendedTag(), o.getExtendedTag())
				.append(getPrefixMetric(), o.getPrefixMetric())
				.append(getOspfForwardingAddress(), o.getOspfForwardingAddress())
				.toComparison();
	}

	/**
	 * @return the linkAreaId
	 */
	public long getLinkAreaId() {
		return linkAreaId;
	}

	/**
	 * @param linkAreaId the linkAreaId to set
	 */
	public void setLinkAreaId(long linkAreaId) {
		this.linkAreaId = linkAreaId;
		validLinkAreaId = true;
	}
	
	public boolean isValidLinkAreaId() {
		return validLinkAreaId;
	}
	
	/**
	 * @return the nodeMultiTopologyId
	 */
	public int getNodeMultiTopologyId() {
		return nodeMultiTopologyId;
	}

	/**
	 * @param nodeMultiTopologyId the nodeMultiTopologyId to set
	 */
	public void setNodeMultiTopologyId(int nodeMultiTopologyId) {
		this.nodeMultiTopologyId = nodeMultiTopologyId;
		validNodeMultiTopologyId = true;
	}

	public boolean isValidNodeMultiTopologyId() {
		return validNodeMultiTopologyId;
	}
	
	/**
	 * @return the nodeFlagBits
	 */
	public short getNodeFlagBits() {
		return nodeFlagBits;
	}

	/**
	 * @param nodeFlagBits the nodeFlagBits to set
	 */
	public void setNodeFlagBits(short nodeFlagBits) {
		this.nodeFlagBits = nodeFlagBits;
		validNodeFlagBits = true;
	}

	public boolean isValidNodeFlagBits() {
		return validNodeFlagBits;
	}
	
	/**
	 * @return the ospfNodeProperties
	 */
	public byte[] getOspfNodeProperties() {
		return ospfNodeProperties;
	}

	/**
	 * @param ospfNodeProperties the ospfNodeProperties to set
	 */
	public void setOspfNodeProperties(byte[] ospfNodeProperties) {
		this.ospfNodeProperties = ospfNodeProperties;
	}

	/**
	 * @return the isisNodeProperties
	 */
	public byte[] getIsisNodeProperties() {
		return isisNodeProperties;
	}

	/**
	 * @param isisNodeProperties the isisNodeProperties to set
	 */
	public void setIsisNodeProperties(byte[] isisNodeProperties) {
		this.isisNodeProperties = isisNodeProperties;
	}

	/**
	 * @return the nodeAreaId
	 */
	public byte[] getNodeAreaId() {
		return nodeAreaId;
	}

	/**
	 * @param nodeAreaId the nodeAreaId to set
	 */
	public void setNodeAreaId(byte[] nodeAreaId) {
		this.nodeAreaId = nodeAreaId;
	}

	/**
	 * @return the igpFlags
	 */
	public long getIgpFlags() {
		return igpFlags;
	}

	/**
	 * @param igpFlags the igpFlags to set
	 */
	public void setIgpFlags(long igpFlags) {
		this.igpFlags = igpFlags;
		validIgpFlags = true;
	}
	
	public boolean isValidIgpFlags() {
		return validIgpFlags;
	}

	/**
	 * @return the routeTag
	 */
	public long getRouteTag() {
		return routeTag;
	}

	/**
	 * @param routeTag the routeTag to set
	 */
	public void setRouteTag(long routeTag) {
		this.routeTag = routeTag;
		validRouteTag = true;
	}
	
	public boolean isValidRouteTag() {
		return validRouteTag;
	}

	/**
	 * @return the extendedTag
	 */
	public byte[] getExtendedTag() {
		return extendedTag;
	}

	/**
	 * @param extendedTag the extendedTag to set
	 */
	public void setExtendedTag(byte[] extendedTag) {
		this.extendedTag = extendedTag;
	}

	/**
	 * @return the prefixMetric
	 */
	public long getPrefixMetric() {
		return prefixMetric;
	}

	/**
	 * @param prefixMetric the prefixMetric to set
	 */
	public void setPrefixMetric(long prefixMetric) {
		this.prefixMetric = prefixMetric;
		validPrefixMetric = true;
	}
	
	public boolean isValidPrefixMetric() {
		return validPrefixMetric;
	}

	/**
	 * @return the ospfForwardingAddress
	 */
	public byte[] getOspfForwardingAddress() {
		return ospfForwardingAddress;
	}

	/**
	 * @param ospfForwardingAddress the ospfForwardingAddress to set
	 */
	public void setOspfForwardingAddress(byte[] ospfForwardingAddress) {
		this.ospfForwardingAddress = ospfForwardingAddress;
	}
}
