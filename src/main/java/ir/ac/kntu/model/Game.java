package ir.ac.kntu.model;


import java.io.*;
import java.util.ArrayList;

public class Game implements Serializable, Observer {
    private Map map;
    private Digger digger;
    private int diggerLives;
    private ArrayList<Enemy> enemies;
    private int time = 0;
    private int highscore;
    private int score;
    private int round;
    private GameState gameState;

    private static final long serialVersionUID = 42L;


    public Game() {
        gameState = GameState.RUNNING;
        diggerLives = 3;
    }



    public void updateGame() {
        for (Enemy enemy : enemies) {
            enemy.move();
        }
    }

    boolean playerCollided() {
        for (Enemy e : enemies) {
            if (e.getPos().distance(digger.getPos()) < 1) {
                return true;
            }
        }
        return false;
    }

    private void playerDie() {
        if (--diggerLives < 1) {
            finishGame();
        }
        playerRevival();
    }

    void playerRevival() {
        this.digger.setPos(new Point2D(digger.getInitialX(), digger.getInintialY()));
        map.getMap()[(int) digger.getInintialY()][(int) digger.getInitialX()] = digger;
    }

    void finishGame() {
        this.gameState = GameState.FINISHED;
    }

    @Override
    public void update(Observable changedObservable, Point2D oldPos, Point2D newPos) {
        GameObject[][] map = this.map.getMap();
        GameObject gameObject = (GameObject) changedObservable;
        if (this.map.getGameObject(oldPos) instanceof Digger) {
            if (playerCollided()) {
                playerDie();
            } else {
                map[(int) newPos.getY()][(int) newPos.getX()] = gameObject;
                map[(int) oldPos.getY()][(int) oldPos.getX()] = null;
            }
        } else {
            if (playerCollided()) {
                playerDie();
            }
            map[(int) newPos.getY()][(int) newPos.getX()] = gameObject;
            map[(int) oldPos.getY()][(int) oldPos.getX()] = null;
        }

    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
        enemies = map.getEnemies();
        digger = Map.getDigger();
    }

    public Digger getDigger() {
        return digger;
    }

    public void setDigger(Digger digger) {
        this.digger = digger;
    }

    public int getDiggerLives() {
        return diggerLives;
    }

    public void setDiggerLives(int diggerLives) {
        this.diggerLives = diggerLives;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public GameState getGameState() {
        return gameState;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
