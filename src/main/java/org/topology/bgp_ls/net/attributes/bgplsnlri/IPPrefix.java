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

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;

import org.topology.bgp_ls.net.AddressFamily;

/**
 * @author nitinb
 *
 */
public class IPPrefix {
	private short prefixLength;
	private byte[] prefix;
	AddressFamily addressFamily;
	
	public IPPrefix(short prefixLength, byte[] prefix, AddressFamily addressFamily) {
		this.prefixLength = prefixLength;
		this.prefix = prefix;
		this.addressFamily = addressFamily;
	}
	
	public boolean isValidPrefix() {
		if (prefixLength == 0 || prefixLength > 128) {
			return false;
		}
		return true;
	}
	
	public short getPrefixLength() {
		return prefixLength;
	}
	
	public byte[] getPrefix() {
		return prefix;
	}
	
	public AddressFamily getAddressFamily() {
		return addressFamily;
	}
	
	private String ipv4ToString() {
		byte ipData[];
		InetAddress addr;
		
		if (prefix.length != 4) {
			ipData = new byte[4];
			System.arraycopy(prefix, 0, ipData, 0, prefix.length); 
		} else {
			ipData = prefix;
		}
		
		try {
			addr = Inet4Address.getByAddress(ipData);
			return addr.toString().concat("/").concat(String.valueOf(prefixLength));
		} catch (Exception e) {
			return null;
		}
	}
	
	private String ipv6ToString() {
		byte ipData[];
		InetAddress addr;
		
		if (prefix.length != 16) {
			ipData = new byte[16];
			System.arraycopy(prefix, 0, ipData, 0, prefix.length); 
		} else {
			ipData = prefix;
		}
		
		try {
			addr = Inet6Address.getByAddress(ipData);
			return addr.toString().concat("/").concat(String.valueOf(prefixLength));
		} catch (Exception e) {
			return null;
		}
	}
	
	public String toString() {
		switch (addressFamily) {
		case IPv4:
			return ipv4ToString();
		case IPv6:
			return ipv6ToString();
		default:
			return null;
		}
	}
}
