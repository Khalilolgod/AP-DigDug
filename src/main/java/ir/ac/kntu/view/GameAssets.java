package ir.ac.kntu.view;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameAssets {
    private static GameAssets instance = new GameAssets();
    private final static Image digger = new Image("File:src/main/resources/assets/mushroom.png");
    private static Image[] BG_BLOCKS ;

    private GameAssets() {
        //digger = //todo Change this to ImageView
        loadBlocks();
    }

    public static GameAssets getInstance() {
        return instance;
    }

    private void loadBlocks(){
        BG_BLOCKS = new Image[4];
        try {
            BG_BLOCKS[0] = new Image("File:src/main/resources/assets/block1.jpg");
            BG_BLOCKS[1] = new Image("File:src/main/resources/assets/block2.jpg");
            BG_BLOCKS[2] = new Image("File:src/main/resources/assets/block3.jpg");
            BG_BLOCKS[3] = new Image("File:src/main/resources/assets/block4.jpg");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static Image getDigger() {
        return digger;
    }

    public static Rectangle getEmpty(){
        return new Rectangle(GraphicsConsts.CellSize,GraphicsConsts.CellSize,Color.BLACK);
    }

    public static Image[] getBgBlocks() {
        return BG_BLOCKS;
    }
}
