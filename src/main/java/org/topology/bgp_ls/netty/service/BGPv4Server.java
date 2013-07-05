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
 */
/**
 * Copyright 2013 Nitin Bahadur (nitinb@gmail.com)
 * 
 * License: same as above
 * 
 * Modified to run as an independent java application, one that does not
 * require webserver or app server
 */
package org.topology.bgp_ls.netty.service;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.topology.bgp_ls.config.global.ApplicationConfiguration;
import org.topology.bgp_ls.netty.SingletonRegistry;
import org.topology.bgp_ls.netty.handlers.BGPv4Codec;
import org.topology.bgp_ls.netty.handlers.BGPv4Reframer;
import org.topology.bgp_ls.netty.handlers.BGPv4ServerEndpoint;
import org.topology.bgp_ls.netty.handlers.InboundOpenCapabilitiesProcessor;
import org.topology.bgp_ls.netty.handlers.ValidateServerIdentifier;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rainer Bieniek (Rainer.Bieniek@web.de)
 *
 */
public class BGPv4Server {

	private static final Logger log = LoggerFactory.getLogger(BGPv4Server.class);
	private ApplicationConfiguration applicationConfiguration;
	private BGPv4ServerEndpoint serverEndpoint;
	private BGPv4Codec codec;
	private InboundOpenCapabilitiesProcessor inboundOpenCapProcessor;
	private ValidateServerIdentifier validateServer;
	private BGPv4Reframer reframer;
	private Channel serverChannel;
	private ChannelFactory serverChannelFactory;

    public BGPv4Server () {
            applicationConfiguration =  (ApplicationConfiguration)SingletonRegistry.getInstance(ApplicationConfiguration.class.getName());
            serverEndpoint = (BGPv4ServerEndpoint)SingletonRegistry.getInstance(BGPv4ServerEndpoint.class.getName());
            codec = (BGPv4Codec)SingletonRegistry.getInstance(BGPv4Codec.class.getName());
            inboundOpenCapProcessor = (InboundOpenCapabilitiesProcessor)SingletonRegistry.getInstance(InboundOpenCapabilitiesProcessor.class.getName());
            validateServer = (ValidateServerIdentifier)SingletonRegistry.getInstance(ValidateServerIdentifier.class.getName());
            reframer = new BGPv4Reframer();
    }

	public void startServer() {
		serverChannelFactory = new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool());
				    
		ServerBootstrap bootstrap = new ServerBootstrap(serverChannelFactory);
				    
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				
				pipeline.addLast(BGPv4Reframer.HANDLER_NAME, reframer);
				pipeline.addLast(BGPv4Codec.HANDLER_NAME, codec);
				pipeline.addLast(InboundOpenCapabilitiesProcessor.HANDLER_NAME, inboundOpenCapProcessor);
				pipeline.addLast(ValidateServerIdentifier.HANDLER_NAME, validateServer);
				pipeline.addLast(BGPv4ServerEndpoint.HANDLER_NAME, serverEndpoint);
				
				return pipeline;
			}
		});
		
		bootstrap.setOption("child.tcpNoDelay", true);
		bootstrap.setOption("child.keepAlive", true);
		
		InetSocketAddress serverSocketAddress = applicationConfiguration.getBgpServerConfiguration().getListenAddress();
		
		log.info("Starting local server on " + serverSocketAddress);
		serverChannel = bootstrap.bind(serverSocketAddress);
	}
	
	public void stopServer() {
		log.info("closing all child connections");
		serverEndpoint.getTrackedChannels().close().awaitUninterruptibly();

		if(serverChannel != null) {
			log.info("stopping local server");
			
			serverChannel.close();
			serverChannel.getCloseFuture().awaitUninterruptibly();
		}

		log.info("cleaning up server resources");
		serverChannelFactory.releaseExternalResources();
	}

}
