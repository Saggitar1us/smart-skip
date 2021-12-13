package com.smartskip.base.storage.impl;

import com.smartskip.base.storage.DataStorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDataStorage implements DataStorage {

    private String fileName;
    private static final String LOCK_WORD = "true";

    public FileDataStorage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean containsLock() {
        return fileContainsWord(LOCK_WORD);
    }

    @Override
    public void saveLock() {
        fileSaveLock();
    }

    @Override
    public void removeLock() {
        fileRemoveLock();
    }

    private void fileSaveLock() {
        writeToFile(LOCK_WORD);
    }

    private boolean fileContainsWord(String word) {
        return readFile().contains(word);
    }

    private void fileRemoveLock() {
        writeToFile("");
    }

    private String readFile() {
        try {
            return new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private void writeToFile(String line) {
        Path path = Paths.get(fileName);
        byte[] strToBytes = line.getBytes();
        try {
            Files.write(path, strToBytes);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
