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

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.topology.bgp_ls.net.AddressFamily;
import org.topology.bgp_ls.net.AddressFamilyKey;
import org.topology.bgp_ls.net.BinaryNextHop;
import org.topology.bgp_ls.net.SubsequentAddressFamily;

/**
 * The MultiProtocolNLRI is an encapsulator for all content in a MP-BGP NLRI (RFC 4760)
 * The variable length information is represented as a list of NLRIs within this
 * encapsulator
 * @author nitinb
 *
 */
public class MultiProtocolNLRI extends PathAttribute {

	private AddressFamily addressFamily;
	private SubsequentAddressFamily subsequentAddressFamily;
	private BinaryNextHop nextHop;
	private List<MultiProtocolNLRIInformation> nlris = new LinkedList<MultiProtocolNLRIInformation>();
	private PathAttributeType attrType;
	
	/**
	 * @param category
	 */
	public MultiProtocolNLRI() {
		super(Category.OPTIONAL_NON_TRANSITIVE);
	}


	/**
	 * @param category
	 */
	public MultiProtocolNLRI(PathAttributeType attrType, AddressFamily addressFamily, SubsequentAddressFamily subsequentAddressFamily) {
		this();
		
		this.attrType = attrType;
		this.addressFamily = addressFamily;
		this.subsequentAddressFamily = subsequentAddressFamily;
	}
	
	/**
	 * @param category
	 */
	public MultiProtocolNLRI(PathAttributeType attrType, AddressFamily addressFamily, SubsequentAddressFamily subsequentAddressFamily, byte[] nextHopAddress) {
		this(attrType, addressFamily, subsequentAddressFamily);
		
		setNextHopAddress(nextHopAddress);
	}
	
	/**
	 * @param category
	 */
	public MultiProtocolNLRI(PathAttributeType attrType, AddressFamily addressFamily, SubsequentAddressFamily subsequentAddressFamily, BinaryNextHop nextHop) {
		this(attrType, addressFamily, subsequentAddressFamily);
		
		this.nextHop = nextHop;
	}
	
	/**
	 * @param category
	 */
	public MultiProtocolNLRI(PathAttributeType attrType, AddressFamily addressFamily, SubsequentAddressFamily subsequentAddressFamily, byte[] nextHopAddress, 
			MultiProtocolNLRIInformation[] nlris) {
		this(attrType, addressFamily, subsequentAddressFamily, nextHopAddress);
		
		for(MultiProtocolNLRIInformation nlri : nlris)
			this.nlris.add(nlri);
	}
	
	/**
	 * @param category
	 */
	public MultiProtocolNLRI(PathAttributeType attrType, AddressFamily addressFamily, SubsequentAddressFamily subsequentAddressFamily, BinaryNextHop nextHop, 
			MultiProtocolNLRIInformation[] nlris) {
		this(attrType, addressFamily, subsequentAddressFamily, nextHop);
		
		for(MultiProtocolNLRIInformation nlri : nlris)
			this.nlris.add(nlri);
	}
	
	/**
	 * @param category
	 */
	public MultiProtocolNLRI(PathAttributeType attrType, AddressFamily addressFamily, SubsequentAddressFamily subsequentAddressFamily, BinaryNextHop nextHop, 
			Collection<MultiProtocolNLRIInformation> nlris) {
		this(attrType, addressFamily, subsequentAddressFamily, nextHop);
		
		if(nlris != null)
			this.nlris.addAll(nlris);
	}
	/**
	 * @return the addressFamily
	 */
	public AddressFamily getAddressFamily() {
		return addressFamily;
	}

	/**
	 * @param addressFamily the addressFamily to set
	 */
	public void setAddressFamily(AddressFamily addressFamily) {
		this.addressFamily = addressFamily;
	}

	/**
	 * @return the subsequentAddressFamily
	 */
	public SubsequentAddressFamily getSubsequentAddressFamily() {
		return subsequentAddressFamily;
	}

	/**
	 * @param subsequentAddressFamily the subsequentAddressFamily to set
	 */
	public void setSubsequentAddressFamily(
			SubsequentAddressFamily subsequentAddressFamily) {
		this.subsequentAddressFamily = subsequentAddressFamily;
	}

	/**
	 * Returns the path attribute type (Reachable or Unreachable)
	 * @return path attribute type
	 */
	public PathAttributeType getPathAttributeType() {
		return attrType;
	}
	
	/**
	 * Sets the path attribute type (Reachable or Unreachable)
	 * @param path attribute type
	 */
	public void setPathAttributeType(PathAttributeType attrType) {
		this.attrType = attrType;
	}
	
	/**
	 * @return the nextHopAddress
	 */
	public BinaryNextHop getNextHop() {
		return nextHop;
	}

	/**
	 * @param nextHopAddress the nextHopAddress to set
	 */
	public void setNextHopAddress(byte[] nextHopAddress) {
		if(nextHopAddress != null)
			this.nextHop = new BinaryNextHop(nextHopAddress);
		else
			this.nextHop = null;
	}

	/**
	 * @param nextHopAddress the nextHopAddress to set
	 */
	public void setNextHop(BinaryNextHop nextHop) {
		this.nextHop = nextHop;
	}

	/**
	 * @return the nlris
	 */
	public List<MultiProtocolNLRIInformation> getNlris() {
		return nlris;
	}

	/**
	 * @param nlris the nlris to set
	 */
	public void setNlris(List<MultiProtocolNLRIInformation> nlris) {
		this.nlris = nlris;
	}

	public AddressFamilyKey addressFamilyKey() {
		return new AddressFamilyKey(getAddressFamily(), getSubsequentAddressFamily());
	}

	/* (non-Javadoc)
	 * @see org.topology.bgp_ls.net.attributes.PathAttribute#internalType()
	 */
	@Override
	protected PathAttributeType internalType() {
		return attrType;
	}

	/* (non-Javadoc)
	 * @see org.topology.bgp_ls.net.attributes.PathAttribute#subclassEquals(org.topology.bgp_ls.net.attributes.PathAttribute)
	 */
	@Override
	protected boolean subclassEquals(PathAttribute obj) {
		MultiProtocolNLRI o = (MultiProtocolNLRI)obj;
		
		EqualsBuilder builer = (new EqualsBuilder())
				.append(getPathAttributeType(), o.getPathAttributeType())
				.append(getAddressFamily(), o.getAddressFamily())
				.append(getSubsequentAddressFamily(), o.getSubsequentAddressFamily())
				.append(getNextHop(), o.getNextHop())
				.append(getNlris().size(), o.getNlris().size());
		
		if(builer.isEquals()) {
			Iterator<MultiProtocolNLRIInformation> lit = getNlris().iterator();
			Iterator<MultiProtocolNLRIInformation> rit = o.getNlris().iterator();
			
			while(lit.hasNext())
				builer.append(lit.next(), rit.next());
		}
		
		return builer.isEquals();
	}

	/* (non-Javadoc)
	 * @see org.topology.bgp_ls.net.attributes.PathAttribute#subclassHashCode()
	 */
	@Override
	protected int subclassHashCode() {
		HashCodeBuilder builder = (new HashCodeBuilder())
				.append(getPathAttributeType())
				.append(getAddressFamily())
				.append(getSubsequentAddressFamily())
				.append(getNextHop());
		Iterator<MultiProtocolNLRIInformation> it = getNlris().iterator();
		
		while(it.hasNext())
			builder.append(it.next());
		
		return builder.toHashCode();
	}

	/* (non-Javadoc)
	 * @see org.topology.bgp_ls.net.attributes.PathAttribute#subclassCompareTo(org.topology.bgp_ls.net.attributes.PathAttribute)
	 */
	@Override
	protected int subclassCompareTo(PathAttribute obj) {
		MultiProtocolNLRI o = (MultiProtocolNLRI)obj;
		
		CompareToBuilder builer = (new CompareToBuilder())
				.append(getPathAttributeType(), o.getPathAttributeType())
				.append(getAddressFamily(), o.getAddressFamily())
				.append(getSubsequentAddressFamily(), o.getSubsequentAddressFamily())
				.append(getNextHop(), o.getNextHop())
				.append(getNlris().size(), o.getNlris().size());
		
		if(builer.toComparison() == 0) {
			Iterator<MultiProtocolNLRIInformation> lit = getNlris().iterator();
			Iterator<MultiProtocolNLRIInformation> rit = o.getNlris().iterator();
			
			while(lit.hasNext())
				builer.append(lit.next(), rit.next());
		}
		
		return builer.toComparison();
	}

	/* (non-Javadoc)
	 * @see org.topology.bgp_ls.net.attributes.PathAttribute#subclassToString()
	 */
	@Override
	protected ToStringBuilder subclassToString() {
		ToStringBuilder builder = new ToStringBuilder(this)
			.append(attrType)
			.append(addressFamily)
			.append(subsequentAddressFamily)
			.append(nextHop);

		for(MultiProtocolNLRIInformation n : nlris)
			builder.append("nlri", n);

		return builder;
	}


}
