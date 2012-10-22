package jp.dev7.game13.actor;

import static com.googlecode.actorom.dsl.Messaging.*;
import jp.dev7.game13.actor.message.JoinMessage;
import jp.dev7.game13.actor.message.SoldOutMessage;
import jp.dev7.game13.actor.message.TickMessage;

import com.googlecode.actorom.Address;
import com.googlecode.actorom.Topology;
import com.googlecode.actorom.annotation.OnMessage;
import com.googlecode.actorom.annotation.TopologyInstance;

public class BattleField {

	@TopologyInstance
	private Topology topology;

	private Address a;
	private Address b;

	@OnMessage(type = JoinMessage.class)
	public void join(JoinMessage join) {
		if (a != null) {
			a = join.getWho();
		} else if (b != null) {
			b = join.getWho();
		} else {
			on(topology).send(new SoldOutMessage()).to(join.getWho());
		}
	}

	@OnMessage(type = TickMessage.class)
	public void onTick(TickMessage tick) {
		on(topology).send(tick).to(a);
		on(topology).send(tick).to(b);
	}

}
