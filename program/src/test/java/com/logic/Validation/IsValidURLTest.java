package com.logic.Validation;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for InputValidation.isValidURL method.
 * This method tests if a given URL string matches a specific format, requiring it to start with "http://" or "https://" and contain at least one letter in each section.
 */
public class IsValidURLTest {

    /**
     * Test to verify that a correctly formatted HTTP URL is recognized as valid.
     */
    @Test
    public void testIsValidURL_ValidHttpURL() {
        assertTrue("A valid HTTP URL should be valid", 
            InputValidation.isValidURL("http://example.com"));
    }

    /**
     * Test to verify that a correctly formatted HTTPS URL is recognized as valid.
     */
    @Test
    public void testIsValidURL_ValidHttpsURL() {
        assertTrue("A valid HTTPS URL should be valid", 
            InputValidation.isValidURL("https://example.com"));
    }

    /**
     * Test to verify that a URL without the HTTP or HTTPS prefix is recognized as invalid.
     */
    @Test
    public void testIsValidURL_MissingHttpHttps() {
        assertFalse("A URL missing HTTP or HTTPS should be invalid", 
            InputValidation.isValidURL("example.com"));
    }

    /**
     * Test to verify that a URL with missing sections (e.g., no domain or top-level domain) is recognized as invalid.
     */
    @Test
    public void testIsValidURL_MissingSections() {
        assertFalse("A URL with missing sections should be invalid", 
            InputValidation.isValidURL("https://example"));
    }

    /**
     * Test to verify that a URL with special characters is recognized as invalid.
     */
    @Test
    public void testIsValidURL_SpecialCharacters() {
        assertFalse("A URL with special characters should be invalid", 
            InputValidation.isValidURL("https://exa!mple.com"));
    }

    /**
     * Test to verify that a URL with extra sections is recognized as invalid.
     */
    @Test
    public void testIsValidURL_ExtraSections() {
        assertFalse("A URL with extra sections should be invalid", 
            InputValidation.isValidURL("https://sub.example.com"));
    }
}
