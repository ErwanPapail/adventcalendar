package models;

import java.util.Objects;

public class Movement {

    enum Sens {L, U, R, D}

    private Sens sens;
    private final int norm;

    public Movement(String sens, int norm) {
        if(sens.equals("L")) { this.sens = Sens.L; }
        if(sens.equals("U")) { this.sens = Sens.U; }
        if(sens.equals("R")) { this.sens = Sens.R; }
        if(sens.equals("D")) { this.sens = Sens.D; }

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

    public Sens getSens() {
        return sens;
    }

    public int getNorm() {
        return norm;
    }
}
