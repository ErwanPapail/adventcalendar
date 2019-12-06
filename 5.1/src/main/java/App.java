public class App {

    public static void main(String[] args){
        final var fileLoader = new FileLoader("input.txt");
        final var intCodeProgram = new IntCodeProgram(fileLoader.getFileContent(), new ProgramIO());

        final var result = ProgramRunner.determinateProgramInput(intCodeProgram, 19690720, 500, 500);

        System.out.println(result);
    }
}
