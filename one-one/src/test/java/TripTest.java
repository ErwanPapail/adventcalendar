import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TripTest {

    @Test
    void shouldConstructWithModules() {
        final List<Float> modules = List.of();
        final var trip = new Trip(modules);

        assertEquals(trip.getModules(), modules);
    }

    @Test
    void shouldComputeFuelRequirement() {
        final List<Float> modules = List.of(100756.f, 1969.f);
        final var trip = new Trip(modules);

        assertEquals(34237, trip.getFuelRequirement());
    }

}