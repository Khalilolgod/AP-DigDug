package ir.ac.kntu.model;


import javafx.geometry.Point2D;

public class Digger extends GameObject implements Movable, Observable {
    String playerName;
    int score = 0;
    int highScore = 0;
    double stepSize;
    Direction direction;
    Observer observer;
    final Point2D initialPos;

    Digger(Map map, Point2D pos, double stepSize , Observer observer) {
        super(map, pos, true);
        this.stepSize = stepSize;
        this.direction = Direction.RIGHT;
        initialPos = pos;
        this.observer = observer;
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
        return map.isInBounds(movedPos) && (map.getGameObject(movedPos) == null || map.getGameObject(movedPos) instanceof Wall);

    }

    @Override
    public void move(Direction d) {
        if (canPass(d)) {
            this.direction = d;
            updateObserver(getPos(), nextPos(d));
            this.setPos(nextPos(d));
        }
    }

    @Override
    public boolean isColliding(GameObject g, Direction d) {
        return nextPos(d).distance(g.getPos()) < 1;
    }


    @Override
    public void updateObserver(Point2D oldPos, Point2D newPos) {
        observer.update(this, oldPos, newPos);
    }
}
