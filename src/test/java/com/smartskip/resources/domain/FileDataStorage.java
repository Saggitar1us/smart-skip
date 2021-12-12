package com.smartskip.resources.domain;

public class FileDataStorage implements DataStorage {

    private String fileName;

    public FileDataStorage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean containsLock() {
        return false;
    }

    @Override
    public void saveLock() {

    }

    @Override
    public void removeLock() {

    }
}
