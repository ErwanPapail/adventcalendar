import java.util.LinkedList;
import java.util.List;

public class IntCodeProgram {

    private List<Integer> intCode;

    public IntCodeProgram(final List<Integer> intCode) {
        this.intCode = intCode;
    }

    public List<Integer> getIntCode() {
        return this.intCode;
    }

    public List<Integer> executeProgram() {
        List<Integer> currentCode = new LinkedList<>(this.intCode);

        for (var index = 0; index < this.intCode.size(); index += 4) {
            if (currentCode.get(index) == 1 || currentCode.get(index) == 2 ) {
                executeOperation(currentCode, index);
            } else { return currentCode; }
        }
        return currentCode;
    }

    private List<Integer> executeOperation(List<Integer> currentCode, final int operationCodeIndex) {
        switch (currentCode.get(operationCodeIndex)) {
            case 1:
                currentCode.set(currentCode.get(operationCodeIndex + 3),
                        currentCode.get(currentCode.get(operationCodeIndex + 1)) + currentCode.get(currentCode.get(operationCodeIndex + 2)));
                return currentCode;
            case 2:
                currentCode.set(currentCode.get(operationCodeIndex + 3),
                        currentCode.get(currentCode.get(operationCodeIndex + 1)) * currentCode.get(currentCode.get(operationCodeIndex + 2)));
                return currentCode;
            default:
                return currentCode;
        }
    }

    public void initialSetUp() {
        this.intCode.set(1, 12);
        this.intCode.set(2, 2);
    }
}
