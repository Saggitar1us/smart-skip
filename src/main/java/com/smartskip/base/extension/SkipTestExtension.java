package com.smartskip.base.extension;

import com.smartskip.base.annotations.SmartSkip;
import com.smartskip.base.storage.DataStorage;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class SkipTestExtension implements BeforeEachCallback, AfterEachCallback {
    private DataStorage dataStorage;
    private int waitTimeInMinutes;

    public SkipTestExtension(DataStorage dataStorage, int waitTimeInMinutes) {
        this.dataStorage = dataStorage;
        this.waitTimeInMinutes = waitTimeInMinutes;
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        waitForLockNotExist();
        if (methodWithSkip(context)) {
            System.out.println("Save lock " + context.getTestMethod().get().getName());
            dataStorage.saveLock();
        }
        if (!methodWithSkip(context)) {
            System.out.println("Check for lock not exist after skip " + context.getTestMethod().get().getName());
            waitForLockNotExist();
        }

    }

    private void waitForLockNotExist() {
        int count = 0;
        while (dataStorage.containsLock() && count <= waitTimeInMinutes * 60) {
            System.out.println("Wait for lock not exist");
            try {
                Thread.sleep(1000);
                count++;
            } catch (InterruptedException ex) {
            }
        }
        assertFalse(dataStorage.containsLock(), "The storage contains lock!");
    }

    private boolean methodWithSkip(ExtensionContext context) {
        return context.getTestMethod().get().getAnnotation(SmartSkip.class) != null;
    }

    @Override
    public void afterEach(ExtensionContext context) {
        if (methodWithSkip(context)) {
            System.out.println("Remove lock " + context.getTestMethod().get().getName());
            dataStorage.removeLock();
        }
    }
}
