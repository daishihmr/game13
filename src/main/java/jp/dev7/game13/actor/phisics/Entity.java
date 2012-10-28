package jp.dev7.game13.actor.phisics;

public class Entity extends Node {

    private double x;
    private double y;
    private double radius;

    private double speed;
    private double direction;

    public boolean within(Entity another, double distance) {
        return (x - another.x) * (x - another.x) + (y - another.y)
                * (y - another.y) <= (radius + another.radius);
    }

    @Override
    public void tick() {
        super.tick();
        x += Math.cos(direction) * speed;
        y += Math.sin(direction) * speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

}
