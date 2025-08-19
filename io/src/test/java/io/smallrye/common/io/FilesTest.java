package io.smallrye.common.io;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FilesTest {

    @Test
    void shouldDeleteDirectory(@TempDir File tempDir) throws IOException {
        Files.deleteDirectory(tempDir);
        assertThat(tempDir).doesNotExist();
    }

    @Test
    void shouldCleanDirectory(@TempDir Path tempDir) throws IOException {
        java.nio.file.Files.writeString(tempDir.resolve("foo.txt"), "Hello World");
        Files.cleanDirectory(tempDir.toFile());
        assertThat(tempDir).exists();
    }

    @Test
    void shouldDeleteQuietly(@TempDir File tempDir) {
        Files.deleteQuietly(tempDir);
        assertThat(tempDir).doesNotExist();
    }

    @Test
    void shouldCopyFileToDirectory() {
    }

    @Test
    void shouldMoveDirectory() {
    }

    @Test
    void shouldCopyDirectoryStructure() {
    }
}
