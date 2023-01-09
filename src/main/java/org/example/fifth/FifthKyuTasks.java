package org.example.fifth;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class FifthKyuTasks {

    /**
     * The rgb function is incomplete. Complete it so that passing in RGB decimal values will result in a hexadecimal
     * representation being returned. Valid decimal values for RGB are 0 - 255. Any values that fall out of that range
     * must be rounded to the closest valid value. Note: Your answer should always be 6 characters long, the shorthand
     * with 3 will not work here.
     */

    public static String rgb(int r, int g, int b) {
        final IntFunction<Integer> normalize = value -> value < 0 ? 0 : (Math.min(value, 255));
        final int normalizedRed = normalize.apply(r);
        final int normalizedGreen = normalize.apply(g);
        final int normalizedBlue = normalize.apply(b);
        return String.format("%02X%02X%02X", normalizedRed, normalizedGreen, normalizedBlue);
    }

    /**
     * You have been hired by a major MP3 player manufacturer to implement a new music compression standard. In this
     * kata you will implement the ENCODER, and its companion kata deals with the DECODER. It can be considered a harder
     * version of Range Extraction.
     */
    public static String compress(int[] raw) {
        final List<String> result = new ArrayList<>();
        for (int i = 0; i < raw.length; i++) {
            if (i == raw.length - 1) {
                result.add(String.valueOf(raw[i]));
            } else {
                final Sequences sequences = new Sequences(raw[i], raw[i + 1]);
                int j = i + 2;
                while (j < raw.length && sequences.add(raw[j])) j++;
                result.add(sequences.toString());
                i += sequences.size() - 1;
            }
        }
        return String.join(",", result);
    }

    public static class Sequences {
        private final List<Integer> sequence = new ArrayList<>();
        public final int difference;

        public Sequences(int first, int second) {
            sequence.add(first);
            sequence.add(second);
            difference = second - first;
        }

        public boolean add(int value) {
            final boolean isTheSameGap = (value - sequence.get(sequence.size() - 1)) == difference;
            if (isTheSameGap) {
                this.sequence.add(value);
            }
            return isTheSameGap;
        }

        public int size() {
            return sequence.size() == 2 && !sequence.get(0).equals(sequence.get(1))
                ? 1
                : sequence.size();
        }

        @Override
        public String toString() {
            if (sequence.size() == 2 && !sequence.get(0).equals(sequence.get(1))) {
                sequence.remove(1);
            }
            return sequence.size() > 1
                ? this.computeValue()
                : sequence.get(0).toString();
        }

        private String computeValue() {
            final int diff = Math.abs(difference);
            return diff == 0
                ? String.format("%d*%d", sequence.get(0), sequence.size())
                : this.computeWithDiff(diff);
        }

        private String computeWithDiff(int diff) {
            return diff == 1
                ? String.format("%d-%d", sequence.get(0), sequence.get(sequence.size() - 1))
                : String.format("%d-%d/%d", sequence.get(0), sequence.get(sequence.size() - 1), diff);
        }
    }

    /**
     * Complete the function that
     * accepts two integer arrays of equal length
     * compares the value each member in one array to the corresponding member in the other
     * squares the absolute value difference between those two values
     * and returns the average of those squared absolute value difference between each member pair.
     */
    public static double solution(int[] arr1, int[] arr2) {
        return IntStream.range(0, arr1.length)
            .mapToDouble(i -> Math.pow(Math.abs(arr1[i] - arr2[i]), 2))
            .average()
            .getAsDouble();
    }

    /**
     * In this example you have to validate if a user input string is alphanumeric. The given string is not nil/null/NULL/None, so you don't have to check that.
     * The string has the following conditions to be alphanumeric:
     * At least one character ("" is not valid)
     * Allowed characters are uppercase / lowercase latin letters and digits from 0 to 9
     * No whitespaces / underscore
     */
    public static boolean alphanumeric(String s){
        return s.matches("^[a-zA-Z0-9]+$");
    }


}
