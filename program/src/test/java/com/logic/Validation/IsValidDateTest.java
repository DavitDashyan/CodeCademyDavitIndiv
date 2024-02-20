package com.logic.Validation;

import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

/**
 * Test class for InputValidation.isValidDate method.
 * This method tests if a given date is correct and not in the future.
 */
public class IsValidDateTest {

    /**
     * Test to verify that a valid past date is recognized as valid.
     */
    @Test
    public void testIsValidDate_ValidPastDate() {
        int year = 2000;
        int month = 1;
        int day = 1;
        assertTrue("A valid past date should be valid", 
            InputValidation.isValidDate(day, month, year));
    }

    /**
     * Test to verify that the current date is recognized as valid.
     */
    @Test
    public void testIsValidDate_CurrentDate() {
        LocalDate today = LocalDate.now();
        assertTrue("The current date should be valid", 
            InputValidation.isValidDate(today.getDayOfMonth(), today.getMonthValue(), today.getYear()));
    }

    /**
     * Test to verify that a future date is recognized as invalid.
     */
    @Test
    public void testIsValidDate_FutureDate() {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        assertFalse("A future date should be invalid", 
            InputValidation.isValidDate(futureDate.getDayOfMonth(), futureDate.getMonthValue(), futureDate.getYear()));
    }

    /**
     * Test to verify that an incorrect date (e.g., February 30th) is recognized as invalid.
     */
    @Test
    public void testIsValidDate_IncorrectDate() {
        assertFalse("An incorrect date (e.g., February 30th) should be invalid", 
            InputValidation.isValidDate(30, 2, 2020));
    }
}
