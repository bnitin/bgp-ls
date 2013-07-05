package org.topology.bgp_ls.netty.fsm;

public class FireSendKeepalive extends FireEventTimeJob {
	public FireSendKeepalive() {
		super(FSMEvent.keepaliveTimerExpires());
	}
}
