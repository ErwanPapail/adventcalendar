package models;

import java.util.Objects;

public class Point {

    private final Integer x;
    private final Integer y;
    private final Integer step;
    private final Integer manhattanDistance;

    public Point(Integer x, Integer y, Integer step) {
        this.x = x;
        this.y = y;
        this.step = step;
        this.manhattanDistance = Math.abs(x) + Math.abs(y);
    }

    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
        this.step = 0;
        this.manhattanDistance = Math.abs(x) + Math.abs(y);
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(x, point.x) &&
                Objects.equals(y, point.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public String getString() {
        return "X : " + this.x + "  Y : " + this.y;
    }

    public Integer getManhattanDistance() {
        return this.manhattanDistance;
    }

    public Integer getStep() { return this.step; }
}
