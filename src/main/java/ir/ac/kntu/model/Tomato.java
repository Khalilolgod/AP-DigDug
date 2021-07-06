package ir.ac.kntu.model;

import ir.ac.kntu.controller.EnemyController;
import javafx.geometry.Point2D;

public class Tomato extends Enemy{
    Tomato(Map map, Point2D pos) {
        super(map, pos);
        setEnemyController(new EnemyController(this));
    }
}
