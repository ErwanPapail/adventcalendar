import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class IntCodeProgramTest {

    @Test
    void shouldCreateObject() {
        final var intCode = List.of(0);

        final var intCodeProgram = new IntCodeProgram(intCode);

        assertEquals(intCode, intCodeProgram.getIntCode());
    }

    @Test
    void shouldExecuteIntProgram() {
        final var initialIntCode = List.of(1,1,1,4,99,5,6,0,99);
        final var expectedIntCode = List.of(30,1,1,4,2,5,6,0,99);
        final var intCodeProgram = new IntCodeProgram(initialIntCode);

        assertEquals(expectedIntCode, intCodeProgram.executeProgram());
    }
}