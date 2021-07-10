package ir.ac.kntu.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameAssets {
    private static GameAssets instance = new GameAssets();
    private final static Image DIGGER = new Image("File:src/main/resources/assets/player_right_0.png");
    private final static Image STONE = new Image("File:src/main/resources/assets/STONE.png");
    private final static Image TOMATO = new Image("File:src/main/resources/assets/TOMATO.png");
    private final static Image DRAGON = new Image("File:src/main/resources/assets/DRAGON.png");
    private static Image[] bgBLOCKS;

    private GameAssets() {
        //DIGGER = //todo Change this to ImageView
        loadBlocks();
    }

    public static GameAssets getInstance() {
        return instance;
    }


    private void loadBlocks() {
        bgBLOCKS = new Image[4];
        try {
            bgBLOCKS[0] = new Image("File:src/main/resources/assets/block1.jpg");
            bgBLOCKS[1] = new Image("File:src/main/resources/assets/block2.jpg");
            bgBLOCKS[2] = new Image("File:src/main/resources/assets/block3.jpg");
            bgBLOCKS[3] = new Image("File:src/main/resources/assets/block4.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static ImageView getDigger() {
        return getInstance().toImageView(DIGGER);
    }

    public static Rectangle getEmpty() {
        return new Rectangle(GraphicsConsts.getCellsize(), GraphicsConsts.getCellsize(), Color.BLACK);
    }

    private ImageView toImageView(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(GraphicsConsts.getCellsize());
        return imageView;
    }

    public static ImageView getStone() {
        return instance.toImageView(STONE);
    }

    public static ImageView getTomato() {
        return getInstance().toImageView(TOMATO);
    }

    public static ImageView getDragon() {
        return getInstance().toImageView(DRAGON);
    }

    public static Image[] getBgBLOCKS() {
        return bgBLOCKS;
    }
}
