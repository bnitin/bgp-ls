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
package org.topology.bgp_ls.bgpd;

import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.topology.bgp_ls.bgpd.config.ConfigurationFileProcessor;
import org.topology.bgp_ls.netty.SingletonRegistry;
import java.io.File;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;

/**
 * @author Rainer Bieniek (Rainer.Bieniek@web.de)
 *
 */
public class BgpDaemon {
		
	private static final Logger log = LoggerFactory.getLogger(BgpDaemon.class);
	private static Scheduler scheduler;
	private static String exportFileName;

	private static boolean processOptions (String[] args) {
		
		String[] commandLine = args;
		ConfigurationFileProcessor configurationFileProcessor;
		
		BasicConfigurator.configure();

		try {
			Options options = new Options();
			Option option;
			
			option = new Option("c", "config-file", true, "XML configuration file (required)");
			option.setRequired(true);
			options.addOption(option);

			option = new Option("l", "log4-file", true, "Log4J XML configuration file (optional");
			option.setRequired(false);
			options.addOption(option);

			option = new Option("e", "export-file", true, "Topology export file (optional");
			option.setRequired(false);
			options.addOption(option);

			option = new Option("t", "test-config-file", false, "test XML configuration file and exit (optional)");
			option.setRequired(false);
			options.addOption(option);

			CommandLine cmd = (new PosixParser()).parse(options, commandLine);

			if(cmd.hasOption("l")) {
				LogManager.resetConfiguration();
				DOMConfigurator.configure(cmd.getOptionValue("l"));
			}

			if (cmd.hasOption("e")) {
				exportFileName = cmd.getOptionValue("e");
			}
			
			configurationFileProcessor = (ConfigurationFileProcessor)SingletonRegistry.getInstance(ConfigurationFileProcessor.class.getName());
			configurationFileProcessor.processConfigFile(cmd.getOptionValue("c"));
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private static boolean startScheduler() {
		StdSchedulerFactory factory = (StdSchedulerFactory)SingletonRegistry.getInstance(StdSchedulerFactory.class.getName());
		
		try {
			factory.initialize();
			scheduler = factory.getScheduler();
			scheduler.start();
			SingletonRegistry.addInstance(Scheduler.class.getName(), scheduler);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
		return true;
	}
	
	private static boolean topologyWriterInit() {
		try {
			JsonFactory jfactory = new JsonFactory();
			
			JsonGenerator jGenerator = jfactory.createJsonGenerator(new File(
					exportFileName), JsonEncoding.UTF8);
			jGenerator.useDefaultPrettyPrinter();
			SingletonRegistry.addInstance(JsonGenerator.class.getName(), jGenerator);
	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	

	public static void main(String[] args) {
		BgpDaemonApplicationListener bgpListener;

		if (!processOptions(args)) {
			return;
		}
		
		if (!startScheduler()) {
			return;
		}
		
		if (!topologyWriterInit()) {
			return;
		}
		
		bgpListener = new BgpDaemonApplicationListener();
		bgpListener.start();
		
		log.info("BGP daemon started");
		
		try {
			bgpListener.wait();
		} catch (Exception e) {
			// do nothing
		}
	}
}
