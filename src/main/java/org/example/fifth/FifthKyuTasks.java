package org.example.fifth;

import java.util.Map;
import java.util.function.Function;
import java.util.function.IntFunction;

public class FifthKyuTasks {

    /**
     * The rgb function is incomplete. Complete it so that passing in RGB decimal values will result in a hexadecimal
     * representation being returned. Valid decimal values for RGB are 0 - 255. Any values that fall out of that range
     * must be rounded to the closest valid value.
     * Note: Your answer should always be 6 characters long, the shorthand with 3 will not work here.
     */

    public static String rgb(int r, int g, int b) {
        final IntFunction<Integer> normalize = value -> value < 0 ? 0 : (Math.min(value, 255));
        final int normalizedRed = normalize.apply(r);
        final int normalizedGreen= normalize.apply(g);
        final int normalizedBlue = normalize.apply(b);
        return String.format("%02X%02X%02X", normalizedRed, normalizedGreen, normalizedBlue);
    }

    public static void main(String[] args) {
        System.out.println(rgb(-20, 275, 125));
    }
}
