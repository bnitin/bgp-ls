
**Code in this repository is organized as follows:**

* bgpd

  Contains bgp daemon code, for starting the application

* config

  Contains all code for configuration file parsing and building relevant 
  config data structures.

* netty

  This directory contains the core bgp state machine. You hopefully will
  not need to touch this, except to fix BGP bugs.

    * fsm

    Contains the BGP finite state machine logic, including capability negotiation

    * handlers

    Contains a bunch of helper classes

    * protocol

    This contains parsing logic for the BGP protocol messages. There are
    multiple folders underneath this, one for each of the major message types.
    The parsing of the link-state NLRI is at protocol/update/bgplsnlri

    * service

    Contains code to start bgp as a client or a server. Depending on what your
    access permissions are, you might need to start it as a server, rather than
    as a client. By server, I mean that the BGP topology server waits for a
    connection from a network BGP speaker.

* net

  This directory structure contains definition of the BGP data structures.
  Once the bgp packet is parsed, the parsed information is stored in data
  structures defined in this directory. Common BGP objects like AddressFamily
  are also stored here.

    * capabilities

    Represents the various capabilities that this BGP speaker supports, to
    enable capability negotiation with peers.

    * attributes

    Various BGP attributes classes are stored here.
    MultiProtocolNLRI is the relevant attribute for BGP topology discovery

    * export

    Contains code to call export routines of NLRIs.

    * bgplsnlri

    The BGP link-state NLRI (one that contains topology information) is a 
    NLRI type of MultiProtocolNLRI. The objects of bgplsnlri are stored here.

        * export

        Contains code to export the link-state topology data structures into
        a JSON format. Right now only minimalistic JSON (not all fields of
        traffic engineering) is output. The minimalistic JSON is sufficient to
        get real-time network topology information.


Notes:


There is a bunch of code related to routes and route filtering (inherited
from parent BGP source) that is present in this repository and is not being
used.
