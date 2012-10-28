package jp.dev7.game13;

import java.io.File;
import java.io.FileNotFoundException;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import jp.dev7.game13.actor.ai.AI;
import jp.dev7.game13.actor.phisics.Doll;
import jp.dev7.game13.actor.phisics.Scene;

import com.googlecode.actorom.Topology;
import com.googlecode.actorom.local.LocalTopology;
import com.googlecode.actorom.support.ThreadingPolicies;

public class BattleMain {

    public static void main(String[] args) throws FileNotFoundException,
            ScriptException {
        final Topology topology = new LocalTopology("local",
                ThreadingPolicies.newOSThreadingPolicy(10, true));

        Scene scene = new Scene();
        Doll dollA = new Doll();
        scene.addChild(dollA);
        Doll dollB = new Doll();
        scene.addChild(dollB);

        ScriptEngineManager engineManager = new ScriptEngineManager();

        AI aiA = new AI(engineManager, new File("a.js"));
        topology.spawnActor("A", aiA);
        aiA.bind(dollA);
        aiA.onload();

        AI aiB = new AI(engineManager, new File("a.js"));
        topology.spawnActor("B", aiB);
        aiB.bind(dollB);
        aiB.onload();

        scene.tick();
    }
}
