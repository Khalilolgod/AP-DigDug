package ir.ac.kntu.model;

import ir.ac.kntu.controller.EnemyController;
import javafx.geometry.Point2D;

public class Dragon extends Enemy{

    Dragon(Map map, Point2D pos) {
        super(map, pos);
        this.setEnemyController(new EnemyController(this));
    }
}
