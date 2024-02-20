package com.logic.Validation;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for InputValidation.isValidGrade method.
 * This method tests if a given grade is within the valid range of 1 to 10, inclusive.
 */
public class IsValidGradeTest {

    /**
     * Test to verify that the minimum valid grade (1) is recognized as valid.
     */
    @Test
    public void testIsValidGrade_ValidMinimum() {
        assertTrue("The minimum valid grade (1) should be valid", 
            InputValidation.isValidGrade(1));
    }

    /**
     * Test to verify that the maximum valid grade (10) is recognized as valid.
     */
    @Test
    public void testIsValidGrade_ValidMaximum() {
        assertTrue("The maximum valid grade (10) should be valid", 
            InputValidation.isValidGrade(10));
    }

    /**
     * Test to verify that a grade just below the valid range (0) is recognized as invalid.
     */
    @Test
    public void testIsValidGrade_InvalidBelowMinimum() {
        assertFalse("A grade below the valid range (0) should be invalid", 
            InputValidation.isValidGrade(0));
    }

    /**
     * Test to verify that a grade just above the valid range (11) is recognized as invalid.
     */
    @Test
    public void testIsValidGrade_InvalidAboveMaximum() {
        assertFalse("A grade above the valid range (11) should be invalid", 
            InputValidation.isValidGrade(11));
    }

    /**
     * Test to verify that a grade well within the valid range (e.g., 5) is recognized as valid.
     */
    @Test
    public void testIsValidGrade_ValidMidRange() {
        assertTrue("A mid-range grade (e.g., 5) should be valid", 
            InputValidation.isValidGrade(5));
    }
}
