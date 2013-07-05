package org.topology.bgp_ls.netty.fsm;

public class FireAutomaticStart extends FireEventTimeJob {
	public FireAutomaticStart() {
		super(FSMEvent.automaticStart());
	}
}
