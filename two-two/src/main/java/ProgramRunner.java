import java.util.List;

public class ProgramRunner {

    static List<Integer> determinateProgramInput(IntCodeProgram intCodeProgram, Integer goal, Integer rangeNoun, Integer rangeVerb) {
        for (int noun = 0; noun <= rangeNoun; noun++) {
            for (int verb = 0; verb <= rangeVerb; verb++) {
                if (intCodeProgram.executeProgram(noun, verb).get(0).equals(goal)) {
                    return List.of(noun, verb);
                }
            }
        }
        return List.of(-1, -1);
    }
}
