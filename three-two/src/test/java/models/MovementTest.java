package models;

import models.Movement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovementTest {

    @Test
    void equalMovementShouldBeEqual() {
        final var movement0 = new Movement("R", 500);
        final var movement1 = new Movement("R", 500);

        assertTrue(movement0.equals(movement1));
    }

    @Test
    void nonEqualMovementShouldNotBeEqual() {
        final var movement0 = new Movement("R", 1000);
        final var movement1 = new Movement("R", 500);

        assertFalse(movement0.equals(movement1));
    }
}