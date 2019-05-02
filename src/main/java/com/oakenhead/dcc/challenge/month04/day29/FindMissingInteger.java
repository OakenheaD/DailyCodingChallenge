package com.oakenhead.dcc.challenge.month04.day29;

import com.oakenhead.dcc.challenge.AbstractCodingChallenge;
import com.oakenhead.dcc.challenge.TripleValue;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Component
public class FindMissingInteger extends AbstractCodingChallenge<Integer, List<Integer>> {

    @Override
    public String dateString() {
        return "2019 April 29";
    }

    @Override
    public String shortName() {
        return "Find First Positive Missing Integer";
    }

    @Override
    public String getChallengeTask() {
        return "" +
                "This problem was asked by Stripe.\n" +
                "\n" +
                "Given an array of integers, find the first missing positive integer in linear time and constant space. " +
                "In other words, find the lowest positive integer that does not exist in the array. " +
                "The array can contain duplicates and negative numbers as well.\n" +
                "\n" +
                "For example, the input [3, 4, -1, 1] should give 2. The input [1, 2, 0] should give 3.\n" +
                "\n" +
                "You can modify the input array in-place.";
    }

    @Override
    public Integer runChallengeCase(final List<Integer> input) {

        final int inputSize = input.size();

        int[] ordered = new int[inputSize];
        Arrays.fill(ordered, -1);

        for (int i = 0; i < input.size(); i++) {

            final int current = input.get(i);

            if (current <= 0) {
                continue;
            }

            if (current >= inputSize) {
                continue;
            }

            ordered[current] = current;

        }

        for (int i = 1; i < ordered.length; i++) {

            if (ordered[i] < 0) {

                return i;

            }

        }

        return ordered.length;
    }

    @Override
    public List<TripleValue<Integer, Function<List<Integer>, Integer>, List<Integer>>> getTestCases() {

        final Function<List<Integer>, Integer> testFunction = this::runChallengeCase;

        return Arrays.asList(
                new TripleValue<>(2, testFunction, Arrays.asList(3, 4, -1, 1)),
                new TripleValue<>(2, testFunction, Arrays.asList(4, 3, -1, 1)),
                new TripleValue<>(2, testFunction, Arrays.asList(-1, 4, 3, 1)),
                new TripleValue<>(3, testFunction, Arrays.asList(1, 2, 0)),
                new TripleValue<>(3, testFunction, Arrays.asList(2, 1, 0)),
                new TripleValue<>(3, testFunction, Arrays.asList(0, 2, 1))
        );
    }
}
