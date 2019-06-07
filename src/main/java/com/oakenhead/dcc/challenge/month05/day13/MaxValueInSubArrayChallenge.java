package com.oakenhead.dcc.challenge.month05.day13;

import com.oakenhead.dcc.challenge.AbstractCodingChallenge;
import com.oakenhead.dcc.challenge.PairValue;
import com.oakenhead.dcc.challenge.TripleValue;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.IntStream;

@Service
public class MaxValueInSubArrayChallenge extends AbstractCodingChallenge<Integer[] , PairValue<Integer[], Integer>> {

    @Override
    public String dateString() {
        return "2019 May 13";
    }

    @Override
    public String shortName() {
        return "maximum values of each subarray";
    }

    @Override
    public String getChallengeTask() {
        return "Good morning! Here's your coding interview problem for today.\n" +
                "\n" +
                "This problem was asked by Google.\n" +
                "\n" +
                "Given an array of integers and a number k, where 1 <= k <= length of the array, compute the maximum values of each subarray of length k.\n" +
                "\n" +
                "For example, given array = [10, 5, 2, 7, 8, 7] and k = 3, we should get: [10, 7, 8, 8], since:\n" +
                "\n" +
                "    10 = max(10, 5, 2)\n" +
                "    7 = max(5, 2, 7)\n" +
                "    8 = max(2, 7, 8)\n" +
                "    8 = max(7, 8, 7)\n" +
                "\n" +
                "Do this in O(n) time and O(k) space. You can modify the input array in-place and you do not need to store the results. You can simply print them out as you compute them.";
    }

    @Override
    public Integer[] runChallengeCase(final PairValue<Integer[], Integer> input) {

        final int subarrayLength = input.right;
        final Integer[] data = input.left;
        final Integer[] result = new Integer[data.length - subarrayLength + 1];

        //o(nk)
        for (int i = 0; i < result.length; i++) {

            int currentMax = data[i];

            for (int j = 1; j < subarrayLength; j++) {
                currentMax = Math.max(currentMax, data[i + j]);
            }

            result[i] = currentMax;

        }

        return result;
    }

    public Integer[] runChallengeCaseDequeue(final PairValue<Integer[], Integer> input) {

        final int subarrayLength = input.right;
        final Integer[] data = input.left;
        final Integer[] result = new Integer[data.length - subarrayLength + 1];

        //right: value, left: place;
        final TreeSet<PairValue<Integer, Integer>> deque = new TreeSet<>(Comparator
                .comparing(value -> ((PairValue<Integer, Integer>) value).right)
                .thenComparing(value -> ((PairValue<Integer, Integer>) value).left));

        final PairValue<Integer, Integer>[] dataPairs = new PairValue[data.length];

        for (int i = 0; i < data.length; i++) {
            dataPairs[i] = new PairValue<>(data[i], i);
        }

        for (int i = 0; i < subarrayLength; i++) {

            deque.add(dataPairs[i]);
        }

        //o(n)
        for (int i = subarrayLength, j = 0; i < data.length; i++, j++) {

            result[j] = deque.last().right;
            deque.remove(dataPairs[i]);
        }

        return result;

    }

    @Override
    public List<TripleValue<Integer[], Function<PairValue<Integer[], Integer>, Integer[]>, PairValue<Integer[], Integer>>> getTestCases() {

        final Function<PairValue<Integer[], Integer>, Integer[]> testFunction = this::runChallengeCase;
        final Function<PairValue<Integer[], Integer>, Integer[]> dequeFunction = this::runChallengeCaseDequeue;

        final Integer[] testInput = new Integer[] {10, 5, 2, 7, 8, 7};
        final Integer[] testResult = new Integer[] {10, 7, 8, 8};

        return Arrays.asList(
                new TripleValue<>(testResult, testFunction, new PairValue<>(testInput, 3)),
                new TripleValue<>(testResult, dequeFunction, new PairValue<>(testInput, 3))
        );
    }
}
