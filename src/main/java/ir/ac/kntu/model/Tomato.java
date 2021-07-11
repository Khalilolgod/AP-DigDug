package ir.ac.kntu.model;


public class Tomato extends Enemy {

    Tomato(Map map, Point2D pos, Observer observer) {
        super(map, pos, observer);
        this.setDeathScore(50);
    }
}
