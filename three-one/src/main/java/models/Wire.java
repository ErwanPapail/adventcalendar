package models;

import java.util.LinkedList;
import java.util.List;

public class Wire {
    private final List<Point> points;

    private Wire(List<Point> points) {
        this.points = points;
    }

    public static class WireBuilder {

        final List<Movement> movements;
        List<Point> points = new LinkedList<>(List.of(new Point(0, 0)));

        public WireBuilder(List<Movement> movements) {
            this.movements = movements;
        }

        public Wire build() {
            for(var movement : this.movements){
                this.addMovementPoints(movement);
            }
            return new Wire(this.points);
        }

        private WireBuilder addMovementPoints(final Movement movement) {
            if(movement.getSens() == Movement.Sens.R) {
                for(int i = 0; i < movement.getNorm(); i++) {
                    this.addPoint(this.getLastPoint().getX() + 1, this.getLastPoint().getY());
                }
            }
            if(movement.getSens() == Movement.Sens.L) {
                for(int i = 0; i < movement.getNorm(); i++) {
                    this.addPoint(this.getLastPoint().getX() - 1, this.getLastPoint().getY());
                }
            }
            if(movement.getSens() == Movement.Sens.D) {
                for(int i = 0; i < movement.getNorm(); i++) {
                this.addPoint(this.getLastPoint().getX(), this.getLastPoint().getY() - 1);
            }
            }
            if(movement.getSens() == Movement.Sens.U) {
                for(int i = 0; i < movement.getNorm(); i++) {
                    this.addPoint(this.getLastPoint().getX(), this.getLastPoint().getY() + 1);
                }
            }
            return this;
        }

        private void addPoint(final Integer x, final Integer y) {
            this.points.add(new Point(x, y));
        }

        private Point getLastPoint() {
            return points.get(this.points.size() - 1);
        }

    }

    public List<Point> computeIntersections(final Wire wire) {
        List<Point> intersections = new LinkedList<>();

        for(var point1 : points){
            for(var point2 : wire.getPoints()) {
                if(point1.equals(point2) && (point1.getX() != 0 || point1.getY() != 0)) { intersections.add(point1); }
            }
        }
        return intersections;
    }

    public List<Point> getPoints() {
        return points;
    }
}
