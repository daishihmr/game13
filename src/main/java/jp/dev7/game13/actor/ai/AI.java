package jp.dev7.game13.actor.ai;

import static com.googlecode.actorom.dsl.Messaging.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.UUID;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import jp.dev7.game13.actor.phisics.Doll;
import jp.dev7.game13.message.BackMessage;
import jp.dev7.game13.message.ForwardMessage;
import jp.dev7.game13.message.aievent.EnterframeMessage;
import jp.dev7.game13.message.aievent.LoadMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.googlecode.actorom.Address;
import com.googlecode.actorom.Topology;
import com.googlecode.actorom.annotation.AddressInstance;
import com.googlecode.actorom.annotation.OnMessage;
import com.googlecode.actorom.annotation.TopologyInstance;

public class AI {
    private static final Logger LOG = LoggerFactory.getLogger(AI.class);

    @TopologyInstance
    private Topology topology;

    @AddressInstance
    private Address myAddress;

    private Doll body;
    private Address bodyAddress;

    private String name = "NO_NAME";

    private ScriptEngine engine;
    private Bindings bindings;

    public AI(ScriptEngineManager engineManager, File scriptFile)
            throws ScriptException, FileNotFoundException {
        engine = engineManager.getEngineByExtension("js");
        bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
        engine.eval(new InputStreamReader(getClass().getResourceAsStream(
                "/BattleAI.js"), Charsets.UTF_8));
        bindings.put("__ai__", new AiInterface(this));

        engine.eval(new InputStreamReader(new FileInputStream(scriptFile),
                Charsets.UTF_8));
    }

    public void bind(Doll body) {
        this.body = body;
        this.bodyAddress = topology.spawnActor(UUID.randomUUID().toString(),
                body);
        body.setAi(myAddress);
    }

    @OnMessage(type = EnterframeMessage.class)
    public void onEnterframe() {
        eval("$.dispatchEvent(new Event('enterframe'))");
    }

    @OnMessage(type = LoadMessage.class)
    public void onload() {
        eval("$.dispatchEvent(new Event('load'))");
    }

    private void eval(String script) {
        try {
            engine.eval(script);
        } catch (ScriptException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return body.getAge();
    }

    public void fire() {
        System.out.println("fire");
    }

    public void forward(Number time) {
        if (bodyAddress != null) {
            if (time == null) {
                time = 0;
            }
            on(topology).send(new ForwardMessage(time.intValue())).to(
                    bodyAddress);
        }
    }

    public void back(Number time) {
        if (bodyAddress != null) {
            if (time == null) {
                time = 0;
            }
            on(topology).send(new BackMessage(time.intValue())).to(bodyAddress);
        }
    }
}
