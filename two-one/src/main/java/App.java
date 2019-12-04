public class App {

    public static void main(String[] args){
        final var fileLoader = new FileLoader("input.txt");
        final var intCodeProgram = new IntCodeProgram(fileLoader.getFileContent());
        intCodeProgram.initialSetUp();

        final var programOutput = intCodeProgram.executeProgram();

        for(var value : programOutput) {
            System.out.print(value + ",");
        }
    }
}
