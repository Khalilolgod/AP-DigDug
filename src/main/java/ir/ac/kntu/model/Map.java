package ir.ac.kntu.model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Map implements Serializable {

    private final char playerChar = 'p';
    private final char emptyChar = ' ';
    private final char stoneChar = 's';
    private final char wallChar = '#';
    private final char dragonChar = 'E';
    private final char tomatoChar = 'e';
    private static Digger digger;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private GameObject[][] map;
    private int xBlocks;
    private int yBlocks;
    private Game game;

    public Map(Game game) {
        this.game = game;
        game.setMap(this);
        loadMap("src/main/resources/maps/map1.txt");
        game.setMap(this);
    }

    public Map(String fileSource, Game game) {
        this.game = game;
        game.setMap(this);
        loadMap(fileSource);
        game.setMap(this);
    }

    public void loadMap(String mapFile) {
        ArrayList<ArrayList<Character>> map = new ArrayList<>();
        File file = new File(mapFile);
        int x = 0, y = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String mapRow = scanner.nextLine();
                x = mapRow.length();
                map.add(new ArrayList<>());
                for (int j = 0; j < x; j++) {
                    map.get(y).add(mapRow.charAt(j));
                }
                y++;
            }

        } catch (Exception e) {
            System.out.println("Problem with data file");
            e.printStackTrace();
        }
        xBlocks = x;
        yBlocks = y;
        //this.defaultMap = new char[y][x];
        this.map = new GameObject[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                GameObject gameObject = charToGameObject(map.get(i).get(j), i, j);
                if (gameObject instanceof Digger) {
                    digger = (Digger) gameObject;
                } else if (gameObject instanceof Enemy) {
                    enemies.add((Enemy) gameObject);
                }
                this.map[i][j] = gameObject;
            }
        }
    }

    private GameObject charToGameObject(char c, int i, int j) {
        switch (c) {
            case emptyChar:
                return null;
            case wallChar:
                return new Wall(this, new Point2D(j, i));
            case stoneChar:
                return new Stone(this, new Point2D(j, i));
            case playerChar:
                return new Digger(this, new Point2D(j, i), 1, game);
            case dragonChar:
                return new Dragon(this, new Point2D(j, i), game);
            case tomatoChar:
                return new Tomato(this, new Point2D(j, i), game);
            default:
                return new Wall(this, new Point2D(j, i));
        }
    }

    public GameObject getGameObject(Point2D pos) {
        return map[(int) pos.getY()][(int) pos.getX()];
    }

    public boolean isInBounds(Point2D pos) {
        return pos.getX() >= 0 && pos.getX() < getxBlocks() && pos.getY() < getyBlocks() && pos.getY() >= 0;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public int getyBlocks() {
        return yBlocks;
    }

    public int getxBlocks() {
        return xBlocks;
    }

    public GameObject[][] getMap() {
        return map;
    }

    public void showMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] instanceof Digger ? playerChar : "#");
            }
            System.out.println();
        }
        System.out.println("***************************************");
    }

    public static Digger getDigger() {
        return digger;
    }

    public char getPlayerChar() {
        return playerChar;
    }

    public char getEmptyChar() {
        return emptyChar;
    }

    public char getStoneChar() {
        return stoneChar;
    }

    public char getWallChar() {
        return wallChar;
    }

    public char getDragonChar() {
        return dragonChar;
    }

    public char getTomatoChar() {
        return tomatoChar;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
