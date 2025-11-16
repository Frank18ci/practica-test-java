package com.carpio.practicatest1.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class TestUtils {

    public static <T> T getResource(String resourceName, Class<T> clazz, Class<?> contextClass) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        String resource = resourceName == null ? "" : resourceName.replaceFirst("^/+", "");
        String path = getString(contextClass, resource);
        try (final InputStream is = contextClass.getClassLoader().getResourceAsStream(path)) {
            if (is == null) {
                throw new IOException("No se encontró el recurso: " + path);
            }
            return objectMapper.readValue(is, clazz);
        }
    }

    private static String getString(Class<?> contextClass, String resource) {
        Package pkg = contextClass.getPackage();
        String path;
        if (pkg != null && !pkg.getName().isEmpty()) {
            String pkgPath = pkg.getName().replace('.', '/');
            if (!resource.isEmpty() && (resource.equals(pkgPath) || resource.startsWith(pkgPath + "/"))) {
                path = resource;
            } else {
                path = pkgPath + (resource.isEmpty() ? "" : "/" + resource);
            }
        } else {
            path = resource;
        }
        return path;
    }

}
