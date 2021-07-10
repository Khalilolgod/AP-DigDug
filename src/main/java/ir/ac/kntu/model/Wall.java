package ir.ac.kntu.model;

import javafx.geometry.Point2D;

public class Wall extends GameObject {
    Wall(Map map, Point2D pos) {
        super(map, pos, false);
    }
}
