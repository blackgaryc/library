package com.blackgaryc.library.tools;

import java.util.Random;

public class RandStringTools {
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
}
