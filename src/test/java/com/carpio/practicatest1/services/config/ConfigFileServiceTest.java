package com.carpio.practicatest1.services.config;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConfigFileServiceTest {

    private Path testFolder;
    private Path testFile;
    private ConfigFileService configFileService;

    @BeforeAll
    void setUpAll() throws IOException {
        testFolder = Paths.get("tmp_app_tests");
        Files.createDirectories(testFolder);
        System.out.println("Test folder created at: " + testFolder.toAbsolutePath());
    }

    @AfterAll
    void tearDownAll() throws IOException {
        try (Stream<Path> paths = Files.walk(testFolder)) {
            paths.sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            System.out.println("Failed to delete " + path + ": " + e.getMessage());
                        }
                    });
        }
        System.out.println("Test folder deleted from: " + testFolder.toAbsolutePath());
    }

    @BeforeEach
    void setUp() throws IOException {
        configFileService = new ConfigFileService();
        testFile = testFolder.resolve("config_test.txt");
        Files.deleteIfExists(testFile);
        Files.createFile(testFile);
        System.out.println("Test file created at: " + testFile.toAbsolutePath());
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(testFile);
        System.out.println("Test file deleted from: " + testFile.toAbsolutePath());
    }

    @Test
    void testWriteConfig() throws IOException {
        configFileService.writeConfig(testFile, "modo=production");
        String content = Files.readString(testFile);
        assertEquals("modo=production", content);
    }

    @Test
    void testReadConfig() throws IOException {
        Files.writeString(testFile, "port=8080");
        String result = configFileService.readConfig(testFile);
        assertEquals("port=8080", result);
    }

    @Test
    void testFileExists() {
        assertTrue(configFileService.fileExists(testFile));
    }
}
