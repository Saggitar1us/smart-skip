package com.smartskip.resources.domain;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class SkipTestExtension implements BeforeEachCallback, AfterEachCallback {
    private DataStorage dataStorage;
    private int waitTimeInMinutes;

    public SkipTestExtension(DataStorage dataStorage, int waitTimeInMinutes) {
        this.dataStorage = dataStorage;
        this.waitTimeInMinutes = waitTimeInMinutes;
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        waitForLockNotExist();

        if (methodWithSkip(context)) {
            dataStorage.saveLock();
        }
        if (!methodWithSkip(context)) { //на случай если методы без skip идут/ждут в параллель с методом который с skip
            waitForLockNotExist();
        }

    }

    private void waitForLockNotExist() {
        while (dataStorage.containsLock()) { //не только условие, но и время waitTimeInMinutes
            //wait for second
        }
        //если после ожидания лок все еще есть - выкинуть AssertionFailedException("время ожидания вышло, тест нельзя запустить")
    }

    private boolean methodWithSkip(ExtensionContext context) {
        return context.getTestMethod().get().getAnnotation(SmartSkip.class) != null;
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        if (context.getTestMethod().get().getAnnotation(SmartSkip.class) != null) {
            dataStorage.removeLock();
        }
    }
}
