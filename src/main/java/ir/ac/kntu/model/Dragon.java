package ir.ac.kntu.model;

import ir.ac.kntu.controller.EnemyController;
import javafx.geometry.Point2D;

public class Dragon extends Enemy{

    Dragon(Map map, Point2D pos, Observer observer) {
        super(map, pos, observer);
        this.setEnemyController(new EnemyController(this));
    }
}
