package com.carpio.practicatest1.session2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class YearUtilitiesTest {
    @ParameterizedTest
    @CsvSource({
            "4, true",
            "6, false",
            "100, false",
            "400, true",
            "2015, false",
            "2016, true"
    })
    public void test1(Integer year, Boolean expected) {
        Boolean actual = YearUtilities.isLeap(year);
        assertEquals(expected, actual);
    }
}
