package ir.ac.kntu.view.scenes;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class TutorialScene {
    private Scene scene;
    private StackPane pane;
    private Stage stage;
    private MainMenu mainMenu;

    TutorialScene(Stage stage,MainMenu mainMenu){
        this.stage = stage;
        this.mainMenu = mainMenu;
        this.pane = new StackPane();
        makeScene();
    }

    private void makeScene(){
        ImageView img = new ImageView(new Image("File:src/main/resources/background/tutorial.jpg"));
        img.setPreserveRatio(true);
        img.setFitHeight(600);
        pane.setMinSize(img.getFitWidth(), 600);
        pane.getChildren().add(img);

        Button reTurn = new Button("Return");
        reTurn.setOnAction(event -> {
            Platform.runLater(()->{
                stage.setScene(mainMenu.getScene());
                stage.show();
            });
        });
        pane.getChildren().add(reTurn);
//        reTurn.setTranslateX();
//        reTurn.setTranslateY();

        scene = new Scene(pane);

    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
