package com.logic.Validation;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for InputValidation.isValidEmail method.
 * This method tests if a given email string matches a specific format, with constraints on the characters before and after the '@' symbol and after the '.' symbol.
 */
public class IsValidEmailTest {

    /**
     * Test to verify that a correctly formatted email is recognized as valid.
     */
    @Test
    public void testIsValidEmail_ValidEmail() {
        assertTrue("A correctly formatted email should be valid", 
            InputValidation.isValidEmail("example@example.com"));
    }

    /**
     * Test to verify that an email without '@' symbol is recognized as invalid.
     */
    @Test
    public void testIsValidEmail_MissingAtSymbol() {
        assertFalse("An email without '@' should be invalid", 
            InputValidation.isValidEmail("example.com"));
    }

    /**
     * Test to verify that an email missing the domain part is recognized as invalid.
     */
    @Test
    public void testIsValidEmail_MissingDomain() {
        assertFalse("An email missing the domain part should be invalid", 
            InputValidation.isValidEmail("example@.com"));
    }

    /**
     * Test to verify that an email missing the top-level domain is recognized as invalid.
     */
    @Test
    public void testIsValidEmail_MissingTopLevelDomain() {
        assertFalse("An email missing the top-level domain should be invalid", 
            InputValidation.isValidEmail("example@example"));
    }

    /**
     * Test to verify that an email with special characters before '@' is recognized as invalid.
     */
    @Test
    public void testIsValidEmail_SpecialCharactersBeforeAt() {
        assertFalse("An email with special characters before '@' should be invalid", 
            InputValidation.isValidEmail("exa!mple@example.com"));
    }

    /**
     * Test to verify that an email with special characters after '@' is recognized as invalid.
     */
    @Test
    public void testIsValidEmail_SpecialCharactersAfterAt() {
        assertFalse("An email with special characters after '@' should be invalid", 
            InputValidation.isValidEmail("example@exa!mple.com"));
    }

    /**
     * Test to verify that an email with numbers in the address part is recognized as valid.
     */
    @Test
    public void testIsValidEmail_WithNumbers() {
        assertTrue("An email with numbers in the address part should be valid", 
            InputValidation.isValidEmail("example123@example.com"));
    }
}
