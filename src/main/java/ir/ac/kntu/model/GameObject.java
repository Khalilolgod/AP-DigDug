package ir.ac.kntu.model;

import javafx.geometry.Point2D;

public abstract class GameObject {
    private Point2D pos;
    Map map;
    boolean isCollisionFriendly;
    GameObject(Map map, Point2D pos,boolean isCollisionFriendly){
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
}
