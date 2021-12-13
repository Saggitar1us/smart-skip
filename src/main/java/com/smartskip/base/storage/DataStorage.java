package com.smartskip.base.storage;

public interface DataStorage {

    boolean containsLock();

    void saveLock();

    void removeLock();
}