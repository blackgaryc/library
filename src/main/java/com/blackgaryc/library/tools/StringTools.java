package com.blackgaryc.library.tools;

import java.util.Random;

public class StringTools {
    public static String randNumberString(int length) {
        if (length<=0){
            throw new RuntimeException("length can't less than zero");
        }
        Random random = new Random();
        if (length > 8) {
            StringBuilder builder = new StringBuilder();
            builder.append(random.nextInt(1, 9));
            while (--length > 0) {
                builder.append(random.nextInt(9));
            }
            return builder.toString();
        }
        int pow = (int) Math.pow(10, length - 1);
        int nextInt = random.nextInt(pow, pow * 10 - 1);
        return String.valueOf(nextInt);
    }
    public static String byte2HexString(byte hex){
        int i0 = hex >> 4 & 0x0f;
        int i1 = hex & 0x0F;
        return ""+Character.forDigit(i0, 16)+Character.forDigit(i1,16);
    }
    public static String byte2ByteString(byte b){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            builder.append(b&0x01);
            b>>=1;
        }
        return builder.reverse().toString();
    }
    public static String bytes2HexString(byte[] bytes){
        StringBuilder builder = new StringBuilder(bytes.length*2);
        for (byte b: bytes){
            builder.append(byte2HexString(b));
        }
        return builder.toString();
    }

    public static String[] splitFilename2NameAndExtension(String filename){
        int i = filename.lastIndexOf('.');
        String[] strings = new String[2];
        if (i==-1){
            strings[0] = filename;
            strings[1] = "";
        }else {
            strings[0]=filename.substring(0,i);
            strings[1]=filename.substring(i);
        }
        return strings;
    }

}
