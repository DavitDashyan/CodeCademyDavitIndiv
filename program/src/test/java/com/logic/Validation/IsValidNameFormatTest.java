package com.logic.Validation;

import org.junit.Test;
import static org.junit.Assert.*;

public class IsValidNameFormatTest {

    /**
     * Test to verify that a name with the correct format is recognized as valid.
     */
    @Test
    public void testIsValidNameFormat_ValidFormat() {
        assertTrue("A name with the correct format should be valid",
                InputValidation.isValidNameFormat("John"));
    }

    /**
     * Test to verify that a name starting with a lowercase letter is recognized as
     * invalid.
     */
    @Test
    public void testIsValidNameFormat_InvalidLowercaseStart() {
        assertFalse("A name starting with a lowercase letter should be invalid",
                InputValidation.isValidNameFormat("john"));
    }

    /**
     * Test to verify that a name with uppercase letters in the middle is recognized
     * as invalid.
     */
    @Test
    public void testIsValidNameFormat_InvalidUppercaseInMiddle() {
        assertFalse("A name with uppercase letters in the middle should be invalid",
                InputValidation.isValidNameFormat("JoHn"));
    }

    /**
     * Test to verify that an all-uppercase name is recognized as invalid.
     */
    @Test
    public void testIsValidNameFormat_InvalidAllUppercase() {
        assertFalse("An all-uppercase name should be invalid",
                InputValidation.isValidNameFormat("JOHN"));
    }

    /**
     * Test to verify that a name with numbers is recognized as invalid.
     */
    @Test
    public void testIsValidNameFormat_InvalidWithNumbers() {
        assertFalse("A name with numbers should be invalid",
                InputValidation.isValidNameFormat("John123"));
    }

    /**
     * Test to verify that an empty name is recognized as invalid.
     */
    @Test
    public void testIsValidNameFormat_InvalidEmptyName() {
        assertFalse("An empty name should be invalid",
                InputValidation.isValidNameFormat(""));
    }
}
