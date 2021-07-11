package ir.ac.kntu.model;

import javafx.beans.NamedArg;

import java.io.Serializable;

/**
 * A 2D geometric point that usually represents the x, y coordinates.
 * It can also represent a relative magnitude vector's x, y magnitudes.
 */
public class Point2D implements Serializable {

    /**
     * Point or vector with both coordinates set to 0.
     */
    public static final Point2D ZERO = new Point2D(0.0, 0.0);

    /**
     * The x coordinate.
     *
     * @defaultValue 0.0
     */
    private double x;

    /**
     * The x coordinate.
     *
     * @return the x coordinate
     */
    public final double getX() {
        return x;
    }

    /**
     * The y coordinate.
     *
     * @defaultValue 0.0
     */
    private double y;

    /**
     * The y coordinate.
     *
     * @return the y coordinate
     */
    public final double getY() {
        return y;
    }

    /**
     * Cache the hash code to make computing hashes faster.
     */
    private int hash = 0;

    /**
     * Creates a new instance of {@code Point2D}.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point2D(@NamedArg("x") double x, @NamedArg("y") double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Computes the distance between this point and point {@code (x1, y1)}.
     *
     * @param x1 the x coordinate of other point
     * @param y1 the y coordinate of other point
     * @return the distance between this point and point {@code (x1, y1)}.
     */
    public double distance(double x1, double y1) {
        double a = getX() - x1;
        double b = getY() - y1;
        return Math.sqrt(a * a + b * b);
    }

    /**
     * Computes the distance between this point and the specified {@code point}.
     *
     * @param point the other point
     * @return the distance between this point and the specified {@code point}.
     * @throws NullPointerException if the specified {@code point} is null
     */
    public double distance(Point2D point) {
        return distance(point.getX(), point.getY());
    }

    /**
     * Returns a point with the specified coordinates added to the coordinates
     * of this point.
     *
     * @param x the X coordinate addition
     * @param y the Y coordinate addition
     * @return the point with added coordinates
     * @since JavaFX 8.0
     */
    public Point2D add(double x, double y) {
        return new Point2D(getX() + x, getY() + y);
    }

    /**
     * Returns a point with the coordinates of the specified point added to the
     * coordinates of this point.
     *
     * @param point the point whose coordinates are to be added
     * @return the point with added coordinates
     * @throws NullPointerException if the specified {@code point} is null
     * @since JavaFX 8.0
     */
    public Point2D add(Point2D point) {
        return add(point.getX(), point.getY());
    }

    public Point2D add(int delta, Direction d) {
        return add(d.getDelta().multiply(delta));
    }


    /**
     * Returns a point with the specified coordinates subtracted from
     * the coordinates of this point.
     *
     * @param x the X coordinate subtraction
     * @param y the Y coordinate subtraction
     * @return the point with subtracted coordinates
     * @since JavaFX 8.0
     */
    public Point2D subtract(double x, double y) {
        return new Point2D(getX() - x, getY() - y);
    }

    /**
     * Returns a point with the coordinates of this point multiplied
     * by the specified factor
     *
     * @param factor the factor multiplying the coordinates
     * @return the point with multiplied coordinates
     * @since JavaFX 8.0
     */
    public Point2D multiply(double factor) {
        return new Point2D(getX() * factor, getY() * factor);
    }

    /**
     * Returns a point with the coordinates of the specified point subtracted
     * from the coordinates of this point.
     *
     * @param point the point whose coordinates are to be subtracted
     * @return the point with subtracted coordinates
     * @throws NullPointerException if the specified {@code point} is null
     * @since JavaFX 8.0
     */
    public Point2D subtract(Point2D point) {
        return subtract(point.getX(), point.getY());
    }

    /**
     * Computes dot (scalar) product of the vector represented by this instance
     * and the specified vector.
     *
     * @param x the X magnitude of the other vector
     * @param y the Y magnitude of the other vector
     * @return the dot product of the two vectors
     * @since JavaFX 8.0
     */
    public double dotProduct(double x, double y) {
        return getX() * x + getY() * y;
    }

    /**
     * Computes dot (scalar) product of the vector represented by this instance
     * and the specified vector.
     *
     * @param vector the other vector
     * @return the dot product of the two vectors
     * @throws NullPointerException if the specified {@code vector} is null
     * @since JavaFX 8.0
     */
    public double dotProduct(Point2D vector) {
        return dotProduct(vector.getX(), vector.getY());
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare
     * @return true if this point is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Point2D) {
            Point2D other = (Point2D) obj;
            return getX() == other.getX() && getY() == other.getY();
        } else {
            return false;
        }
    }

    /**
     * Returns a hash code value for the point.
     *
     * @return a hash code value for the point.
     */
    @Override
    public int hashCode() {
        if (hash == 0) {
            long bits = 7L;
            bits = 31L * bits + Double.doubleToLongBits(getX());
            bits = 31L * bits + Double.doubleToLongBits(getY());
            hash = (int) (bits ^ (bits >> 32));
        }
        return hash;
    }

    /**
     * Returns a string representation of this {@code Point2D}.
     * This method is intended to be used only for informational purposes.
     * The content and format of the returned string might vary between
     * implementations.
     * The returned string might be empty but cannot be {@code null}.
     */
    @Override
    public String toString() {
        return "Point2D [x = " + getX() + ", y = " + getY() + "]";
    }

}