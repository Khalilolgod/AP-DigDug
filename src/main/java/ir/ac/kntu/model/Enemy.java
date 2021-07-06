package ir.ac.kntu.model;


import ir.ac.kntu.controller.EnemyController;
import javafx.geometry.Point2D;

public class Enemy extends GameObject implements Movable,Observable{

    double stepSize;
    Direction direction;
    Observer observer;
    private EnemyController enemyController;
    Enemy(Map map, Point2D pos) {
        super(map,pos,false);
        this.stepSize = 1;
        this.direction =  Direction.RIGHT;
        this.observer = map;
    }



    public Point2D nextPos(Direction d) {
        switch (d){
            case UP:
                return pos.add(Direction.UP.getDelta().multiply(stepSize));
            case RIGHT:
                return pos.add(Direction.RIGHT.getDelta().multiply(stepSize));
            case DOWN:
                return pos.add(Direction.DOWN.getDelta().multiply(stepSize));
            case LEFT:
                return pos.add(Direction.LEFT.getDelta().multiply(stepSize));
            default:
                return pos;
        }
    }

    @Override
    public boolean canPass(Direction d) {
        Point2D movedPos = nextPos(d);
        return map.isInBounds(movedPos) && (map.getGameObject(movedPos) == null || map.getGameObject(movedPos).isCollisionFriendly);
    }

    @Override
    public void move(Direction d) {
        if (canPass(d)){
            updateObserver(pos,nextPos(d));
            this.pos = nextPos(d);
        }
    }

    @Override
    public void updateObserver(Point2D oldPos, Point2D newPos) {
        observer.update(this,oldPos,newPos);
    }

    public EnemyController getEnemyController() {
        return enemyController;
    }

    public void setEnemyController(EnemyController enemyController) {
        this.enemyController = enemyController;
    }
}
