import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProgramRunnerTest {

    @Test
    void shouldDetermineProgramInputs() {
        final var initialIntCode = List.of(1,9,10,3,2,3,11,0,99,30,40,50);
        final var intCodeProgram = new IntCodeProgram(new LinkedList<>(initialIntCode), new ProgramIO());
        final var result = ProgramRunner.determinateProgramInput(intCodeProgram, 3500, 10, 11);

        assertEquals(9, result.get(0));
        assertEquals(10, result.get(1));
    }

    @Test
    void shouldReturnErrorCodeIfUnableToDetermineInputs() {
        final var initialIntCode = List.of(1,9,10,3,2,3,11,0,99,30,40,50);
        final var intCodeProgram = new IntCodeProgram(new LinkedList<>(initialIntCode), new ProgramIO());
        final var result = ProgramRunner.determinateProgramInput(intCodeProgram, 3500, 7, 7);

        assertEquals(-1, result.get(0));
        assertEquals(-1, result.get(1));
    }
}