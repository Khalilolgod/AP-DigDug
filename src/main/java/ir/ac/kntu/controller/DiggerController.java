package ir.ac.kntu.controller;

import ir.ac.kntu.model.Digger;
import ir.ac.kntu.model.Direction;
import ir.ac.kntu.model.Map;
import javafx.scene.input.KeyCode;

public class DiggerController {

    private static DiggerController instance = new DiggerController();

    public static DiggerController getInstance() {
        return instance;
    }

    private DiggerController() {
    }

    public void handlePlayerInputs(KeyCode code) {
        Digger digger = Map.getDigger();
        switch (code) {
            case UP:
                digger.move(Direction.UP);
                break;
            case RIGHT:
                digger.move(Direction.RIGHT);
                break;
            case DOWN:
                digger.move(Direction.DOWN);
                break;
            case LEFT:
                digger.move(Direction.LEFT);
                break;
            case S:
                digger.shoot();
            default:
                break;
        }
    }
}
