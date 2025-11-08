package com.carpio.practicatest1.session2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordValidatorTest {
    @ParameterizedTest
    @CsvSource({
            "Abc1, false",
            "abcdefg, false",
            "ABCDEFG1, true",
            "abcdEFG, false",
            "abcd123, false",
            "ABCD123, true"
    })
    public void testPasswordValidationSucessfullyTest(String password, boolean expected) {
        boolean actual = PasswordValidator.isValid(password);
        assertEquals(expected, actual);
    }
}
