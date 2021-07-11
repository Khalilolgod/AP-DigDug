package ir.ac.kntu;

import ir.ac.kntu.view.scenes.MainMenu;
import javafx.application.Application;

import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class JavaFxApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {

//        //GameAssets.getInstance().loadAssets();
//        GameScene gameScene = new GameScene(map);

        MainMenu mainMenu = new MainMenu(stage);
        stage.setScene(mainMenu.getScene());
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("DigDug");

        stage.show();
        //gameScene.gridPaneUpdater();

    }
}
