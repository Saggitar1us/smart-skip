package com.smartskip.tests;

import com.smartskip.base.annotations.SmartSkip;
import com.smartskip.base.extension.FileDataStorageSkipTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@ExtendWith(FileDataStorageSkipTestExtension.class)
public class DemoTest {

    @Test
    @SmartSkip
    void firstTest() {
    }

    @Test
    @SmartSkip
    void secondTest() {
    }

    @Test
    void thirdTest() {
    }

    @SmartSkip
    @ParameterizedTest()
    @MethodSource("testDataProvider")
    public void parameterizedTest(int someCount) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Stream<Arguments> testDataProvider() {
        List<Arguments> arguments = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arguments.add(Arguments.of(i));
        }
        return arguments.stream();
    }
}
