import java.util.Scanner;

public class ProgramIO {

    final Scanner scanner;

    public ProgramIO() {
        this.scanner = new Scanner(System.in);
    }

    public Integer inputValue() {
        String s = scanner.next();
        return scanner.nextInt();
    }

    public void outputValue(final Integer value) {
        System.out.println(value);
    }

}
