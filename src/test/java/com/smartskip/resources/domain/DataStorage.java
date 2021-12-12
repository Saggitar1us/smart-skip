package com.smartskip.resources.domain;

public interface DataStorage {

    boolean containsLock();

    void saveLock();

    void removeLock();
}