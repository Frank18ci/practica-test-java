package com.carpio.practicatest1.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class TestUtils {

    public static <T> T getResource(String resourceName, Class<T> clazz, Class<?> contextClass) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        try (final InputStream is = contextClass.getClassLoader().getResourceAsStream(resourceName)) {
            if (is == null) {
                throw new IOException("No se encontró el recurso: " + resourceName);
            }
            return objectMapper.readValue(is, clazz);
        }
    }

}
