import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordValidationService {


    public boolean doesContainTwoSimilarAdjacentDigits(final Integer password) {
        final List<Integer> integerAsDigitsList = this.integerToDigitsList(password);
        boolean doesContainADouble = false;

        for(int i = 0; i < integerAsDigitsList.size() - 1; i++) {
            if(integerAsDigitsList.get(i) == integerAsDigitsList.get(i+1)) {
                doesContainADouble = true;
            }
        }
        return doesContainADouble;
    }

    public List<Integer> integerToDigitsList(final Integer integer) {
        String integerAsString = Integer.toString(integer);
        return Arrays.stream(integerAsString.split("")).map(Integer::parseInt).collect(Collectors.toList());
    }

    public boolean isInGrowingOrder(final Integer password) {
        final List<Integer> integerAsDigitsList = this.integerToDigitsList(password);

        for(int i = 0; i < integerAsDigitsList.size() - 1; i++) {
            if(integerAsDigitsList.get(i) > integerAsDigitsList.get(i+1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isValid(final Integer password) {
        return doesContainTwoSimilarAdjacentDigits(password) && isInGrowingOrder(password);
    }
}
