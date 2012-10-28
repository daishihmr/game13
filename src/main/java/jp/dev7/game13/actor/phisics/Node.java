package jp.dev7.game13.actor.phisics;

public abstract class Node {

    private Scene scene;

    private int age;

    public Node() {
        age = -1;
    }

    public void tick() {
        this.age += 1;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public int getAge() {
        return age;
    }
}
