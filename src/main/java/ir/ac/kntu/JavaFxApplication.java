package ir.ac.kntu;

import ir.ac.kntu.model.Enemy;
import ir.ac.kntu.model.Map;
import ir.ac.kntu.view.GameAssets;
import ir.ac.kntu.view.GraphicsConsts;
import ir.ac.kntu.view.scenes.GameScene;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class JavaFxApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {

        Map map = new Map();
        //GameAssets.getInstance().loadAssets();
        GameScene gameScene = new GameScene(map);

        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("DigDug");

        stage.setScene(gameScene.getScene());

        // longrunning operation runs on different thread
        Thread thread = new Thread(() -> {

            Runnable updater = () -> gameScene.gridPaneUpdater();
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                }
                // UI update is run on the Application thread
                Platform.runLater(updater);
            }
        });
        Thread enemymoving = new Thread(() -> {
            Runnable updater = () -> {
                for(Enemy e : Map.getEnemies()){
                    e.getEnemyController().move();
                }
            };
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                }
                // UI update is run on the Application thread
                Platform.runLater(updater);
            }
        });
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();
        enemymoving.setDaemon(true);
        enemymoving.start();

        stage.show();
        //gameScene.gridPaneUpdater();

    }
}
