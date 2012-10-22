package jp.dev7.game13.actor;

import static com.googlecode.actorom.dsl.Messaging.*;

import com.googlecode.actorom.Address;
import com.googlecode.actorom.Topology;
import com.googlecode.actorom.annotation.OnMessage;
import com.googlecode.actorom.annotation.TopologyInstance;

public class TalkerA {

	@TopologyInstance
	private Topology topology;
	private final Address talkerB;

	public TalkerA(Address talkerB) {
		this.talkerB = talkerB;
	}

	@OnMessage(type = String.class)
	public void onstart(String message) {
		System.out.println("onstart");
		System.out.println("get message = " + message);
		on(topology).send("hi").to(talkerB);
	}

}
