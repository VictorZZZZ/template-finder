package com.zvv.utils;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class FileUtil {
    private FileUtil() {

    };

    public static void checkIfFileExists(File file) {
        if (!file.isFile()) {
            throw new RuntimeException(file.getPath() + " is not exist!");
        }
    }

    public static File createFile(String path) {
        try {
            return Paths.get(path).toFile();
        } catch (InvalidPathException | NullPointerException ex) {
            throw new RuntimeException(path + " is not valid!");
        }
    }
}
