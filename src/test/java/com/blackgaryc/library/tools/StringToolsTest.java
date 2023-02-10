package com.blackgaryc.library.tools;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class StringToolsTest {

    @Test
    void randNumberString() {
        testByLength(6);
        testByLength(16);
    }

    private static void testByLength(int length) {
        for (int i = 0; i < 100; i++) {
            String s = StringTools.randNumberString(length);
            assert s.length() == length && s.charAt(0) !='0';
            System.out.println(s);
        }
    }

    @Test
    void hex2String() {
        for (int a = 0; a < 16*16; a++) {
            String s = StringTools.byte2ByteString((byte) a);
            System.out.println(s);
            String s1 = StringTools.byte2HexString((byte) a);
            System.out.println(s1);
        }
    }

    @Test
    void splitFilename2NameAndExtension() {
        String[] strings={"abc.pdf","abc.c.pdf"};
        for (String s :
                strings) {
            String[] strings1 = StringTools.splitFilename2NameAndExtension(s);
            System.out.println("s = " + Arrays.toString(strings1));
        }
    }
}