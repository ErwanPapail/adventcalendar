package models;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class WireTest {

    @Test
    void shouldBuildWire() {
        List<Movement> movements = List.of(new Movement("R", 5), new Movement("U", 5));
        List<Point> expectedPoints = List.of(
                new Point(0,0),
                new Point(1,0),
                new Point(2,0),
                new Point(3,0),
                new Point(4,0),
                new Point(5,0),
                new Point(5,1),
                new Point(5,2),
                new Point(5,3),
                new Point(5,4),
                new Point(5,5)
        );
        Wire wire = new Wire.WireBuilder(movements).build();

        wire.getPoints().stream().peek(el -> System.out.println(el.getString())).collect(Collectors.toList());

        assertEquals(expectedPoints, wire.getPoints());
    }

    @Test
    void shouldComputeIntersectionsPoints() {
        List<Movement> movements = List.of(new Movement("R", 5), new Movement("U", 5));
        List<Point> expectedPoints = List.of(
                new Point(1,0),
                new Point(2,0),
                new Point(3,0),
                new Point(4,0),
                new Point(5,0),
                new Point(5,1),
                new Point(5,2),
                new Point(5,3),
                new Point(5,4),
                new Point(5,5)
        );
        Wire wire1 = new Wire.WireBuilder(movements).build();
        Wire wire2 = new Wire.WireBuilder(movements).build();

        assertEquals(expectedPoints, wire1.computeIntersections(wire2));
    }

}