package ir.ac.kntu.model;


import java.io.*;

public class Digger extends GameObject implements Movable, Observable, Serializable {
    private String playerName;
    private int score = 0;
    private int highScore = 0;
    private double stepSize;
    private Direction direction;
    private Observer observer;
    private final int initialX;
    private final int inintialY;
    private int shootingRange = 3;
    private static final long serialVersionUID = 24L;

    Digger(Map map, Point2D pos, double stepSize, Observer observer) {
        super(map, pos, true);
        this.stepSize = stepSize;
        this.direction = Direction.RIGHT;
        this.initialX = (int) pos.getX();
        this.inintialY = (int) pos.getY();

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
        return getMap().isInBounds(movedPos) && (getMap().getGameObject(movedPos) == null || getMap().getGameObject(movedPos) instanceof Wall);

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

    public static void saveDigger(Digger digger, String filePath) {
        File file = new File(filePath);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream output = new ObjectOutputStream(fileOutputStream)) {
            try {
                output.writeObject(digger);
            } catch (IOException e) {
                System.out.println("(Digger::saveDiggerInfos): " +
                        "An error occurred while trying to save info");
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.out.println("(Digger::saveDiggerInfos): " +
                    "An error occurred while trying to save info");
            e.printStackTrace();
        }
    }

    public void shoot(){
        ((Game)observer).diggershoot(this);
    }

    public static Digger loadDigger(String filePath) {

        File file = new File(filePath);
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream input = new ObjectInputStream(fileInputStream)) {

            try {
                //Read info for each student
                Digger digger = (Digger) input.readObject();
                digger.setScore(0);
                return digger;
            } catch (Exception e) {
                System.out.println("Problem with some of the records in the Digger data file");
                System.out.println(e.getMessage());
            }

        } catch (IOException e) {
            System.out.println("No previous data for Digger has been saved.");
        }
        return null;
    }


    @Override
    public void updateObserver(Point2D oldPos, Point2D newPos) {
        observer.update(this, oldPos, newPos);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        highScore = Math.max(score, highScore);
        this.score = score;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
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

    public int getInitialX() {
        return initialX;
    }

    public int getInintialY() {
        return inintialY;
    }

    public int getShootingRange() {
        return shootingRange;
    }

    public void setShootingRange(int shootingRange) {
        this.shootingRange = shootingRange;
    }
}
