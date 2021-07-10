package ir.ac.kntu.view.scenes;

import ir.ac.kntu.controller.EventHandler;
import ir.ac.kntu.model.Game;
import ir.ac.kntu.model.GameState;
import ir.ac.kntu.view.GraphicsConsts;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class GameWindow {

    private Game game;

    private BorderPane borderPane;
    private Scene scene;
    private Pane upper;
    private Pane center;
    private Pane down;
    private GameScene gameScene;

    public GameWindow(Game game){
        borderPane = new BorderPane();
        this.game = game;

        makeScene();
    }

    void makeCenterPane(){
        gameScene = new GameScene(game.getMap());
        center = gameScene.getGridPane();
    }

    void makeBorderPane(){
        makeCenterPane();
        makeUpper();
        borderPane.setTop(upper);
        borderPane.setCenter(center);
    }

    private void makeScene(){
        makeBorderPane();
        gameScene.gridPaneUpdater();
        gameScene.getGridPane().setGridLinesVisible(false);
        scene = new Scene(borderPane, game.getMap().getX_Blocks()* GraphicsConsts.CellSize,game.getMap().getY_Blocks()*GraphicsConsts.CellSize);
        EventHandler.getInstance().attachGameEventHandlers(scene);
    }

    public void run(){
        //longrunning operation runs on different thread
        Thread thread = new Thread(() -> {

            Runnable updater = () -> {
                gameScene.gridPaneUpdater();
                game.updateGame();
            };
            while (game.getGameState() == GameState.RUNNING) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                }
                // UI update is run on the Application thread
                Platform.runLater(updater);
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    Pane makeUpper(){
        upper = new Pane();
        upper.setPrefSize(200,50);
        VBox upLeft = new VBox();
        Label scoreL = new Label("Score");
        Label scoreVar = new Label("0");
        upLeft.getChildren().addAll(scoreL,scoreVar);

        //upLeft.setAlignment(Pos.CENTER_LEFT);

        VBox upCenter = new VBox();
        Label highScoreL = new Label("High Score");
        Label highScoreVar = new Label("-");
        upCenter.getChildren().addAll(highScoreL,highScoreVar);

        Button menuButton = new Button("#");
        menuButton.setPrefSize(50,50);

        HBox hBox = new HBox(upLeft,upCenter,menuButton);

        upper.getChildren().add(hBox);
        hBox.setMinWidth(center.getWidth());
        hBox.setAlignment(Pos.CENTER);
        return upper;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Pane getUpper() {
        return upper;
    }

    public void setUpper(Pane upper) {
        this.upper = upper;
    }

    public Pane getCenter() {
        return center;
    }

    public void setCenter(Pane center) {
        this.center = center;
    }

    public Pane getDown() {
        return down;
    }

    public void setDown(Pane down) {
        this.down = down;
    }
}
