import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IntCodeProgramTest {

    @Mock
    private ProgramIO programIO;


    @Test
    void shouldCreateObject() {
        final var intCode = List.of(0);

        final var intCodeProgram = new IntCodeProgram(new LinkedList<>(intCode), programIO);

        assertEquals(intCode, intCodeProgram.getIntCode());
    }

    @Test
    void shouldDetermineAndBuildInstruction() {
        fail();
    }

    @Test
    void shouldExecuteOperation1() {
        final var intCode = List.of(1,0,0,0,99);
        final var expectedCode = List.of(2,0,0,0,99);

        final var intCodeProgram = new IntCodeProgram(new LinkedList<>(intCode), programIO);

        assertEquals(expectedCode, intCodeProgram.executeProgram());
    }

    @Test
    void shouldExecuteOperation2() {
        final var intCode = List.of(2,3,0,3,99);
        final var expectedCode = List.of(2,3,0,6,99);

        final var intCodeProgram = new IntCodeProgram(new LinkedList<>(intCode), programIO);

        assertEquals(expectedCode, intCodeProgram.executeProgram());
    }

    @Test
    void shouldExecuteOperation3() {
        final var fakeInput = 5;
        final var intCode = List.of(3,0,99);
        final var expectedCode = List.of(fakeInput,0,99);

        final var intCodeProgram = new IntCodeProgram(new LinkedList<>(intCode), programIO);

        when(programIO.inputValue()).thenReturn(fakeInput);

        assertEquals(expectedCode, intCodeProgram.executeProgram());
        verify(programIO).inputValue();
    }

    @Test
    void shouldExecuteOperation4() {
        final var intCode = List.of(4,0,99);
        final var expectedCode = List.of(4,0,99);

        final var intCodeProgram = new IntCodeProgram(new LinkedList<>(intCode), programIO);

        assertEquals(expectedCode, intCodeProgram.executeProgram());
        verify(programIO).outputValue(4);
    }

    @Test
    void shouldExecuteProgram() {
        final var initialIntCode = List.of(1,1,1,4,99,5,6,0,99);
        final var expectedIntCode = List.of(30,1,1,4,2,5,6,0,99);
        final var intCodeProgram = new IntCodeProgram(new LinkedList<>(initialIntCode), programIO);

        assertEquals(expectedIntCode, intCodeProgram.executeProgram());
    }

    @Test
    void shouldExecuteProgramWithParameters() {
        final var initialIntCode = List.of(1,1,1,4,99,5,6,0,99);
        final var expectedIntCode = List.of(30,1,1,4,2,5,6,0,99);
        final var intCodeProgram = new IntCodeProgram(new LinkedList<>(initialIntCode), programIO);

        assertEquals(expectedIntCode, intCodeProgram.executeProgram(1, 1));
    }

    @Test
    void programExecutionShouldHandleIncorrectInput() {
        final var initialIntCode = List.of(1,1,1,10);
        final var intCodeProgram = new IntCodeProgram(new LinkedList<>(initialIntCode), programIO);

        assertEquals(-1, intCodeProgram.executeProgram().get(0));
    }
}