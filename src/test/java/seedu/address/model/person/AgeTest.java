package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AgeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Age(null));
    }

    @Test
    public void constructor_invalidAge_throwsIllegalArgumentException() {
        String invalidAge = "";
        assertThrows(IllegalArgumentException.class, () -> new Age(invalidAge));
    }

    @Test
    public void isValidAge() {

        // invalid age
        assertFalse(Age.isValidAge("")); // empty string
        assertFalse(Age.isValidAge(" ")); // spaces only
        assertFalse(Age.isValidAge("^")); // only non-alphanumeric characters
        assertFalse(Age.isValidAge("peter*")); // contains non-alphanumeric characters
        assertFalse(Age.isValidAge("Peter")); //alphabets
        assertFalse(Age.isValidAge("-74")); //negative numbers
        assertFalse(Age.isValidAge("-150")); //negative 3 digits
        assertFalse(Age.isValidAge(null)); //null age
        assertFalse(Age.isValidAge("200")); //over the range of 0-150
        assertFalse(Age.isValidAge("0.8")); //decimals
        assertFalse(Age.isValidAge("0")); //0 case
        assertFalse(Age.isValidAge("150")); //max valid age

        // valid age
        assertTrue(Age.isValidAge("1")); //min valid age
        assertTrue(Age.isValidAge("6")); //single digit number
        assertTrue(Age.isValidAge("27")); //double digit number
        assertTrue(Age.isValidAge("10")); //starts with 1
        assertTrue(Age.isValidAge("30")); //starts with 3
        assertTrue(Age.isValidAge("67")); //starts with 6
        assertTrue(Age.isValidAge("99")); //starts with 9
        assertTrue(Age.isValidAge("149")); //max valid age
    }
}
