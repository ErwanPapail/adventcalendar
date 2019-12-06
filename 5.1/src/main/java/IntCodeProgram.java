import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class IntCodeProgram {

    private List<Integer> intCode;
    private final ProgramIO programIO;
    private int instructionIndex = 0;

    IntCodeProgram(final List<Integer> intCode, final ProgramIO programIO) {
        this.intCode = intCode;
        this.programIO = programIO;
    }

    List<Integer> executeProgram() {
        List<Integer> currentCode = new LinkedList<>(this.intCode);

        do {
            final var instruction = this.getNextInstruction(currentCode);
            if (isOperationAllowed(currentCode, instruction)) {
                this.executeOperation(currentCode, instruction);
                this.updateInstructionIndex(instruction);
            } else {
                return currentCode;
            }

        } while (instructionIndex < intCode.size() - 1);
        return currentCode;
    }

    List<Integer> executeProgram(final int noun, final int verb) {
        List<Integer> currentCode = new LinkedList<>(this.intCode);
        currentCode.set(1, noun);
        currentCode.set(2, verb);

        do {
            final var instruction = this.getNextInstruction(currentCode);
            if (isOperationAllowed(currentCode, instruction)) {
                this.executeOperation(currentCode, instruction);
                this.updateInstructionIndex(instruction);
            } else {
                return currentCode;
            }

        } while (instructionIndex < intCode.size() - 1);
        return currentCode;
    }

    private void executeOperation(List<Integer> currentCode, final Instruction instruction) {
        final var destructuredCode = this.integerToDigitsList(currentCode.get(instructionIndex));
        int firstValue, secondValue, index;

        switch (instruction.getOperationCode()) {
            case 1:
                if (destructuredCode.get(2).equals(1)) {
                    firstValue = instruction.getParameters().get(0);
                } else { firstValue = currentCode.get(currentCode.get(instructionIndex + 1)); }

                if (destructuredCode.get(1).equals(1)) {
                    secondValue = instruction.getParameters().get(1);
                } else { secondValue = currentCode.get(currentCode.get(instructionIndex + 2)); }


                    index = currentCode.get(instructionIndex + 3);

                currentCode.set(index, firstValue + secondValue);
                break;
            case 2:
                if (destructuredCode.get(2).equals(1)) {
                    firstValue = instruction.getParameters().get(0);
                } else { firstValue = currentCode.get(currentCode.get(instructionIndex + 1)); }

                if (destructuredCode.get(1).equals(1)) {
                    secondValue = instruction.getParameters().get(1);
                } else { secondValue = currentCode.get(currentCode.get(instructionIndex + 2)); }


                    index = currentCode.get(instructionIndex + 3);


                currentCode.set(index, firstValue * secondValue);
                break;
            case 3:
                final var input = programIO.inputValue();
                currentCode.set(currentCode.get(instructionIndex + 1), input);
                break;
            case 4:
                    programIO.outputValue(currentCode.get(instructionIndex + 1));
                break;
            default:
                break;
        }
    }

    public boolean isOperationAllowed(final List<Integer> currentCode, final Instruction instruction) {
        final var destructuredCode = this.integerToDigitsList(currentCode.get(instructionIndex));

        if (instruction.getOperationCode().equals(1) || instruction.getOperationCode().equals(2)) {
            return currentCode.size() >= instructionIndex + 3 &&
                    destructuredCode.get(0).equals(0) ? currentCode.size() > currentCode.get(instructionIndex + 3) : destructuredCode.get(0).equals(0) ? currentCode.size() > currentCode.get(instructionIndex + 2) : !destructuredCode.get(0).equals(0) || currentCode.size() > currentCode.get(instructionIndex + 1);
        }
        if (instruction.getOperationCode().equals(3)) {
            return currentCode.size() > instructionIndex + 1 &&
                    currentCode.size() > currentCode.get(instructionIndex + 1);
        }
        if (instruction.getOperationCode().equals(4)) {
            return currentCode.size() <= instructionIndex + 1 ||
                    !destructuredCode.get(2).equals(0) || currentCode.size() > currentCode.get(instructionIndex + 1);
        }
        return true;
    }

    List<Integer> getIntCode() {
        return this.intCode;
    }

    public Instruction getNextInstruction(final List<Integer> code) {
        final var destructuredCode = this.integerToDigitsList(code.get(instructionIndex));
        switch (destructuredCode.get(4)) {
            case 1:
                return new Instruction(1, List.of(code.get(instructionIndex + 1), code.get(instructionIndex + 2), code.get(instructionIndex + 3)));
            case 2:
                return new Instruction(2, List.of(code.get(instructionIndex + 1), code.get(instructionIndex + 2), code.get(instructionIndex + 3)));
            case 3:
                return new Instruction(3, List.of(code.get(instructionIndex + 1)));
            case 4:
                return new Instruction(4, List.of(code.get(instructionIndex + 1)));
            default:
                return new Instruction(99, List.of());
        }
    }

    public void updateInstructionIndex(final Instruction instruction) {
        switch (instruction.getOperationCode()) {
            case 1:
            case 2:
                instructionIndex += 4;
                break;
            case 3:
            case 4:
                instructionIndex += 2;
                break;
            default:
                instructionIndex = this.intCode.size();
                break;
        }
    }

    public List<Integer> integerToDigitsList(final Integer integer) {
        String integerAsString = Integer.toString(integer);
        List<Integer> integerAsList = Arrays.stream(integerAsString.split("")).map(Integer::parseInt).collect(Collectors.toList());

        while (integerAsList.size() < 5) {
            integerAsList.add(0, 0);
        }
        return integerAsList;
    }
}
