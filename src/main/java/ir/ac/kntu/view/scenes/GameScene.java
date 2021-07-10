package ir.ac.kntu.view.scenes;


import ir.ac.kntu.model.*;
import ir.ac.kntu.view.GameAssets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GameScene {

    private Scene scene;
    private Map map;
    private GridPane gridPane;

    public GameScene(Map map) {
        this.map = map;
        this.gridPane = new GridPane();
        //makeScene();
    }


    public void gridPaneUpdater() {
        try {
            gridPane.getChildren().clear();
            Image[] images = GameAssets.getInstance().getBgBLOCKS();
            ImageView digger = GameAssets.getInstance().getDigger();
            for (int k = 0; k < 4; k++) {
                for (int j = map.getyBlocks() / 4 * k; j < map.getyBlocks() / 4 * (k + 1); j++) {
                    for (int i = 0; i < map.getxBlocks(); i++) {
                        if (map.getMap()[j][i] == null) {
                            gridPane.add(GameAssets.getEmpty(), i, j);
                        } else if (map.getMap()[j][i] instanceof Digger) {
                            gridPane.add(digger, i, j);
                        } else if (map.getMap()[j][i] instanceof Wall) {
                            gridPane.add(new ImageView(images[k]), i, j);
                        } else if (map.getMap()[j][i] instanceof Stone) {
                            gridPane.add(GameAssets.getStone(), i, j);
                        } else if (map.getMap()[j][i] instanceof Dragon) {
                            gridPane.add(GameAssets.getDragon(), i, j);
                        } else if (map.getMap()[j][i] instanceof Tomato) {
                            gridPane.add(GameAssets.getTomato(), i, j);
                        }
                    }
                }
            }
            for (int j = map.getyBlocks() / 4 * 4; j < map.getyBlocks(); j++) {
                for (int i = 0; i < map.getxBlocks(); i++) {
                    if (map.getMap()[j][i] == null) {
                        gridPane.add(GameAssets.getEmpty(), i, j);
                    } else if (map.getMap()[j][i] instanceof Digger) {
                        gridPane.add(digger, i, j);
                    } else if (map.getMap()[j][i] instanceof Wall) {
                        gridPane.add(new ImageView(images[3]), i, j);
                    } else if (map.getMap()[j][i] instanceof Stone) {
                        gridPane.add(GameAssets.getInstance().getStone(), i, j);
                    } else if (map.getMap()[j][i] instanceof Dragon) {
                        gridPane.add(GameAssets.getDragon(), i, j);
                    } else if (map.getMap()[j][i] instanceof Tomato) {
                        gridPane.add(GameAssets.getTomato(), i, j);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }
}
