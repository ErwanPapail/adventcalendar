import java.util.Objects;

public class Movement {

    enum Sens {L, U, R, D}
    private Sens sens;
    private final int norm;

    Movement(String sens, int norm) {
        switch (sens){
            case "L":
                this.sens = Sens.L;
            case "U":
                this.sens = Sens.U;
            case "R":
                this.sens = Sens.R;
            case "D":
                this.sens = Sens.D;
        }
        this.norm = norm;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Movement)) return false;
        final Movement movement = (Movement) o;
        return norm == movement.norm &&
                sens == movement.sens;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sens, norm);
    }
}
