package jp.dev7.game13.actor.phisics;

import java.util.List;

import com.google.common.collect.Lists;

public class Group extends Node {

    private final List<Node> childNodes = Lists.newLinkedList();

    public void addChild(Node child) {
        child.setScene(getScene());
        childNodes.add(child);
    }

    public void removeChild(Node child) {
        child.setScene(null);
        childNodes.remove(child);
    }

    @Override
    public void tick() {
        super.tick();
        for (Node childNode : childNodes) {
            childNode.tick();
        }
    }

}
