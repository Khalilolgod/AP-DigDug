package ir.ac.kntu.model;

import javafx.geometry.Point2D;

/**
 * @author Khalilollah
 */
public interface Observer {

    /**
     * This method should invoke when the observed object state has been
     * changed.
     *
     * @param changedObservable pass the changed observable object to the
     *                          observer
     */
    void update(Observable changedObservable, Point2D oldPos , Point2D newPos);
}
