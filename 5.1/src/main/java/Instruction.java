import java.util.List;
import java.util.Objects;

public class Instruction {

    final Integer operationCode;
    final List<Integer> parameters;

    public Instruction(final Integer operationCode, final List<Integer> parameters) {
        this.operationCode = operationCode;
        this.parameters = parameters;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Instruction)) return false;
        final Instruction that = (Instruction) o;
        return Objects.equals(operationCode, that.operationCode) &&
                this.areParametersEqual(that.parameters);
    }

    private boolean areParametersEqual(final List<Integer> parameters) {
        if(this.parameters.size() != parameters.size()) return false;

        for(int i = 0; i < this.parameters.size(); i++) {
            if(this.parameters.get(i) != parameters.get(i)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationCode, parameters);
    }

    public Integer getOperationCode() {
        return operationCode;
    }

    public List<Integer> getParameters() {
        return parameters;
    }
}
