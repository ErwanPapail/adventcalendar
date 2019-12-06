import java.util.LinkedList;
import java.util.List;

public class IntCodeProgram {

    private List<Integer> intCode;
    private final ProgramIO programIO;

    IntCodeProgram(final List<Integer> intCode, final ProgramIO programIO) {
        this.intCode = intCode;
        this.programIO = programIO;
    }

    List<Integer> executeProgram() {
        List<Integer> currentCode = new LinkedList<>(this.intCode);

        for (var index = 0; index < this.intCode.size(); index += 4) {
            if (currentCode.get(index) == 1 || currentCode.get(index) == 2) {
                if (isOperationAllowed(currentCode, index)) {
                    executeOperation(currentCode, index);
                } else { return List.of(-1); }
            } else {
                return currentCode;
            }
        }
        return currentCode;
    }

    List<Integer> executeProgram(final int noun, final int verb) {
        List<Integer> currentCode = new LinkedList<>(this.intCode);
        currentCode.set(1, noun);
        currentCode.set(2, verb);

        for (var index = 0; index < this.intCode.size(); index += 4) {
            if (currentCode.get(index) == 1 || currentCode.get(index) == 2) {
                if (isOperationAllowed(currentCode, index)) {
                    executeOperation(currentCode, index);
                } else { return List.of(-1); }
            } else {
                return currentCode;
            }
        }
        return currentCode;
    }

    public void executeOperation(List<Integer> currentCode, final int operationCodeIndex) {
        switch (currentCode.get(operationCodeIndex)) {
            case 1:
                currentCode.set(currentCode.get(operationCodeIndex + 3),
                        currentCode.get(currentCode.get(operationCodeIndex + 1)) + currentCode.get(currentCode.get(operationCodeIndex + 2)));
                return;
            case 2:
                currentCode.set(currentCode.get(operationCodeIndex + 3),
                        currentCode.get(currentCode.get(operationCodeIndex + 1)) * currentCode.get(currentCode.get(operationCodeIndex + 2)));
                return;
            case 3:
                final var input = programIO.inputValue();
                currentCode.set(currentCode.get(operationCodeIndex + 1), input);
            case 4:
                programIO.outputValue(currentCode.get(operationCodeIndex + 1));
            default:
        }
    }

    private boolean isOperationAllowed(final List<Integer> currentCode, final int operationCodeIndex) {
        if (currentCode.get(operationCodeIndex) == 1 || currentCode.get(operationCodeIndex) == 2) {
            return currentCode.size() > operationCodeIndex + 3 &&
                    currentCode.size() > currentCode.get(operationCodeIndex + 3) &&
                    currentCode.size() > currentCode.get(operationCodeIndex + 2) &&
                    currentCode.size() > currentCode.get(operationCodeIndex + 1);
        }
        return true;
    }

    List<Integer> getIntCode() {
        return this.intCode;
    }
}
