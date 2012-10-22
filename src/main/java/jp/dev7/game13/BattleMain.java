package jp.dev7.game13;

import static com.googlecode.actorom.dsl.Messaging.*;
import jp.dev7.game13.actor.BattleField;
import jp.dev7.game13.actor.TalkerA;
import jp.dev7.game13.actor.TalkerB;

import com.googlecode.actorom.Address;
import com.googlecode.actorom.Topology;
import com.googlecode.actorom.local.LocalTopology;
import com.googlecode.actorom.support.ThreadingPolicies;

public class BattleMain {

	public static void main(String[] args) {
		final Topology topology = new LocalTopology("local",
				ThreadingPolicies.newOSThreadingPolicy(10, true));

		final Address battleField = topology.spawnActor("battleField",
				new BattleField());

		final Address talkerB = topology.spawnActor("b", new TalkerB());
		final Address talkerA = topology.spawnActor("a", new TalkerA(talkerB));

		on(topology).send("hello").to(talkerA);

	}
}
