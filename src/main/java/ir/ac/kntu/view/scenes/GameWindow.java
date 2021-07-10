package ir.ac.kntu.view.scenes;

import ir.ac.kntu.controller.EventHandler;
import ir.ac.kntu.model.Digger;
import ir.ac.kntu.model.Game;
import ir.ac.kntu.model.GameState;
import ir.ac.kntu.util.FileChooserWrapper;
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
import javafx.stage.Stage;

public class GameWindow {

    private Game game;

    private Stage stage;
    private BorderPane borderPane;
    private Scene scene;
    private Pane upper;
    private Pane center;
    private Pane down;
    private GameScene gameScene;

    private Label timerL;
    private Label lives;

    public GameWindow(Game game, Stage stage) {
        this.stage = stage;
        borderPane = new BorderPane();
        this.game = game;

        makeScene();
    }

    void makeCenterPane() {
        gameScene = new GameScene(game.getMap());
        center = gameScene.getGridPane();
    }

    void makeBorderPane() {
        makeCenterPane();
        makeUpper();
        makeLowerPane();
        borderPane.setTop(upper);
        borderPane.setCenter(center);
        borderPane.setBottom(down);
    }

    void makeLowerPane() {
        down = new Pane();
        down.prefHeight(50);
        timerL = new Label(String.valueOf(game.getTime()));
        lives = new Label(String.valueOf(game.getDiggerLives()));
        HBox hBox = new HBox(timerL, lives);
        down.getChildren().add(hBox);

    }

    private void makeScene() {
        makeBorderPane();
        gameScene.gridPaneUpdater();
        gameScene.getGridPane().setGridLinesVisible(false);
        scene = new Scene(borderPane, game.getMap().getxBlocks() * GraphicsConsts.getCellsize(), (game.getMap().getyBlocks() + 3) * GraphicsConsts.getCellsize());
        EventHandler.getInstance().attachGameEventHandlers(scene);
    }

    public void run() {
        //long running operation runs on different thread
        Thread thread = new Thread(() -> {
            Runnable updater = () -> {
                gameScene.gridPaneUpdater();
                game.updateGame();
                lives.setText(String.valueOf(game.getDiggerLives()));
            };
            Runnable diggerSaver = () -> {
                String path = FileChooserWrapper.getInstance().showSaveDialog(stage);
                Digger.saveDigger(game.getDigger(), path);
            };
            while (true) {
                if (game.getGameState() == GameState.RUNNING) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }
                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                } else if (game.getGameState() == GameState.FINISHED) {
                    Platform.runLater(diggerSaver);
                    break;
                }
            }
        });
        thread.setDaemon(true);
        thread.start();

        Thread timer = makeTimerThread();
        timer.setDaemon(true);
        timer.start();

    }


    private Thread makeTimerThread() {
        Thread timer = new Thread(() -> {
            Runnable updater = () -> {
                game.setTime(game.getTime()+1);
                timerL.setText(String.valueOf(game.getTime()));
            };

            while (true) {
                if (game.getGameState() == GameState.RUNNING) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                } else if (game.getGameState() == GameState.FINISHED) {
                    break;
                }
            }

        });
        return timer;
    }

    Pane makeUpper() {
        upper = new Pane();
        upper.setPrefSize(200, 50);
        VBox upLeft = new VBox();
        Label scoreL = new Label("Score");
        Label scoreVar = new Label("0");
        upLeft.getChildren().addAll(scoreL, scoreVar);

        //upLeft.setAlignment(Pos.CENTER_LEFT);

        VBox upCenter = new VBox();
        Label highScoreL = new Label("High Score");
        Label highScoreVar = new Label("-");
        upCenter.getChildren().addAll(highScoreL, highScoreVar);

        Button menuButton = new Button("#");
        menuButton.setPrefSize(50, 50);

        HBox hBox = new HBox(upLeft, upCenter, menuButton);

        upper.getChildren().add(hBox);
        hBox.setMinWidth(center.getWidth());
        hBox.setAlignment(Pos.CENTER);
        return upper;
    }

    public Game getGame() {
        return game;
    }

    public Scene getScene() {
        return scene;
    }

    public Label getTimerL() {
        return timerL;
    }

    public void setTimerL(Label timerL) {
        this.timerL = timerL;
    }

    public Label getLives() {
        return lives;
    }

    public void setLives(Label lives) {
        this.lives = lives;
    }
}

