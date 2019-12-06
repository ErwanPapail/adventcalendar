public class App {

    public static void main(String[] args) {
        final var passwordValidationService = new PasswordValidationService();
        final Integer rangeInf = 193651;
        final Integer rangeSup = 649729;
        int validPasswordCount = 0;

        for(int password = rangeInf; password <= rangeSup; password++) {
            if (passwordValidationService.isValid(password)) {
                validPasswordCount++;
            }
        }

        System.out.println(validPasswordCount);

    }

}
