import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordValidationService {


    public boolean containsTwoSimilarAdjacentDigits(final Integer password) {
        final List<Integer> integerAsDigitsList = this.integerToDigitsList(password);
        final var passwordSize = integerAsDigitsList.size();

        if(passwordSize <= 1 ) return false;

        if(passwordSize == 2 && integerAsDigitsList.get(0).equals(integerAsDigitsList.get(1))) {
            return true;
        }

        if (passwordSize >= 3) {

            // Check case of 2 first digits
            if (integerAsDigitsList.get(0).equals(integerAsDigitsList.get(1)) &&
                    !integerAsDigitsList.get(0).equals(integerAsDigitsList.get(2))) {
                return true;
            }

            // Check case of 2 last digits
            if (integerAsDigitsList.get(passwordSize - 1).equals(integerAsDigitsList.get(passwordSize - 2)) &&
                    !integerAsDigitsList.get(passwordSize - 1).equals(integerAsDigitsList.get(passwordSize - 3))) {
                return true;
            }

            for (int i = 1; i < passwordSize - 2; i++) {
                if (integerAsDigitsList.get(i).equals(integerAsDigitsList.get(i + 1))) {
                    if (!integerAsDigitsList.get(i).equals(integerAsDigitsList.get(i - 1)) &&
                            !integerAsDigitsList.get(i).equals(integerAsDigitsList.get(i + 2))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public List<Integer> integerToDigitsList(final Integer integer) {
        String integerAsString = Integer.toString(integer);
        return Arrays.stream(integerAsString.split("")).map(Integer::parseInt).collect(Collectors.toList());
    }

    public boolean isInGrowingOrder(final Integer password) {
        final List<Integer> integerAsDigitsList = this.integerToDigitsList(password);

        for (int i = 0; i < integerAsDigitsList.size() - 1; i++) {
            if (integerAsDigitsList.get(i) > integerAsDigitsList.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isValid(final Integer password) {
        return containsTwoSimilarAdjacentDigits(password) && isInGrowingOrder(password);
    }
}
