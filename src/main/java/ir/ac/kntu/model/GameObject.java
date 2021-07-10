package ir.ac.kntu.model;

import javafx.geometry.Point2D;

import java.io.Serializable;

public abstract class GameObject implements Serializable {
    private Point2D pos;
    private Map map;
    private boolean isCollisionFriendly;

    GameObject(Map map, Point2D pos, boolean isCollisionFriendly) {
        this.pos = pos;
        this.map = map;
        this.isCollisionFriendly = isCollisionFriendly;
    }

    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public boolean isCollisionFriendly() {
        return isCollisionFriendly;
    }

    public void setCollisionFriendly(boolean collisionFriendly) {
        isCollisionFriendly = collisionFriendly;
    }
}
