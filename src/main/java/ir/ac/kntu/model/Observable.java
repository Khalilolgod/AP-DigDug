package ir.ac.kntu.model;


/**
 *
 * @author Khalilollah
 */
public interface Observable {
    /**
     * update observers.
     */
    public void updateObserver(Point2D oldPos,Point2D newPos);
}

