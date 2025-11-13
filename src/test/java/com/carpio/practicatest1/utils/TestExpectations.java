package com.carpio.practicatest1.utils;

import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class TestExpectations {
    public static void expectAllListOfClassAt(ResultActions actions, List<?> properties) throws Exception {
        for (int i = 0; i < properties.size(); i++) {
            actions.andExpect(jsonPath("$[" + i + "]").value(properties.get(i)));
        }
    }
}
