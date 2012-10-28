package jp.dev7.game13.actor.ai;

public class AiInterface {

    private AI ai;

    public AiInterface(AI ai) {
        this.ai = ai;
    }

    public String getName() {
        return ai.getName();
    }

    public void setName(String name) {
        ai.setName(name);
    }

    public int getAge() {
        return ai.getAge();
    }

    public void fire() {
        ai.fire();
    }

}
