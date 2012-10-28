package jp.dev7.game13.actor.phisics;

import static com.googlecode.actorom.dsl.Messaging.*;
import jp.dev7.game13.message.BackMessage;
import jp.dev7.game13.message.ForwardMessage;
import jp.dev7.game13.message.aievent.EnterframeMessage;

import com.googlecode.actorom.Address;
import com.googlecode.actorom.Topology;
import com.googlecode.actorom.annotation.OnMessage;
import com.googlecode.actorom.annotation.TopologyInstance;

public class Doll extends Entity {

    @TopologyInstance
    private Topology topology;

    private Address ai;

    private int forwardEnd;
    private int backEnd;

    private double accel = 1;
    private double maxSpeed = 3;

    public void setAi(Address ai) {
        this.ai = ai;
    }

    @Override
    public void tick() {
        super.tick();
        if (ai != null) {
            on(topology).send(new EnterframeMessage()).to(ai);
        }

        if (getAge() < forwardEnd) {
            setSpeed(Math.min(maxSpeed, getSpeed() + accel));
        }

        if (getAge() < backEnd) {
            setSpeed(Math.max(-maxSpeed, getSpeed() - accel));
        }
    }

    @OnMessage(type = ForwardMessage.class)
    public void forward(ForwardMessage message) {
        backEnd = getAge();
        forwardEnd = getAge() + message.time;
    }

    @OnMessage(type = BackMessage.class)
    public void back(BackMessage message) {
        forwardEnd = getAge();
        backEnd = getAge() + message.time;
    }

}
