import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
        final var intCode = List.of(1,0,0,0);
        final var intCodeProgram = new IntCodeProgram(new LinkedList<>(intCode), programIO);
        final var expectedInstruction1 = new Instruction(1, List.of(0,0,0));

        assertEquals(expectedInstruction1, intCodeProgram.getNextInstruction(intCode));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void shouldAllowOperation1And2(final int operationCode) {
        final var intCode = List.of(operationCode,0, 3,0);
        final var intCodeProgram = new IntCodeProgram(new LinkedList<>(intCode), programIO);
        final var instruction = new Instruction(operationCode, List.of(0,3,0));

        assertTrue(intCodeProgram.isOperationAllowed(intCode, instruction));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4})
    void shouldAllowOperation3And4(final int operationCode) {
        final var intCode = List.of(operationCode,0);
        final var intCodeProgram = new IntCodeProgram(new LinkedList<>(intCode), programIO);
        final var instruction = new Instruction(operationCode, List.of(0));

        assertTrue(intCodeProgram.isOperationAllowed(intCode, instruction));

    }

    @Test
    void shouldExecuteOperation1() {
        final var intCode = List.of(1,0,0,0,99);
        final var expectedCode = List.of(2,0,0,0,99);

        final var intCodeProgram = new IntCodeProgram(new LinkedList<>(intCode), programIO);

        assertEquals(expectedCode, intCodeProgram.executeProgram());
    }

    @Test
    void shouldExecuteComplexOperation1() {
        final var initialIntCode = List.of(1001,4,3,4,33);
        final var expectedIntCode = List.of(1001,4,3,4,36);
        final var intCodeProgram = new IntCodeProgram(new LinkedList<>(initialIntCode), programIO);

        assertEquals(expectedIntCode, intCodeProgram.executeProgram());
    }

    @Test
    void shouldExecuteComplexOperation1bis() {
        final var initialIntCode = List.of(1101,1,238,4,33);
        final var expectedIntCode = List.of(1101,1,238,4,239);
        final var intCodeProgram = new IntCodeProgram(new LinkedList<>(initialIntCode), programIO);

        assertEquals(expectedIntCode, intCodeProgram.executeProgram());
    }

    @Test
    void shouldExecuteOperation2() {
        final var intCode = List.of(2,3,0,3,99);
        final var expectedCode = List.of(2,3,0,6,99);

        final var intCodeProgram = new IntCodeProgram(new LinkedList<>(intCode), programIO);

        assertEquals(expectedCode, intCodeProgram.executeProgram());
    }

    @Test
    void shouldExecuteComplexOperation2() {
        final var initialIntCode = List.of(1002,4,3,4,33);
        final var expectedIntCode = List.of(1002,4,3,4,99);
        final var intCodeProgram = new IntCodeProgram(new LinkedList<>(initialIntCode), programIO);

        assertEquals(expectedIntCode, intCodeProgram.executeProgram());
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
        verify(programIO).outputValue(eq(4));
    }

    @Test
    void shouldOutputValue1000() {
        final var intCode = List.of(104,1000,99);
        final var expectedCode = List.of(104,1000,99);

        final var intCodeProgram = new IntCodeProgram(new LinkedList<>(intCode), programIO);

        assertEquals(expectedCode, intCodeProgram.executeProgram());
        verify(programIO).outputValue(eq(1000));
    }

    @Test
    void shouldExecuteProgram() {
        final var initialIntCode = List.of(1,1,1,4,99,5,6,0,99);
        final var expectedIntCode = List.of(30,1,1,4,2,5,6,0,99);
        final var intCodeProgram = new IntCodeProgram(new LinkedList<>(initialIntCode), programIO);

        assertEquals(expectedIntCode, intCodeProgram.executeProgram());
    }

    @Test
    void shouldExecuteProgramWithNegatives() {
        final var initialIntCode = List.of(1101,100,-1,4,0);
        final var expectedIntCode = List.of(1101,100,-1,4,99);
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

    @Test
    void shouldDecodeInstructionCode() {
        final Integer code = 102;
        final List<Integer> list = List.of(0, 0, 1, 0, 2);

        final var intCodeProgram = new IntCodeProgram(List.of(1,1,1,10), programIO);
        assertEquals(list, intCodeProgram.integerToDigitsList(code));
    }
}