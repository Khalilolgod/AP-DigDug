package ir.ac.kntu.view.scenes;


import ir.ac.kntu.controller.EventHandler;
import ir.ac.kntu.model.Digger;
import ir.ac.kntu.model.Map;
import ir.ac.kntu.model.Wall;
import ir.ac.kntu.view.GameAssets;
import ir.ac.kntu.view.GraphicsConsts;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class GameScene {

    private Scene scene;
    private Map map;
    GridPane gridPane ;
    public GameScene(Map map){
        this.map = map;
        makeScene();
    }

    private void makeScene(){
        try{
            //map.showMap();
            gridPane = new GridPane();
            gridPane.getChildren().clear();
            Image[] images = GameAssets.getInstance().getBgBlocks();
            ImageView digger = new ImageView(GameAssets.getInstance().getDigger());
            for (int k = 0; k < 4; k++) {
                for(int j = map.getY_Blocks() / 4 * k; j < map.getY_Blocks()/4 * (k+1) ; j++) {
                    for (int i = 0; i < map.getX_Blocks(); i++) {
                        if(map.getMap()[j][i] == null){
                            gridPane.add(GameAssets.getEmpty(),i,j);
                        }else if (map.getMap()[j][i] instanceof Digger) {
                            gridPane.add(digger,i,j);
                        }else if(map.getMap()[j][i] instanceof Wall){
                            gridPane.add(new ImageView(images[k]), i, j);
                        }
                    }
                }
            }
            for(int j = map.getY_Blocks() / 4 * 4 ; j < map.getY_Blocks(); j++) {
                for (int i = 0; i < map.getX_Blocks(); i++) {
                    if(map.getMap()[j][i] == null){
                        gridPane.add(GameAssets.getEmpty(),i,j);
                    }else if (map.getMap()[j][i] instanceof Digger) {
                        gridPane.add(digger,i,j);
                    }else if(map.getMap()[j][i] instanceof Wall){
                        gridPane.add(new ImageView(images[3]), i, j);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        gridPane.setGridLinesVisible(false);
        scene = new Scene(gridPane, map.getX_Blocks()*GraphicsConsts.CellSize,map.getY_Blocks()*GraphicsConsts.CellSize);
        EventHandler.getInstance().attachGameEventHandlers(scene);
    }

    public void gridPaneUpdater(){
        try{
            gridPane.getChildren().clear();
            Image[] images = GameAssets.getInstance().getBgBlocks();
            ImageView digger = new ImageView(GameAssets.getInstance().getDigger());
            for (int k = 0; k < 4; k++) {
                for(int j = map.getY_Blocks() / 4 * k; j < map.getY_Blocks()/4 * (k+1) ; j++) {
                    for (int i = 0; i < map.getX_Blocks(); i++) {
                        if(map.getMap()[j][i] == null){
                            gridPane.add(GameAssets.getEmpty(),i,j);
                        }else if (map.getMap()[j][i] instanceof Digger) {
                            gridPane.add(digger,i,j);
                        }else if(map.getMap()[j][i] instanceof Wall){
                            gridPane.add(new ImageView(images[k]), i, j);
                        }
                    }
                }
            }
            for(int j = map.getY_Blocks() / 4 * 4 ; j < map.getY_Blocks(); j++) {
                for (int i = 0; i < map.getX_Blocks(); i++) {
                    if(map.getMap()[j][i] == null){
                        gridPane.add(GameAssets.getEmpty(),i,j);
                    }else if (map.getMap()[j][i] instanceof Digger) {
                        gridPane.add(digger,i,j);
                    }else if(map.getMap()[j][i] instanceof Wall){
                        gridPane.add(new ImageView(images[3]), i, j);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

}
