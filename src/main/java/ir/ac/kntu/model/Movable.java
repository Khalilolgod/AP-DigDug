package ir.ac.kntu.model;

public interface Movable {
    boolean canPass(Direction d);
    void move(Direction d);
    boolean isColliding(GameObject g, Direction d);
}
