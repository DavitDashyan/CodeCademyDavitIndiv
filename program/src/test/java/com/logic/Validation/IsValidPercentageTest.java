package com.logic.Validation;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for InputValidation.isValidPercentage method.
 * This method tests if a given string can be parsed as an integer percentage value within the range of 0 to 100, inclusive.
 */
public class IsValidPercentageTest {

    /**
     * Test to verify that a valid percentage string within the range is recognized as valid.
     */
    @Test
    public void testIsValidPercentage_ValidPercentage() {
        assertTrue("A valid percentage (50) should be valid", 
            InputValidation.isValidPercentage("50"));
    }

    /**
     * Test to verify that the minimum valid percentage (0) is recognized as valid.
     */
    @Test
    public void testIsValidPercentage_MinimumPercentage() {
        assertTrue("The minimum valid percentage (0) should be valid", 
            InputValidation.isValidPercentage("0"));
    }

    /**
     * Test to verify that the maximum valid percentage (100) is recognized as valid.
     */
    @Test
    public void testIsValidPercentage_MaximumPercentage() {
        assertTrue("The maximum valid percentage (100) should be valid", 
            InputValidation.isValidPercentage("100"));
    }

    /**
     * Test to verify that a percentage below the valid range is recognized as invalid.
     */
    @Test
    public void testIsValidPercentage_BelowMinimum() {
        assertFalse("A percentage below the valid range (-1) should be invalid", 
            InputValidation.isValidPercentage("-1"));
    }

    /**
     * Test to verify that a percentage above the valid range is recognized as invalid.
     */
    @Test
    public void testIsValidPercentage_AboveMaximum() {
        assertFalse("A percentage above the valid range (101) should be invalid", 
            InputValidation.isValidPercentage("101"));
    }

    /**
     * Test to verify that a non-numeric string is recognized as an invalid percentage.
     */
    @Test
    public void testIsValidPercentage_NonNumericInput() {
        assertFalse("A non-numeric string should be invalid", 
            InputValidation.isValidPercentage("abc"));
    }
}
