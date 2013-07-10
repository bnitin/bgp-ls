bgp-ls
======

This project aims to expose network topology through Border Gateway Protocol (BGP) Link State extensions defined in [draft-ietf-idr-ls-distribution](http://tools.ietf.org/html/draft-ietf-idr-ls-distribution).

This is built on top of a BGP daemon that connects to a BGP peer in the network. Topology is learnt via BGP extenions defined in the above draft. The daemon processed real-time topology updates and writes them in JSON format to a text file.

The core BGP daemon is taken from the open-source project [rbieniek/BGP4J](https://github.com/rbieniek/BGP4J). The base project was modified to run as a standalone Java application and BGP Link state extensions were then added on top of it.

Currently the code is based off draft-ietf-idr-ls-distribution-01. It is not compatible with version -00 or -02 (and beyond). 

This source is licensed under Apache 2.0.

Build environment:
-----------------
This project requires a minimum of JavaSE-1.6.

It depends on the following libraries.
- junit-3.8.1
- quartz-2.1.6 (org.quartz-scheduler)
- jackson-core-asl-1.9.12 (org.codehaus.jackson)
- jackson-mapper-asl-1.9.12 (org.codehaus.jackson)
- commons-lang3-3.1
- logging-1.0.4
- log4j-1.2.15
- slf4j-api-1.7.2 (org.slf4j.api)
- slf4j.log4j12-1.7.2 (org.slf4j.log4j12)
- commons-configuration-1.9 (commons-configuration)
- netty-3.6.3.Final
- cli-1.0.0 (org.mod4j.org.apache.commons)
- commons-lang-2.6
- commons-logging-1.1.1

Dependencies can be directly obtained using maven pom file.

Runtime arguments
=================
Required:
 -c <config-file>

Optional:
 -l <log4j XML config file>
 -e <export-file>

See the config-samples directory, for a sample of config-file and log-config-file

If the <export-file> argument is not provided, then topology data will not be exported to a file.


