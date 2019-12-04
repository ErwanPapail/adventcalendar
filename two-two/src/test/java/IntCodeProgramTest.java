import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntCodeProgramTest {

    @Test
    void shouldCreateObject() {
        final var intCode = List.of(0);

        final var intCodeProgram = new IntCodeProgram(new LinkedList<>(intCode));

        assertEquals(intCode, intCodeProgram.getIntCode());
    }

    @Test
    void shouldExecuteProgram() {
        final var initialIntCode = List.of(1,1,1,4,99,5,6,0,99);
        final var expectedIntCode = List.of(30,1,1,4,2,5,6,0,99);
        final var intCodeProgram = new IntCodeProgram(new LinkedList<>(initialIntCode));

        assertEquals(expectedIntCode, intCodeProgram.executeProgram());
    }

    @Test
    void shouldExecuteProgramWithParameters() {
        final var initialIntCode = List.of(1,1,1,4,99,5,6,0,99);
        final var expectedIntCode = List.of(30,1,1,4,2,5,6,0,99);
        final var intCodeProgram = new IntCodeProgram(new LinkedList<>(initialIntCode));

        assertEquals(expectedIntCode, intCodeProgram.executeProgram(1, 1));
    }

    @Test
    void programExecutionShouldHandleIncorrectInput() {
        final var initialIntCode = List.of(1,1,1,10);
        final var intCodeProgram = new IntCodeProgram(new LinkedList<>(initialIntCode));

        assertEquals(-1, intCodeProgram.executeProgram().get(0));
    }
}