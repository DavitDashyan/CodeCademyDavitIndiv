package com.logic.Validation;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class tests the InputValidation.isValidDutchPostalCode method.
 * It includes various scenarios to ensure the method accurately validates Dutch postal codes.
 */
public class IsValidDutchPostalCodeTest {

    /**
     * Test to verify that a valid Dutch postal code is correctly identified as valid.
     * A typical valid postal code format (four digits followed by a space and two letters) is used here.
     */
    @Test
    public void testIsValidDutchPostalCode_ValidFormat() {
        assertTrue("Valid Dutch postal code should return true", 
            InputValidation.isValidDutchPostalCode("1234 AB"));
    }

    /**
     * Test to check if the method correctly identifies an invalid postal code without a space.
     */
    @Test
    public void testIsValidDutchPostalCode_InvalidFormatNoSpace() {
        assertFalse("Postal code without space should return false", 
            InputValidation.isValidDutchPostalCode("1234AB"));
    }

    /**
     * Test to ensure that a postal code with numbers instead of letters after the space is considered invalid.
     */
    @Test
    public void testIsValidDutchPostalCode_InvalidFormatNoLetters() {
        assertFalse("Postal code without letters should return false", 
            InputValidation.isValidDutchPostalCode("1234 56"));
    }

    /**
     * Test to validate that a postal code starting with zero is correctly identified as invalid.
     */
    @Test
    public void testIsValidDutchPostalCode_InvalidFormatStartsWithZero() {
        assertFalse("Postal code starting with zero should return false", 
            InputValidation.isValidDutchPostalCode("0123 AB"));
    }

    /**
     * Test to check if a postal code with the letters at the beginning is correctly marked as invalid.
     */
    @Test
    public void testIsValidDutchPostalCode_InvalidFormatLettersFirst() {
        assertFalse("Postal code with letters first should return false", 
            InputValidation.isValidDutchPostalCode("AB 1234"));
    }

    /**
     * Test to verify that a postal code with extra characters beyond the standard format is invalid.
     */
    @Test
    public void testIsValidDutchPostalCode_InvalidFormatExtraCharacters() {
        assertFalse("Postal code with extra characters should return false", 
            InputValidation.isValidDutchPostalCode("1234 ABC"));
    }

    /**
     * Test to ensure that special characters in the postal code make it invalid.
     */
    @Test
    public void testIsValidDutchPostalCode_InvalidFormatSpecialCharacters() {
        assertFalse("Postal code with special characters should return false", 
            InputValidation.isValidDutchPostalCode("1234 A@"));
    }

    /**
     * Test to confirm that postal codes with lowercase letters are correctly identified as invalid.
     */
    @Test
    public void testIsValidDutchPostalCode_InvalidFormatLowercaseLetters() {
        assertFalse("Postal code with lowercase letters should return false", 
            InputValidation.isValidDutchPostalCode("1234 ab"));
    }

    /**
     * Test to verify that an incomplete postal code (missing parts of the required format) is invalid.
     */
    @Test
    public void testIsValidDutchPostalCode_InvalidFormatIncomplete() {
        assertFalse("Incomplete postal code should return false", 
            InputValidation.isValidDutchPostalCode("1234 A"));
    }

    /**
     * Test to check that an empty string is not considered a valid postal code.
     */
    @Test
    public void testIsValidDutchPostalCode_EmptyString() {
        assertFalse("Empty string should return false", 
            InputValidation.isValidDutchPostalCode(""));
    }

    /**
     * Test to ensure that the method correctly handles null inputs as invalid.
     */
    @Test
    public void testIsValidDutchPostalCode_NullInput() {
        assertFalse("Null input should return false", 
            InputValidation.isValidDutchPostalCode(null));
    }
}
