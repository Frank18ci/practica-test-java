package com.carpio.practicatest1.services.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigFileService {

    public void writeConfig(Path file, String content) throws IOException {
        Files.writeString(file, content);
    }

    public String readConfig(Path file) throws IOException {
        return Files.readString(file);
    }

    public boolean fileExists(Path file) {
        return Files.exists(file);
    }
}
