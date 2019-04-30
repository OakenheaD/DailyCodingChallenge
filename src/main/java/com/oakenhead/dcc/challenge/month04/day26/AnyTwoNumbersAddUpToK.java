package com.oakenhead.dcc.challenge.month04.day26;

import com.oakenhead.dcc.challenge.AbstractCodingChallenge;
import com.oakenhead.dcc.challenge.PairValue;
import org.h2.util.IntArray;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class AnyTwoNumbersAddUpToK extends AbstractCodingChallenge<Boolean, IntArrayAndInt> {

    @Override
    public String dateString() {
        return "2019 April 26";
    }

    @Override
    public String shortName() {
        return "Check if any numbers in array add up to K";
    }

    @Override
    public String getChallengeTask() {
        return "" +
                "Good morning! Here's your coding interview problem for today.\n" +
                "\n" +
                "This problem was recently asked by Google.\n" +
                "\n" +
                "Given a list of numbers and a number k, return whether any two numbers from the list add up to k.\n" +
                "\n" +
                "For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.\n" +
                "\n" +
                "Bonus: Can you do this in one pass?";
    }

    @Override
    public Boolean runChallengeCase(final IntArrayAndInt input) {

        return isThereASumInThisArray(input.intArray, input.intNum);

    }

    @Override
    public List<PairValue<Boolean, IntArrayAndInt>> getTestCases() {

        return Arrays.asList(
                new PairValue(true, new IntArrayAndInt(new int[] {10, 15, 3, 7}, 17))
        );

    }

    private boolean isThereASumInThisArray(final int[] array, final int sum) {

        final HashSet<Integer> counterSums = new HashSet<>(array.length);

        for (int i = 0; i < array.length; i++) {

            final int counterSum = sum - array[i];

            if (counterSums.contains(counterSum)) {

                return true;

            } else {

                counterSums.add(array[i]);

            }

        }

        return false;

    }
}
