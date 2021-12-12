package com.smartskip.resources.domain;

public class FileDataStorageSkipTestExtension extends SkipTestExtension {


    public FileDataStorageSkipTestExtension() {
        super(new FileDataStorage("/Users/AlekseyStepanov/Documents/skipTest.txt"), 5);
    }
}


