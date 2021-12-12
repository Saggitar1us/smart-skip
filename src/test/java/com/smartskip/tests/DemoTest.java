package com.smartskip.tests;

import com.smartskip.resources.domain.FileDataStorageSkipTestExtension;
import com.smartskip.resources.domain.SmartSkip;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(FileDataStorageSkipTestExtension.class)
public class DemoTest {

    @Test
    @SmartSkip
    void testMe() {

    }

    @Test
    @SmartSkip
    void testMe6() {

    }

    @Test
    void testMe3() {

    }

    @Test
    void testMe2() {

    }

    @SmartSkip
    @ParameterizedTest
    @ValueSource(strings = {"12", "13", "14"})
    void testMe7() {
        assertTrue(false);
    }
}
