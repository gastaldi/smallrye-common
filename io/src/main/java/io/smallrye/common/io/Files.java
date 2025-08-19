package io.smallrye.common.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileTime;
import java.util.Objects;

/**
 * Utility class for {@link File} operations
 */
public final class Files {

    private Files() {
    }

    /**
     * Deletes a directory recursively.
     *
     * @param directory directory to delete
     * @throws IOException in case deletion is unsuccessful
     * @throws NullPointerException if the parameter is {@code null}
     * @throws IllegalArgumentException if {@code directory} is not a directory
     */
    public static void deleteDirectory(final File directory) throws IOException {
        Objects.requireNonNull(directory, "directory");
        if (!directory.exists()) {
            return;
        }
        if (!java.nio.file.Files.isSymbolicLink(directory.toPath())) {
            cleanDirectory(directory);
        }
        java.nio.file.Files.delete(directory.toPath());
    }

    /**
     * Cleans a directory without deleting it.
     *
     * @param directory directory to clean
     * @throws NullPointerException if the given {@link File} is {@code null}.
     * @throws IllegalArgumentException if the {@code directory} does not exist or is not a directory.
     * @throws IOException if an I/O error occurs.
     */
    public static void cleanDirectory(final File directory) throws IOException {
        Objects.requireNonNull(directory, "directory");
        if (!directory.exists()) {
            return;
        }
        final File[] files = directory.listFiles();
        if (files == null) {
            return;
        }
        for (final File file : files) {
            if (file.isDirectory()) {
                cleanDirectory(file);
            } else {
                java.nio.file.Files.delete(file.toPath());
            }
        }
    }

    /**
     * Deletes a file, never throwing an exception. If file is a directory, delete it and all subdirectories.
     * <p>
     * The difference between File.delete() and this method are:
     * </p>
     * <ul>
     * <li>A directory to be deleted does not have to be empty.</li>
     * <li>No exceptions are thrown when a file or directory cannot be deleted.</li>
     * </ul>
     *
     * @param file file or directory to delete, can be {@code null}
     */
    public static void deleteQuietly(final File file) {
        if (file == null) {
            return;
        }
        try {
            if (file.isDirectory()) {
                cleanDirectory(file);
            }
        } catch (final Exception ignored) {
            // ignore
        }
        try {
            java.nio.file.Files.delete(file.toPath());
        } catch (final Exception ignored) {
        }
    }

    /**
     * Copies a file to a directory preserving the file date.
     * <p>
     * This method copies the contents of the specified source file to a file of the same name in the specified
     * destination directory. The destination directory is created if it does not exist. If the destination file exists,
     * then this method will overwrite it.
     * </p>
     * <p>
     * <strong>Note:</strong> This method tries to preserve the file's last modified date/times using
     * {@link BasicFileAttributeView#setTimes(FileTime, FileTime, FileTime)}. However, it is not guaranteed that the
     * operation will succeed. If the modification operation fails it falls back to
     * {@link File#setLastModified(long)} and if that fails, the method throws IOException.
     * </p>
     *
     * @param srcFile an existing file to copy, must not be {@code null}.
     * @param destDir the directory to place the copy in, must not be {@code null}.
     * @throws NullPointerException if any of the given {@link File}s are {@code null}.
     * @throws IllegalArgumentException if source or destination is invalid.
     * @throws IOException if an error occurs or setting the last-modified time didn't succeed.
     */
    public static void copyFileToDirectory(final File srcFile, final File destDir) throws IOException {

    }

    /**
     * Moves a directory.
     * <p>
     * When the destination directory is on another file system, do a "copy and delete".
     * </p>
     *
     * @param srcDir the directory to be moved.
     * @param destDir the destination directory.
     * @throws NullPointerException if any of the given {@link File}s are {@code null}.
     * @throws IllegalArgumentException if {@code srcDir} exists but is not a directory
     * @throws FileNotFoundException if the source does not exist.
     * @throws IOException if an error occurs or setting the last-modified time didn't succeed.
     */
    public static void moveDirectory(final File srcDir, final File destDir) throws IOException {

    }

    /**
     * <p>
     * Copies a entire directory structure.
     * </p>
     * <p>
     * Note:
     * <ul>
     * <li>It will include empty directories.
     * <li>The <code>sourceDirectory</code> must exists.
     * </ul>
     *
     * @param sourceDirectory the source dir
     * @param destinationDirectory the target dir
     * @throws IOException if any
     */
    public static void copyDirectoryStructure(File sourceDirectory, File destinationDirectory) throws IOException {

    }

}
