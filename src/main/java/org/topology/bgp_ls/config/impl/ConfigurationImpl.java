/**
 *  Copyright 2012 Rainer Bieniek (Rainer.Bieniek@web.de)
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
 * File: org.bgp4.config.impl.ConfigurationImpl.java 
 */
package org.topology.bgp_ls.config.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.configuration.ConfigurationException;
import org.topology.bgp_ls.config.Configuration;
import org.topology.bgp_ls.config.nodes.CapabilitiesList;
import org.topology.bgp_ls.config.nodes.ServerConfiguration;
import org.topology.bgp_ls.config.nodes.PeerConfiguration;
import org.topology.bgp_ls.config.nodes.RoutingProcessorConfiguration;
import org.topology.bgp_ls.config.nodes.PeerConfigurationTimer;
import org.topology.bgp_ls.config.nodes.impl.FixedDefaultsPeerConfigurationTimer;
import org.topology.bgp_ls.netty.SingletonRegistry;
import org.topology.bgp_ls.net.AddressFamily;
import org.topology.bgp_ls.net.SubsequentAddressFamily;
import org.topology.bgp_ls.net.capabilities.*;

/**
 * @author Rainer Bieniek (Rainer.Bieniek@web.de)
 *
 */
public class ConfigurationImpl implements Configuration {

	private ServerConfiguration bgpServerConfigImpl;
	private ServerConfiguration httpServerConfiguration;
	private RoutingProcessorConfiguration routingProcessorConfiguration;
	
	private Map<String, PeerConfiguration> peerMap = new HashMap<String, PeerConfiguration>();
	
	@Override
	public ServerConfiguration getBgpServerConfiguration() {
		return bgpServerConfigImpl;
	}
	
	void setBgpServerConfiguration(ServerConfiguration bgpServerConfigImpl) {
		this.bgpServerConfigImpl = bgpServerConfigImpl;
	}

	@Override
	public ServerConfiguration getHttpServerConfiguration() {
		return httpServerConfiguration;
	}

	void setHttpServerConfiguration(
			ServerConfiguration httpServerConfiguration) {
		this.httpServerConfiguration = httpServerConfiguration;
	}

	@Override
	public Set<String> listPeerNames() {
		return Collections.unmodifiableSet(peerMap.keySet());
	}

	@Override
	public PeerConfiguration getPeer(String peerName) {
		return peerMap.get(peerName);
	}
	
	private void setupPeerTimers(PeerConfiguration peer, PeerConfigurationTimer timer) throws ConfigurationException {
		peer.setHoldTime(timer.getHoldTime());
		peer.setIdleHoldTime(timer.getIdleHoldTime());
		peer.setDelayOpenTime(timer.getDelayOpenTime());
		peer.setConnectRetryTime(timer.getConnectRetryTime());
		peer.setAutomaticStartInterval(timer.getAutomaticStartInterval());
	}
	
	void addPeer(PeerConfiguration peerConfig) throws ConfigurationException {
		if(peerMap.containsKey(peerConfig.getPeerName()))
				throw new ConfigurationException("duplicate peer name " + peerConfig.getPeerName());
		
		PeerConfigurationTimer peerConfigTimer = (PeerConfigurationTimer)SingletonRegistry.getInstance(FixedDefaultsPeerConfigurationTimer.class.getName());
		setupPeerTimers(peerConfig, peerConfigTimer);
		
		CapabilitiesList caps = new CapabilitiesList();
		caps.addRequiredCapability(new AutonomousSystem4Capability(peerConfig.getLocalAS()));
		caps.addRequiredCapability(new MultiProtocolCapability(AddressFamily.BGP_LS,SubsequentAddressFamily.NLRI_BGP_LS));
		peerConfig.setCapabilities(caps);
		
		peerMap.put(peerConfig.getPeerName(), peerConfig);
	}

	@Override
	public List<PeerConfiguration> listPeerConfigurations() {
		List<PeerConfiguration> peers = new ArrayList<PeerConfiguration>(peerMap.size());
		
		for(Entry<String, PeerConfiguration> peerEntry : peerMap.entrySet())
			peers.add(peerEntry.getValue());
		
		return Collections.unmodifiableList(peers);
	}

	/**
	 * @return the routingProcessorConfiguration
	 */
	public RoutingProcessorConfiguration getRoutingProcessorConfiguration() {
		return routingProcessorConfiguration;
	}

	/**
	 * @param routingProcessorConfiguration the routingProcessorConfiguration to set
	 */
	void setRoutingProcessorConfiguration(
			RoutingProcessorConfiguration routingProcessorConfiguration) {
		this.routingProcessorConfiguration = routingProcessorConfiguration;
	}
}
