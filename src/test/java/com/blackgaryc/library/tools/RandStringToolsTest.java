package com.blackgaryc.library.tools;

import org.junit.jupiter.api.Test;

class RandStringToolsTest {

    @Test
    void randNumberString() {
        testByLength(6);
        testByLength(16);
    }

    private static void testByLength(int length) {
        for (int i = 0; i < 100; i++) {
            String s = RandStringTools.randNumberString(length);
            assert s.length() == length && s.charAt(0) !='0';
            System.out.println(s);
        }
    }
}