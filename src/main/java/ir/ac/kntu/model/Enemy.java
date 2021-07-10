package ir.ac.kntu.model;


import javafx.geometry.Point2D;

import java.io.Serializable;
import java.util.Random;

public class Enemy extends GameObject implements Movable, Observable, Serializable {

    private double stepSize;
    private Direction direction;
    private Observer observer;
    private Random random;

    Enemy(Map map, Point2D pos, Observer observer) {
        super(map, pos, true);
        this.stepSize = 1;
        this.direction = Direction.RIGHT;
        this.observer = observer;
        this.random = new Random();
    }


    public Point2D nextPos(Direction d) {
        switch (d) {
            case UP:
                return getPos().add(Direction.UP.getDelta().multiply(stepSize));
            case RIGHT:
                return getPos().add(Direction.RIGHT.getDelta().multiply(stepSize));
            case DOWN:
                return getPos().add(Direction.DOWN.getDelta().multiply(stepSize));
            case LEFT:
                return getPos().add(Direction.LEFT.getDelta().multiply(stepSize));
            default:
                return getPos();
        }
    }

    @Override
    public boolean canPass(Direction d) {
        Point2D movedPos = nextPos(d);
        return getMap().isInBounds(movedPos) && (getMap().getGameObject(movedPos) == null || getMap().getGameObject(movedPos).isCollisionFriendly());
    }

    @Override
    public void move(Direction d) {
        if (canPass(d)) {
            this.direction = d;
            updateObserver(getPos(), nextPos(d));
            this.setPos(nextPos(d));
        }
    }

    Direction directionGen() {
        return Direction.values()[random.nextInt(Direction.values().length)];
    }

    public void move() {
        this.move(directionGen());
    }

    @Override
    public boolean isColliding(GameObject g, Direction d) {
        return nextPos(d).distance(g.getPos()) < 1;
    }

    @Override
    public void updateObserver(Point2D oldPos, Point2D newPos) {
        observer.update(this, oldPos, newPos);
    }

    public double getStepSize() {
        return stepSize;
    }

    public void setStepSize(double stepSize) {
        this.stepSize = stepSize;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Observer getObserver() {
        return observer;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }
}
