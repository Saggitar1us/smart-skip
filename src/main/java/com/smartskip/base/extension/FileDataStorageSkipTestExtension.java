package com.smartskip.base.extension;

import com.smartskip.base.storage.impl.FileDataStorage;

public class FileDataStorageSkipTestExtension extends SkipTestExtension {
    public FileDataStorageSkipTestExtension() {
        super(new FileDataStorage("C:\\Users\\alexey.stepanov\\Documents\\java\\smart-skip\\skipTest.txt"), 5);
    }
}


