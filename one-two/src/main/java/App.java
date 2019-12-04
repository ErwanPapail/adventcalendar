public class App {

    public static void main(String[] args) {
        final var fileLoader = new FileLoader("input.txt");
        final var trip = new Trip(fileLoader.getFileContent());

        System.out.println(trip.computeFuelRequirement());
    }
}
