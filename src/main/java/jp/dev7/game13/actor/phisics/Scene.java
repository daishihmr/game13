package jp.dev7.game13.actor.phisics;

import java.awt.geom.Rectangle2D;

public class Scene extends Group {

    private int frame;
    private Rectangle2D.Double size;

    public Scene() {
        frame = 0;
        size = new Rectangle2D.Double(0, 0, 1200, 1200);
    }

    public double getWidth() {
        return size.width;
    }

    public double getHeight() {
        return size.height;
    }

    @Override
    public Scene getScene() {
        return this;
    }

    @Override
    public void setScene(Scene scene) {
        // no op
    }

    @Override
    public void tick() {
        frame += 1;
        super.tick();
    }
}
