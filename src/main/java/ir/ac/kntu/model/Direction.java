package ir.ac.kntu.model;

import javafx.geometry.Point2D;

public enum Direction {
    UP(new Point2D(0, -1)), RIGHT(new Point2D(1, 0)), DOWN(new Point2D(0, 1)), LEFT(new Point2D(-1, 0));

    private Point2D delta;

    Direction(Point2D delta) {
        this.delta = delta;
    }

    public Point2D getDelta() {
        return delta;
    }
}
