package org.example.sixth;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SixthKyuTasks {
    /**
     * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these
     * multiples is 23. Finish the solution so that it returns the sum of all the multiples of 3 or 5 below the number
     * passed in. Additionally, if the number is negative, return 0 (for languages that do have them).
     * Note: If the number is a multiple of both 3 and 5, only count it once.
     */
    public static int solution(int number) {
        return IntStream.iterate(number, n -> n > 0, n -> n - 1)
                .filter(n -> n % 3 == 0 || n % 5 == 0)
                .sum();
    }

    /**
     * Write a function that accepts an array of 10 integers (between 0 and 9), that returns a string of those numbers
     * in the form of a phone number.
     */
    public static String createPhoneNumber(int[] numbers) {
        return String.format("(%d%d%d) %d%d%d-%d%d%d%d", IntStream.of(numbers).boxed().toArray());
    }

    /**
     * In this kata you are given a string for example: "example(unwanted thing)example"
     * Your task is to remove everything inside the parentheses as well as the parentheses themselves.
     * The example above would return: "exampleexample"
     */
    public static String removeParentheses(final String str) {
        final AtomicInteger counter = new AtomicInteger(0);
        return str.chars()
            .filter(ch -> {
                final boolean isOpened = ch == '(';
                final boolean isClosed = ch == ')';
                final int currentState = counter.addAndGet(isOpened ? 1 : (isClosed ? -1 : 0));
                return currentState == 0 && !isOpened && !isClosed;
            })
            .mapToObj(Character::toString)
            .collect(Collectors.joining());
    }

    public static String removeParentheses_2(final String str) {
        return str.contains("(") ? removeParentheses_2(str.replaceAll("\\([^()]*\\)", "")) : str;
    }

}
