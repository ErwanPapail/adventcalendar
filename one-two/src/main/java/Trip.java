import java.util.List;

public class Trip {

    private final List<Float> modules;

    Trip(final List<Float> modules) {
        this.modules = modules;
    }

    List<Float> getModules() {
        return modules;
    }

    Float computeFuelRequirement() {
        return modules.stream()
                .map(this::computeSingleModuleFuelRequirement)
                .reduce(Float::sum).get();
    }

    Float computeSingleModuleFuelRequirement(final float moduleWeight) {
        return computeFuelForMass(moduleWeight);
    }

    private Float computeFuelForMass(final Float fuelToLoad) {
        final var newFuelAmount = (float)(Math.floor(fuelToLoad/3) - 2);
        if(newFuelAmount <= 0) {
            return 0.f;
        }

        return newFuelAmount + computeFuelForMass(newFuelAmount);
    }
}
