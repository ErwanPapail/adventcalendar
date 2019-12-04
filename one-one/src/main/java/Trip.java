import java.util.List;

public class Trip {

    private final List<Float> modules;

    public Trip(final List<Float> modules) {
        this.modules = modules;
    }

    public List<Float> getModules() {
        return modules;
    }

    public Double getFuelRequirement() {
        return modules.stream()
                .map(weight -> Math.floor(weight/3) - 2)
                .reduce((a, b) -> a + b).get();
    }
}
