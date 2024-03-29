package ir.ac.kntu.view.scenes;

import ir.ac.kntu.model.Digger;
import ir.ac.kntu.util.FileChooserWrapper;
import ir.ac.kntu.model.Game;
import ir.ac.kntu.model.Map;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class MainMenu {
    private final Scene scene;
    private Pane pane;
    private Stage stage;

    public MainMenu(Stage stage) {
        pane = new Pane();
        this.stage = stage;
        scene = new Scene(makePane());
    }

    Pane makePane() {
        ImageView img = new ImageView(new Image("File:src/main/resources/background/background3.jpg"));
        img.setPreserveRatio(true);
        img.setFitHeight(600);
        pane.setMinSize(img.getFitWidth(), 600);

        pane.getChildren().add(img);


        Title title = new Title("D I G D U G");
        title.setTranslateX(465);
        title.setTranslateY(250);

        MenuItem itemExit = new MenuItem("EXIT");
        itemExit.setOnMouseClicked(event -> System.exit(0));

        MenuItem newGame = new MenuItem("New Game");
        newGame.setOnMouseClicked(mouseEvent -> {
            GameWindow gameWindow = initializeGameWindow(stage, false);
            stage.setScene(gameWindow.getScene());
            gameWindow.run();
            stage.show();
        });
        MenuItem loadGame = new MenuItem("Load Game");
        loadGame.setOnMouseClicked(mouseEvent -> {
            GameWindow gameWindow = initializeGameWindow(stage, true);
            stage.setScene(gameWindow.getScene());
            gameWindow.run();
            stage.show();
        });

        MenuItem tutorial = new MenuItem("Tutorial");
        tutorial.setOnMouseClicked(mouseEvent -> {
            TutorialScene tutorialScene = new TutorialScene(stage,this);
            stage.setScene(tutorialScene.getScene());
            stage.show();
        });

        MenuBox menu = new MenuBox(
                newGame,
                loadGame,
                tutorial,
                itemExit);
        menu.setTranslateX(495);
        menu.setTranslateY(350);

        pane.getChildren().addAll(title, menu);
        return pane;
    }

    public GameWindow initializeGameWindow(Stage stage, boolean isLoadingDigger) {
        String mapPath = FileChooserWrapper.getInstance().showOpenDialog(stage);
        Game game = new Game();
        Map map = new Map(mapPath, game);
        if (isLoadingDigger) {
            String diggerPath = FileChooserWrapper.getInstance().showOpenDialog(stage);
            Digger digger = Digger.loadDigger(diggerPath);
            Map.setDigger(digger);
            game.setDigger(digger);
            if (digger == null) {
                System.out.println("digger is null");
            }
            digger.setObserver(game);
        }
        return new GameWindow(game, stage,this);
    }


    public Scene getScene() {
        return scene;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

class Title extends StackPane {
    public Title(String name) {
        Rectangle bg = new Rectangle(250, 60);
        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(2);
        bg.setFill(null);

        Text text = new Text(name);
        text.setFill(Color.BLACK);
        text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 50));

        setAlignment(Pos.CENTER);
        getChildren().addAll(bg, text);
    }
}

class MenuBox extends VBox {
    public MenuBox(MenuItem... items) {
        getChildren().add(createSeparator());

        for (MenuItem item : items) {
            getChildren().addAll(item, createSeparator());
        }
    }

    private Line createSeparator() {
        Line sep = new Line();
        sep.setEndX(200);
        sep.setStroke(Color.DARKGREY);
        return sep;
    }
}

class MenuItem extends StackPane {
    public MenuItem(String name) {
        LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[]{new Stop(0, Color.DARKBLUE), new Stop(0.1, Color.BLACK), new Stop(0.9, Color.BLACK), new Stop(1, Color.DARKBLUE)});

        Rectangle bg = new Rectangle(200, 30);
        bg.setOpacity(0.4);

        Text text = new Text(name);
        text.setFill(Color.DARKGREY);
        text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 22));

        setAlignment(Pos.CENTER);
        getChildren().addAll(bg, text);

        setOnMouseEntered(event -> {
            bg.setFill(gradient);
            text.setFill(Color.BLACK);
        });


        setOnMouseExited(event -> {
            bg.setFill(Color.BLACK);
            text.setFill(Color.BEIGE);
        });

        setOnMousePressed(event -> {
            bg.setFill(Color.DARKVIOLET);
        });

        setOnMouseReleased(event -> {
            bg.setFill(gradient);
        });
    }
}
