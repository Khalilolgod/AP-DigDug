package ir.ac.kntu.model;


import ir.ac.kntu.view.scenes.GameScene;
import javafx.geometry.Point2D;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Map implements Observer{

    public final char playerChar = 'p';
    public final char emptyChar = ' ';
    public final char stoneChar = 's';
    public final char wallChar = '#';
    public final char dragonChar = 'E';
    public final char tomatoChar = 'e';
    private static Digger digger;
    private static ArrayList<Enemy> enemies = new ArrayList<>();
    private GameObject[][] map;
    private int X_Blocks;
    private int Y_Blocks;

    public Map() {
        loadMap("src/main/resources/maps/map1.txt");
    }

    public Map(String fileSource) {
        loadMap(fileSource);
    }

    public static ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public static void setEnemies(ArrayList<Enemy> enemies) {
        Map.enemies = enemies;
    }

    public void loadMap(String mapFile) {
        ArrayList <ArrayList<Character>> map = new ArrayList<>();
        File file = new File(mapFile);
        int x = 0,y=0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String map_row = scanner.nextLine();
                x= map_row.length();
                map.add(new ArrayList<>());
                for (int j = 0; j < x; j++) {
                    map.get(y).add(map_row.charAt(j)) ;
                }
                y++;
            }

        } catch (Exception e) {
            System.out.println("Problem with data file");
            e.printStackTrace();
        }
        X_Blocks = x;
        Y_Blocks = y;
        //this.defaultMap = new char[y][x];
        this.map = new GameObject[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                GameObject gameObject = charToGameObject(map.get(i).get(j),i,j);
                if (gameObject instanceof Digger){
                    digger = (Digger) gameObject;
                }else if(gameObject instanceof Enemy){
                    enemies.add((Enemy)gameObject);
                }
                this.map[i][j] = gameObject;
            }
        }
    }

    private GameObject charToGameObject(char c,int i,int j){
        switch(c){
            case emptyChar:
                return null;
            case wallChar:
                return new Wall(this,new Point2D(j,i));
            case stoneChar:
                return new Stone(this,new Point2D(j,i));
            case playerChar:
                return new Digger(this,new Point2D(j,i),1);
            case dragonChar:
                return new Dragon(this,new Point2D(j,i));
            case tomatoChar:
                return new Tomato(this,new Point2D(j,i));
            default:
                return new Wall(this,new Point2D(j,i));
        }
    }

    public GameObject getGameObject(Point2D pos){
        return map[(int) pos.getY()][(int) pos.getX()];
    }

    public boolean isInBounds(Point2D pos){
        return pos.getX() >= 0 && pos.getX() < getX_Blocks() && pos.getY() < getY_Blocks() && pos.getY() >= 0;
    }

    public int getY_Blocks() {
        return Y_Blocks;
    }

    public int getX_Blocks() {
        return X_Blocks;
    }

    public GameObject[][] getMap() {
        return map;
    }

    @Override
    public void update(Observable changedObservable,Point2D oldPos ,Point2D newPos) {
        GameObject gameObject = (GameObject) changedObservable;
        map[(int) newPos.getY()][(int) newPos.getX()] = gameObject;
        map[(int) oldPos.getY()][(int) oldPos.getX()] = null;
    }

    public void showMap(){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]instanceof Digger?playerChar:"#");
            }
            System.out.println();
        }
        System.out.println("***************************************");
    }
    
    public static Digger getDigger() {
        return digger;
    }
}
