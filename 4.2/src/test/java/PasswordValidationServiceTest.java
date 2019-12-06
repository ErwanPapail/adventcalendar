import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidationServiceTest {

    @Test
    void shouldReturnIntegerAsListOfDigits() {
        final var passwordValidationService = new PasswordValidationService();

        final var integer = 154685;
        final List<Integer> expectedList = List.of(1, 5, 4, 6, 8, 5);

        assertEquals(expectedList, passwordValidationService.integerToDigitsList(integer));
    }

    @ParameterizedTest
    @ValueSource(ints = {122345777, 1544887, 665455454})
    void shouldValidateIfContainsTwoAdjacentSimilarDigit(final Integer password) {
        final var passwordValidationService = new PasswordValidationService();

        assertTrue(passwordValidationService.containsTwoSimilarAdjacentDigits(password));
    }

    @ParameterizedTest
    @ValueSource(ints = {1112345, 15487, 65554445})
    void shouldInvalidateIfDoesNotContainTwoAdjacentSimilarDigit(final Integer password) {
        final var passwordValidationService = new PasswordValidationService();

        assertFalse(passwordValidationService.containsTwoSimilarAdjacentDigits(password));
    }

    @ParameterizedTest
    @ValueSource(ints = {123456, 458})
    void shouldValidateIfDigitsAreInGrowingOrder(final Integer password) {
        final var passwordValidationService = new PasswordValidationService();

        assertTrue(passwordValidationService.isInGrowingOrder(password));
    }

    @ParameterizedTest
    @ValueSource(ints = {1234560, 45698})
    void shouldInvalidateIfDigitsAreNotInGrowingOrder(final Integer password) {
        final var passwordValidationService = new PasswordValidationService();

        assertFalse(passwordValidationService.isInGrowingOrder(password));
    }

    @ParameterizedTest
    @ValueSource(ints = {111122, 22345, 1233789, 112233})
    void shouldCombineRulesProperlyAndValidatePassword(final Integer password) {
        final var passwordValidationService = new PasswordValidationService();

        assertTrue(passwordValidationService.isValid(password));
    }

    @ParameterizedTest
    @ValueSource(ints = {1211111, 223450, 123789, 1111111, 123444})
    void shouldCombineRulesProperlyAndInvalidatePassword(final Integer password) {
        final var passwordValidationService = new PasswordValidationService();

        assertFalse(passwordValidationService.isValid(password));
    }

}