package org.topology.bgp_ls.netty.fsm;

public class FireHoldTimerExpired extends FireEventTimeJob {
	public FireHoldTimerExpired() {
		super(FSMEvent.holdTimerExpires());
	}
}
