import models.Wire;

public class App {

    public static void main(String args[]) {
        final var fileLoader = new FileLoader("input.txt");
        final var movementsInputs = fileLoader.getFileContent();

        final var wire1 = new Wire.WireBuilder(movementsInputs.get(0)).build();
        final var wire2 = new Wire.WireBuilder(movementsInputs.get(1)).build();

        final var intersections = wire1.computeIntersections(wire2);

        if(intersections.size() > 0 ) {
            var closestPoint = intersections.get(0);
            System.out.println("Looking for closest intersection point ...");
            for(var point : intersections) {
                if(point.getStep() < closestPoint.getStep()) {
                    closestPoint = point;
                }
            }
            System.out.println(closestPoint.getStep());
        } else { System.out.println("No intersection point found"); }
    }
}
